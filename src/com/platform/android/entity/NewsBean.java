package com.platform.android.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "news")
public class NewsBean implements Serializable{

	private static final long serialVersionUID = 7760189691958831528L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "c_id", length = 32)
	private String id;
	
	@Column(name = "c_title", length = 255)
	private String c_title;
	
	@Column(name = "c_picture", length = 255)
	private String c_picture;
	
	@Column(name = "c_content_path", length = 255)
	private String c_content_path;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getC_title() {
		return c_title;
	}

	public void setC_title(String c_title) {
		this.c_title = c_title;
	}

	public String getC_picture() {
		return c_picture;
	}

	public void setC_picture(String c_picture) {
		this.c_picture = c_picture;
	}

	public String getC_content_path() {
		return c_content_path;
	}

	public void setC_content_path(String c_content_path) {
		this.c_content_path = c_content_path;
	}
	
}
