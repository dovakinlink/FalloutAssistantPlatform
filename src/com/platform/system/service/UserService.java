package com.platform.system.service;

import java.util.List;


import com.platform.base.SysException;
import com.platform.bean.PageInfo;
import com.platform.system.bean.UserBean;

/**
 * 用户信息
 * @author 刘焕超
 * 2014-9-16 上午10:39:40
 */
public interface UserService {

	/**
	 * 获取用户信息
	 * @param userBean
	 * @param pageInfo
	 * @return
	 */
	public List<UserBean> getListUser(UserBean userBean, PageInfo pageInfo) throws SysException;

	/**
	 * 保存用户信息
	 * @param bean
	 * @throws SysException
	 */
	public void saveUser(UserBean bean) throws SysException;

	/**
	 * 根据用户ID获取用户信息
	 * @param id
	 * @return
	 * @throws SysException
	 */
	public UserBean getUserInfoById(String id) throws SysException;

	/**
	 * 修改用户信息
	 * @param bean
	 * @throws SysException
	 */
	public void modifyUserInfo(UserBean bean) throws SysException;
	
	/**
	 * 修改密码
	 * @param bean
	 * @throws SysException
	 */
	public void modifyUserInfoPwd(UserBean bean) throws SysException ;
	/**
	 * 重置密码为111111
	 * @param bean
	 * @throws SysException
	 */
	public void resetUser(UserBean bean) throws SysException;

	/**
	 * 删除用户信息
	 * @param bean
	 * @throws SysException
	 */
	public void deleteUser(UserBean bean) throws SysException;
}
