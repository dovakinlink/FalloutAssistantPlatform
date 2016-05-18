package com.platform.mobile.bean;

/**
 * 
 * ClassName：reqBean
 * Description：请求信息
 * 
 * @author: 刘焕超
 * @date: 2015-5-19 上午10:39:02
 *        note:
 * 
 */
public class ReqBean extends BaseBean {

	private static final long serialVersionUID = 6756949237111030599L;
	
	/**
	 * 请求内容
	 */
	private String requestContent;
	/**
	 * 请求参数
	 */
	private Object params;

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

}
