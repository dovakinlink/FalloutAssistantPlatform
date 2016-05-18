package com.platform.bean;

import java.io.Serializable;

import com.platform.base.SysConst;

/**
 * 查询结果及分页信息
 * @author 刘焕超
 * 2014-9-16 上午9:58:05
 */
public class PageInfo implements Serializable{

	private static final long serialVersionUID = 6246851862200128813L;

	/**
	 * 开始位置
	 */
	private Integer startPage = 0;

	/**
	 * 结束位置
	 */
	private Integer endPage = 10;

	/**
	 * 总记录数
	 */
	private Integer totolPage;

	/**
	 * 每页数量
	 */
	private Integer pageSize = 10;

	/**
	 * 错误信息
	 */
	private String data;

	/**
	 * 请求状态
	 */
	private String state = SysConst.RESULT_BEAN_STATE_FAIL;

	/**
	 * 查询结果
	 */
	private Object object;

	/**
	 * 排序
	 */
	private String order;
	
	/**
	 * 当前页数
	 */
	private Integer currPage =1;

	public Integer getStartPage() {
		return startPage;
	}

	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}

	public Integer getEndPage() {
		return endPage;
	}

	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}

	public Integer getTotolPage() {
		return totolPage;
	}

	public void setTotolPage(Integer totolPage) {
		this.totolPage = totolPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

}
