package com.platform.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.base.SysException;
import com.platform.bean.PageInfo;
import com.platform.system.bean.DictItemBean;
import com.platform.system.dao.DictDao;
import com.platform.system.entity.DictItemInfo;
import com.platform.system.service.DictService;

/**
 * 
 * ClassName：DictServiceImpl
 * Description：字典业务处理
 * 
 * @author: 刘焕超
 * @date: 2015年5月25日 下午7:50:51
 *        note:
 *
 */
@Service
public class DictServiceImpl implements DictService {

	private static final Logger log = LoggerFactory.getLogger(DictServiceImpl.class);

	@Autowired
	private DictDao dao;

	@Transactional
	public void addDictItem(DictItemBean bean) throws SysException {

		try {

			DictItemInfo info = new DictItemInfo();

			BeanUtils.copyProperties(bean, info);
			info.setCreateTime(new Date());

			dao.create(info);

			log.debug("保存字典项成功");

		} catch (Exception e) {
			log.error("保存字典项失败:{}", e.getMessage(), e);
			throw new SysException("获取日志详细失败:" + e.getMessage());
		}

	}

	@Transactional
	public void deleteDictItem(DictItemBean bean) throws SysException {
		try {

			DictItemInfo info = (DictItemInfo) dao.getObjectById(DictItemInfo.class, bean.getItemId());

			dao.delete(info);

			log.debug("删除字典项成功");

		} catch (Exception e) {
			log.error("删除字典项失败:", e.getMessage(), e);
			throw new SysException("删除字典项失败:" + e.getMessage());
		}

	}

	@Transactional
	public void modifyDictItem(DictItemBean bean) throws SysException {

		try {

			DictItemInfo info = (DictItemInfo) dao.getObjectById(DictItemInfo.class, bean.getItemId());

			info.setName(bean.getName());
			info.setSeq(bean.getSeq());
			info.setReserve1(bean.getReserve1());
			info.setReserve2(bean.getReserve2());

			dao.update(info);

			log.debug("修改字典项成功");
		} catch (Exception e) {
			log.error("修改字典项失败:", e.getMessage(), e);
			throw new SysException("修改字典项失败:" + e.getMessage());
		}

	}

	public List<DictItemBean> getList(DictItemBean bean, PageInfo page) throws SysException {

		List<DictItemBean> list = new ArrayList<DictItemBean>();

		try {
			List<DictItemInfo> i = dao.getList(bean, page);

			for (DictItemInfo dictItemInfo : i) {

				DictItemBean b = new DictItemBean();
				BeanUtils.copyProperties(dictItemInfo, b);
				list.add(b);

			}

			log.debug("查询字典项成功,共{}条", list.size());
		} catch (Exception e) {
			log.error("查询字典项失败:", e.getMessage(), e);
			throw new SysException("查询字典项失败:" + e.getMessage());
		}

		return list;
	}

	public DictItemBean getDictItemBeanById(DictItemBean bean) throws SysException {

		try {

			DictItemInfo info = (DictItemInfo) dao.getObjectById(DictItemInfo.class, bean.getItemId());

			BeanUtils.copyProperties(info, bean);

			log.debug("查询字典项详细成功");
			
		} catch (Exception e) {
			log.error("查询字典项详细失败:", e.getMessage(), e);
			throw new SysException("查询字典项详细失败:" + e.getMessage());
		}

		return bean;
	}

}
