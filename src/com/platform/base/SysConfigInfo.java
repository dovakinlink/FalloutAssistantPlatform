package com.platform.base;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.platform.bean.ConfigXmlBean;
import com.platform.tool.HelpFunctions;
import com.platform.tool.XmlHelper;

/**
 * 系统配置信息
 * @author 刘焕超
 * 2014-9-15 上午10:41:54
 */
@Component
public class SysConfigInfo implements InitializingBean{

	private static final Logger log = LoggerFactory.getLogger(SysConfigInfo.class);

	/**
	 * 存储配置信息，以路径为KEY
	 */
	private static List<ConfigXmlBean> configList = new ArrayList<ConfigXmlBean>();

	/**
	 * 初始化配置文件信息
	 * @throws Exception
	 */
	public static void initAllConfigInfo() {

		try {
			configList.clear();

			String path = System.getProperty(SysConst.SYSTEM_ROOT_PATH) + SysConst.CONFIG_XML_PATH;

			Document doc = XmlHelper.getDoc(new File(path));

			@SuppressWarnings("unchecked")
			List<Node> root = doc.selectSingleNode("root").selectNodes("*");
			for (Node node : root) {
				String pathTemp = node.getName();

				@SuppressWarnings("unchecked")
				List<Node> childs = node.selectNodes("*");

				for (Node node2 : childs) {

					ConfigXmlBean cxb = new ConfigXmlBean();
					cxb.setNodeName(node2.getName());
					cxb.setNodeDesc(node2.valueOf("@description"));
					if(!HelpFunctions.isEmpty(node2.valueOf("@showEnable"))){
						cxb.setShowEnable(node2.valueOf("@showEnable"));
					}
					cxb.setNodeValue(node2.getText().trim());
					cxb.setPath(pathTemp + "/" + node2.getName());
					configList.add(cxb);
				}
			}
		} catch (Exception e) {
			log.error("读取加载配置文件信息失败:" + e.getMessage(), e);
		}
	}

	/**
	 * 获取全部配置信息
	 * @return
	 */
	public static List<ConfigXmlBean> getAllConfigInfo() {

		if (configList.isEmpty()) {
			initAllConfigInfo();
		}
		return configList;
	}

	/**
	 * 根据路径获取对应的配置信息
	 * @param path
	 * @return
	 */
	public static ConfigXmlBean getConfigInfoByPath(String path) {

		for (ConfigXmlBean bean : configList) {

			if (bean.getPath().equals(path)) {
				return bean;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param list
	 * @throws SysException 
	 */
	public static void updateConfigXml(List<HashMap<String, String>> list) throws SysException {
		try {
			String path = System.getProperty(SysConst.SYSTEM_ROOT_PATH) + SysConst.CONFIG_XML_PATH;
			Document doc = XmlHelper.getDoc(new File(path));
			Node root = doc.selectSingleNode("root");

			for (HashMap<String, String> map : list) {
				Element e = (Element) root.selectSingleNode(map.get("key"));
				e.setText(map.get("value"));
			}

			XmlHelper.writerDoc(path, doc);
		} catch (Exception e) {
			log.error("保存配置信息失败:" + e.getMessage(), e);
			throw new SysException("保存配置信息失败:" + e.getMessage());
		}
	}

	public void afterPropertiesSet() throws Exception {
		initAllConfigInfo();
	}
}
