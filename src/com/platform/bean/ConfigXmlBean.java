package com.platform.bean;


/**
 * 系统配置信息
 * @author 刘焕超
 * 2014-9-11 上午11:26:50
 */
public class ConfigXmlBean {

	/**
	 * 节点名
	 */
	private String nodeName;

	/**
	 * 节点描述
	 */
	private String nodeDesc;

	/**
	 * 节点值
	 */
	private String nodeValue;
	
	/**
	 * 路径
	 */
	private String path;
	
	/**
	 * 是否可以显示修改
	 */
	private String showEnable="true";

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeDesc() {
		return nodeDesc;
	}

	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}

	public String getNodeValue() {
		return nodeValue;
	}

	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getShowEnable() {
		return showEnable;
	}

	public void setShowEnable(String showEnable) {
		this.showEnable = showEnable;
	}
}
