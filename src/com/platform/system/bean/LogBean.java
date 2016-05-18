package com.platform.system.bean;

import java.util.Date;

import com.platform.system.entity.LogInfo;


/**
 * 
 * ClassName：LogBean
 * Description：系统日志bean
 * @author: 刘焕超
 * @date: 2015年5月23日 上午11:44:15
 * note:
 *
 */
public class LogBean extends LogInfo {

	private static final long serialVersionUID = -2980358825378546591L;

	public LogBean() {

	}

	/**
	 * 日志内容
	 * @param clientId 
	 * @param request
	 * @param result
	 * @param type
	 */
	public LogBean(String clientId, String request, String result, String type,String state) {

		this.setClientId(clientId);
		this.setRequest(request);
		this.setResult(result);
		this.setType(type);
		this.setCreateTime(new Date());
		this.setState(state);

	}

	private String timeStart;

	private String timeEnd;
	
	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

}
