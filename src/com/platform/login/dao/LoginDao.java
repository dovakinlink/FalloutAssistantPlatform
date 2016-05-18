package com.platform.login.dao;

import java.util.List;

import com.platform.system.bean.UserBean;
import com.platform.common.dao.CommonDao;
import com.platform.system.entity.UserInfo;

/**
 * 
 * ClassName：LoginDao
 * Description：登录数据接口
 * 
 * @author: 刘焕超
 * @date: 2015-5-18 下午2:48:05
 *        note:
 * 
 */
public interface LoginDao extends CommonDao {

	/**
	 * 登录验证
	 * 
	 * @param bean
	 * @return
	 */
	public List<UserInfo> checkUserInfo(UserBean bean);
}
