package com.platform.mobile.bean;

import java.io.Serializable;

import com.platform.base.DictConst;

/**
 * 
 * ClassName：ResultBean
 * Description：响应详细信息
 * 
 * @author: 刘焕超
 * @date: 2015-5-19 上午11:04:53
 *        note:
 * 
 */
public class ResultBean implements Serializable {

	private static final long serialVersionUID = -6001845574129636263L;

	/**
	 * 执行结果 1 成功 其他失败
	 */
	private String resultStatus = DictConst.RESBEAN_ERROR;

	/**
	 * 返回内容
	 */
	private Object info;

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

}
