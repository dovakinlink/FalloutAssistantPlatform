package com.platform.system.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.platform.bean.PageInfo;
import com.platform.system.bean.UserBean;
import com.platform.common.dao.impl.CommonDaoImpl;
import com.platform.system.dao.UserDao;
import com.platform.system.entity.UserInfo;
import com.platform.tool.HelpFunctions;

/**
 * 用户信息
 * @author 刘焕超
 * 2014-9-16 上午9:48:16
 */
@Repository
public class UserDaoImpl extends CommonDaoImpl implements UserDao {

	@SuppressWarnings("unchecked")
	public List<UserInfo> getListUser(UserBean userBean, PageInfo pageInfo) {

		List<UserInfo> list = new ArrayList<UserInfo>();
		StringBuilder whereSql = new StringBuilder("from UserInfo where 1=1 ");
		List<String> params = new ArrayList<String>();

		if (!HelpFunctions.isEmpty(userBean.getDeleteFlag())) {
			whereSql.append(" and deleteFlag =? ");
			params.add(userBean.getDeleteFlag());
		}
		if (!HelpFunctions.isEmpty(userBean.getUserName())) {
			whereSql.append(" and userName =? ");
			params.add(userBean.getUserName());
		}
		if (pageInfo != null && !HelpFunctions.isEmpty(pageInfo.getOrder())) {
			whereSql.append(" order by " + pageInfo.getOrder());
		}

		list = (List<UserInfo>) getListByHQLByCache(whereSql.toString(), params.toArray(), pageInfo);

		return list;
	}

}
