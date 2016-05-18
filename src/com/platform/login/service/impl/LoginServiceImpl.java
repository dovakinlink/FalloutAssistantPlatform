package com.platform.login.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.base.SysConst;
import com.platform.system.bean.UserBean;
import com.platform.login.dao.LoginDao;
import com.platform.system.entity.UserInfo;
import com.platform.login.service.LoginService;
import com.platform.tool.HelpFunctions;

/**
 * 
 * ClassName：LoginServiceImpl
 * Description：登录业务处理类
 * 
 * @author: 刘焕超
 * @date: 2015-5-18 下午2:46:55
 *        note:
 * 
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	@Transactional
	public UserBean checkUserInfo(UserBean userBean) {

		UserBean ub = new UserBean();

		try {

			userBean.setPassword(HelpFunctions.getStringMD5(userBean.getPassword()));
			List<UserInfo> list = loginDao.checkUserInfo(userBean);

			if (!HelpFunctions.isEmpty(list)) {
				UserInfo ui = list.get(0);
				BeanUtils.copyProperties(ui, ub);
				ub.setState(SysConst.RESULT_BEAN_STATE_PASS);
				ui.setLastLoginTime(new Date());
				loginDao.update(ui);
			} else {
				ub.setData("用户名密码验证失败!");
			}
		} catch (Exception e) {
			ub.setData("用户名密码验证失败!");
		}

		return ub;
	}
}
