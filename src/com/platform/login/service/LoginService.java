package com.platform.login.service;

import com.platform.system.bean.UserBean;

/**
 * 
 * ClassName：LoginService
 * Description：登录业务处理
 * 
 * @author: 刘焕超
 * @date: 2015-5-18 下午2:45:35
 *        note:
 * 
 */
public interface LoginService {

	/**
	 * 验证用户信息
	 * 
	 * @param userBean
	 * @return
	 */
	public UserBean checkUserInfo(UserBean userBean);

}
