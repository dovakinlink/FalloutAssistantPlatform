package com.platform.system.bean;

import com.platform.base.SysConst;
import com.platform.system.entity.UserInfo;

/**
 * 用户信息
 * @author 刘焕超
 * 2014-9-12 下午2:12:47
 */
public class UserBean extends UserInfo {

	private static final long serialVersionUID = 1520732327075202901L;

	/**
	 * 结果
	 */
	private String state = SysConst.RESULT_BEAN_STATE_FAIL;

	/**
	 * 错误信息
	 */
	private String data;
	
	private String passwordOld;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPasswordOld() {
		return passwordOld;
	}

	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}

}
