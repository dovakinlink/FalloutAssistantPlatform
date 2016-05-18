package com.platform.android.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "code")
public class CodeInfo implements Serializable{

	private static final long serialVersionUID = -7489436295975628068L;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", length = 32)
	private String id;
	
	@Column(name = "c_code_name", length = 255)
	private String c_code_name;
	
	@Column(name = "c_code_des", length = 255)
	private String c_code_des;
	
	@Column(name = "c_code_value", length = 16)
	private String c_code_value;
	
	@Column(name = "c_code_type_id", length = 32)
	private String c_code_type_id;
	
	@Column(name = "c_code_type_des", length = 255)
	private String c_code_type_des;
	
	@Column(name = "c_sys_type", length = 2)
	private String c_sys_type;
	
	@Column(name = "c_code_picture", length = 255)
	private String c_code_picture;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getC_code_name() {
		return c_code_name;
	}

	public void setC_code_name(String c_code_name) {
		this.c_code_name = c_code_name;
	}

	public String getC_code_des() {
		return c_code_des;
	}

	public void setC_code_des(String c_code_des) {
		this.c_code_des = c_code_des;
	}

	public String getC_code_value() {
		return c_code_value;
	}

	public void setC_code_value(String c_code_value) {
		this.c_code_value = c_code_value;
	}

	public String getC_code_type_id() {
		return c_code_type_id;
	}

	public void setC_code_type_id(String c_code_type_id) {
		this.c_code_type_id = c_code_type_id;
	}

	public String getC_code_type_des() {
		return c_code_type_des;
	}

	public void setC_code_type_des(String c_code_type_des) {
		this.c_code_type_des = c_code_type_des;
	}

	public String getC_sys_type() {
		return c_sys_type;
	}

	public void setC_sys_type(String c_sys_type) {
		this.c_sys_type = c_sys_type;
	}

	public String getC_code_picture() {
		return c_code_picture;
	}

	public void setC_code_picture(String c_code_picture) {
		this.c_code_picture = c_code_picture;
	}

}
