package com.platform.system.dao;

import java.util.List;

import com.platform.bean.PageInfo;
import com.platform.system.bean.UserBean;
import com.platform.common.dao.CommonDao;
import com.platform.system.entity.UserInfo;

/**
 * 用户信息
 * @author 刘焕超
 * 2014-9-16 上午9:47:31
 */
public interface UserDao extends CommonDao{

	/**
	 * 获取全部用户信息
	 * @param userBean
	 * @param pageInfo
	 * @return
	 */
	public List<UserInfo> getListUser(UserBean userBean , PageInfo pageInfo);
}
