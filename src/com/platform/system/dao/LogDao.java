package com.platform.system.dao;

import java.util.List;

import com.platform.bean.PageInfo;
import com.platform.common.dao.CommonDao;
import com.platform.system.bean.LogBean;
import com.platform.system.entity.LogInfo;

/**
 * 
 * ClassName：LogDao
 * Description：
 * @author: 刘焕超
 * @date: 2015年5月23日 上午11:45:26
 * note:
 *
 */
public interface LogDao extends CommonDao {

	/**
	 * 获取日志列表
	 * @param bean
	 * @return
	 */
	public List<LogInfo> getList(LogBean bean, PageInfo page);

}
