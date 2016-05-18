package com.platform.system.dao;

import java.util.List;

import com.platform.bean.PageInfo;
import com.platform.common.dao.CommonDao;
import com.platform.system.bean.DictItemBean;
import com.platform.system.bean.LogBean;
import com.platform.system.entity.DictItemInfo;
import com.platform.system.entity.LogInfo;

/**
 * 
 * ClassName：DictDao
 * Description：字典
 * @author: 刘焕超
 * @date: 2015年5月25日 下午7:42:16
 * note:
 *
 */
public interface DictDao extends CommonDao {

	/**
	 * 获取字典项列表
	 * @param bean
	 * @return
	 */
	public List<DictItemInfo> getList(DictItemBean bean, PageInfo page);

}
