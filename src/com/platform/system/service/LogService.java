package com.platform.system.service;

import java.util.List;

import com.platform.base.SysException;
import com.platform.bean.PageInfo;
import com.platform.system.bean.LogBean;


/**
 * 
 * ClassName：LogService
 * Description：日志业务处理
 * @author: 刘焕超
 * @date: 2015年5月23日 上午11:47:28
 * note:
 *
 */
public interface LogService {

	/**
	 * 录入日志
	 * @param bean
	 */
	public void addLog(LogBean bean);

	/**
	 * 获取日志列表
	 * @param bean
	 * @param page
	 * @return
	 * @throws SysException
	 */
	public List<LogBean> getList(LogBean bean, PageInfo page) throws SysException;

	/**
	 * 根据ID获取日志详细信息
	 * @param logId
	 * @return
	 * @throws SysException
	 */
	public LogBean getLogBeanById(String logId) throws SysException;
}
