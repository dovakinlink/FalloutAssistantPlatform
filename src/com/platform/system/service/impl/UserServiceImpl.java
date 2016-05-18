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

import com.platform.base.SysConst;
import com.platform.base.SysException;
import com.platform.bean.PageInfo;
import com.platform.system.bean.UserBean;
import com.platform.system.dao.UserDao;
import com.platform.system.entity.UserInfo;
import com.platform.system.service.UserService;
import com.platform.tool.HelpFunctions;

/**
 * 用户管理业务处理
 * @author 刘焕超
 * 2014-9-16 上午10:44:33
 */
@Service
public class UserServiceImpl implements UserService {

	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;

	public List<UserBean> getListUser(UserBean userBean, PageInfo pageInfo) throws SysException {

		List<UserBean> returnList = new ArrayList<UserBean>();
		try {
			pageInfo.setOrder("lastModifyTime desc");
			List<UserInfo> list = userDao.getListUser(userBean, pageInfo);

			for (UserInfo userInfo : list) {
				UserBean ub = new UserBean();
				BeanUtils.copyProperties(userInfo, ub);
				returnList.add(ub);
			}
		} catch (Exception e) {
			log.error("查询用户信息出错:" + e.getMessage(), e);
			throw new SysException("查询用户信息出错");
		}
		return returnList;
	}

	@Transactional
	public void saveUser(UserBean bean) throws SysException {

		try {

			bean.setLastModifyTime(new Date());
			List<UserInfo> list = userDao.getListUser(bean, null);
			if (!HelpFunctions.isEmpty(list)) {
				throw new SysException("该用户名已存在");
			}

			UserInfo ui = new UserInfo();

			bean.setPassword(HelpFunctions.getStringMD5(bean.getPassword()));

			BeanUtils.copyProperties(bean, ui);

			userDao.create(ui);

		} catch (SysException e) {
			throw new SysException(e.getMessage());
		} catch (Exception e) {
			log.error("保存用户信息出错:" + e.getMessage(), e);
			throw new SysException("保存用户信息出错");
		}

	}

	public UserBean getUserInfoById(String id) throws SysException {

		UserBean bean = new UserBean();

		try {

			UserInfo ui = (UserInfo) userDao.getObjectById(UserInfo.class, id);

			BeanUtils.copyProperties(ui, bean);

		} catch (Exception e) {
			log.error("查询用户信息出错:" + e.getMessage(), e);
			throw new SysException("查询用户信息出错");
		}

		return bean;
	}

	@Transactional
	public void modifyUserInfo(UserBean bean) throws SysException {

		try {

			UserInfo ui = (UserInfo) userDao.getObjectById(UserInfo.class, bean.getUserId());

			ui.setUserRealName(bean.getUserRealName());
			ui.setUserType(bean.getUserType());
			ui.setLastModifyTime(new Date());

			userDao.update(ui);

		} catch (Exception e) {
			log.error("修改用户信息出错:" + e.getMessage(), e);
			throw new SysException("修改用户信息出错");
		}
	}

	@Transactional
	public void modifyUserInfoPwd(UserBean bean) throws SysException {

		try {

			UserInfo ui = (UserInfo) userDao.getObjectById(UserInfo.class, bean.getUserId());

			String old = HelpFunctions.getStringMD5(bean.getPasswordOld());

			if (!old.equals(ui.getPassword())) {
				throw new SysException("原密码输入错误");
			}

			ui.setPassword(HelpFunctions.getStringMD5(bean.getPassword()));

			userDao.update(ui);

		} catch (SysException e) {
			throw e;
		} catch (Exception e) {
			log.error("修改用户信息出错:" + e.getMessage(), e);
			throw new SysException("修改用户信息出错");
		}
	}

	@Transactional
	public void resetUser(UserBean bean) throws SysException {

		try {

			log.debug("userId:" + bean.getUserId());
			UserInfo ui = (UserInfo) userDao.getObjectById(UserInfo.class, bean.getUserId());

			ui.setPassword(SysConst.SYSTEM_DEFAULT_PASSWORD);
			ui.setLastModifyTime(new Date());

			userDao.update(ui);

		} catch (Exception e) {
			log.error("重置密码出错:" + e.getMessage(), e);
			throw new SysException("重置密码出错");
		}
	}

	@Transactional
	public void deleteUser(UserBean bean) throws SysException {

		try {
			log.debug("userId:" + bean.getUserId());
			UserInfo ui = (UserInfo) userDao.getObjectById(UserInfo.class, bean.getUserId());

			ui.setDeleteFlag(SysConst.DICT_DELETE_YES);
			ui.setLastModifyTime(new Date());

			userDao.update(ui);

		} catch (Exception e) {
			log.error("删除用户信息出错:" + e.getMessage(), e);
			throw new SysException("删除用户信息出错");
		}
	}
}
