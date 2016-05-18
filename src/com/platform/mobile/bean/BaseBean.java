package com.platform.mobile.bean;

import java.io.Serializable;

/**
 * 
 * ClassName：BaseBean
 * Description：客户端请求和响应信息基础类
 * 
 * @author: 刘焕超
 * @date: 2015-5-19 上午10:56:21
 *        note:
 * 
 */
public abstract class BaseBean implements Serializable {

	private static final long serialVersionUID = 6672751550244686626L;
	/**
	 * 一个字符串，指定JSON-RPC协议版本，目前使用”2.0”
	 */
	private String jsonrpc = "2.0";

	/**
	 * 调用方法
	 */
	private String method;
	
	/**
	 * 客户端信息
	 */
	private String clientInfo;

	public String getJsonrpc() {
		return jsonrpc;
	}

	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(String clientInfo) {
		this.clientInfo = clientInfo;
	}

}
