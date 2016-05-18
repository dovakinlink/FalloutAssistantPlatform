package com.platform.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * ClassName：UserInfo
 * Description：系统用户表
 * @author: 刘焕超
 * @date: 2015-5-18 下午3:51:07
 * note:
 *
 */
@Entity
@Table(name = "t_platform_user")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 9019554946160748165L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "c_id", length = 32)
	private String userId;

	/**
	 * 登录用户名
	 */
	@Column(name = "c_name", length = 32)
	private String userName;
	/**
	 * 用户真实姓名
	 */
	@Column(name = "c_real_name", length = 128)
	private String userRealName;
	/**
	 * 登录密码
	 */
	@Column(name = "c_password", length = 32)
	private String password;

	/**
	 * 最后登录时间
	 */
	@Column(name = "d_last_login_time")
	private Date lastLoginTime;

	/**
	 * 最后修改时间
	 */
	@Column(name = "d_last_modify_time")
	private Date lastModifyTime;

	/**
	 * 创建人ID
	 */
	@Column(name = "c_create_user_id", length = 32)
	private String createUserId;

	/**
	 * 删除标志 0删除 1未删除
	 */
	@Column(name = "c_delete_flag", length = 2)
	private String deleteFlag;

	/**
	 * 用户类型 0系统用户，可登录  1第三方用户，不能登录
	 */
	@Column(name = "c_user_type", length = 2)
	private String userType;

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
