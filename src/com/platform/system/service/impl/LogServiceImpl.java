package com.platform.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.base.DictConst;
import com.platform.base.SysException;
import com.platform.bean.PageInfo;
import com.platform.system.bean.LogBean;
import com.platform.system.dao.LogDao;
import com.platform.system.entity.LogInfo;
import com.platform.system.service.LogService;


/**
 * 
 * ClassName：LogServiceImpl
 * Description：日志业务处理
 * @author: 刘焕超
 * @date: 2015年5月23日 上午11:47:58
 * note:
 *
 */
@Service
public class LogServiceImpl implements LogService {

	private static final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);

	@Autowired
	private LogDao dao;

	public List<LogBean> getList(LogBean bean, PageInfo page) throws SysException {

		List<LogBean> list = new ArrayList<LogBean>();

		try {

			List<LogInfo> l = dao.getList(bean, page);
			for (LogInfo logInfo : l) {
				LogBean logBean = new LogBean();

				BeanUtils.copyProperties(logInfo, logBean);

				logBean.setType(DictConst.LOGTYPE.get(logBean.getType()));

				list.add(logBean);
			}

		} catch (Exception e) {
			log.error("获取日志列表失败:" + e.getMessage(), e);
			throw new SysException("获取日志列表失败:" + e.getMessage());
		}

		return list;
	}

	public LogBean getLogBeanById(String logId) throws SysException {

		LogBean bean = new LogBean();
		try {

			LogInfo logInfo = (LogInfo) dao.getObjectById(LogInfo.class, logId);

			BeanUtils.copyProperties(logInfo, bean);

			bean.setType(DictConst.LOGTYPE.get(bean.getType()));

		} catch (Exception e) {
			log.error("获取日志详细失败:" + e.getMessage(), e);
			throw new SysException("获取日志详细失败:" + e.getMessage());
		}

		return bean;

	}

	@Transactional
	public void addLog(LogBean bean) {

		try {

			new LogInsert(dao, bean).run();

		} catch (Exception e) {

			log.debug("启动日志记录线程失败");
		}

	}

	/**
	 * 日志录入线程
	 * @author 刘焕超
	 *
	 * 2014-10-29 下午1:40:29
	 */
	class LogInsert implements Runnable {

		private LogDao dao;
		private LogBean bean;

		public LogInsert(LogDao dao, LogBean bean) {

			this.dao = dao;
			this.bean = bean;
		}

		public void run() {
			log.debug("开始记录[" + bean.getClientId() + "]的日志");

			try {
				LogInfo li = new LogInfo();

				BeanUtils.copyProperties(bean, li);

				dao.create(li);

				log.debug("[" + bean.getClientId() + "]日志记录成功");
			} catch (Exception e) {
				log.debug("[" + bean.getClientId() + "]日志记录失败:" + e.getMessage());
			}

		}

	}

}
