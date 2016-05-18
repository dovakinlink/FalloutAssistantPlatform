package com.platform.login.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.platform.base.SysConst;
import com.platform.system.bean.UserBean;
import com.platform.common.dao.impl.CommonDaoImpl;
import com.platform.login.dao.LoginDao;
import com.platform.system.entity.UserInfo;

/**
 * 
 * ClassName：LoginDaoImpl
 * Description：
 * 
 * @author: 刘焕超
 * @date: 2015-5-18 下午2:50:14
 *        note:
 * 
 */
@Repository
public class LoginDaoImpl extends CommonDaoImpl implements LoginDao {

	public List<UserInfo> checkUserInfo(UserBean userBean) {

		List<String> params = new ArrayList<String>();
		params.add(userBean.getUserName());
		params.add(userBean.getPassword());
		params.add(SysConst.DICT_DELETE_NO);
		//params.add(userBean.getUserType());
		
		@SuppressWarnings("unchecked")
		List<UserInfo> list = (List<UserInfo>) getListByHQL(
				"from UserInfo where userName =? and password = ? and deleteFlag =? ", params.toArray(),
				null);

		return list;
	}

}
