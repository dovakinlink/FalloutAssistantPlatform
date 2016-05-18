package com.platform.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * 
 * ClassName：LogInfo
 * Description：系统日志表
 * @author: 刘焕超
 * @date: 2015年5月23日 上午11:42:40
 * note:
 *
 */
@Entity
@Table(name = "t_platform_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LogInfo implements Serializable {

	private static final long serialVersionUID = -1084614943198153584L;

	/**
	 * 日志ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "c_id", length = 32)
	private String logId;

	@Column(name = "c_client_id", length = 128)
	private String clientId;

	@Lob
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	@Column(name = "c_request")
	private String request;

	@Lob
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	@Column(name = "c_result")
	private String result;

	@Column(name = "c_type", length = 16)
	private String type;

	@Column(name = "d_create_time")
	private Date createTime;
	
	@Column(name = "c_state", length = 16)
	private String state;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
