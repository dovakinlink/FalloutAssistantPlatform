package com.platform.mobile.session;

import java.io.Serializable;

/**
 * 
 * ClassName：UserSession
 * Description：会话信息父类
 * 
 * @author: 刘焕超
 * @date: 2015年5月27日 上午11:46:06
 *        note:
 *
 */
public class UserSession implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5098358603746174645L;

	/**
	 * 最后修改时间
	 */
	private Long lastModifyTime;

	/**
	 * 用户信息
	 */
	private Object obj;

	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
