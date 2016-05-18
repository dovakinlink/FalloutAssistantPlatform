package com.platform.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.InvalidXPathException;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.STAXEventReader;
import org.dom4j.io.XMLWriter;

/**
 * 
 * ClassName：XmlHelper
 * Description：XML工具类
 * @author: 刘焕超
 * @date: 2015-5-18 下午1:42:10
 * note:
 *
 */
public final class XmlHelper {

	private static final Logger log4j = Logger.getLogger(XmlHelper.class);

	private static final SAXReader reader = new SAXReader();

	private static final DocumentFactory factory = new DocumentFactory();

	private static final String UTF8 = "UTF-8";

	public static final String ATTR_NAME_VALUE = "value";

	public static final String XML_HEADER = "<?xml version='1.0' encoding='UTF-8'?>";

	private static OutputFormat format = OutputFormat.createCompactFormat();

	/**
	 * 获取 org.dom4j.Document 文档。
	 * 
	 * @param file File文件对象
	 * @return
	 */
	public static Document getDoc(File file) {

		if (file == null) {
			log4j.warn("传入了空值！");
			return null;
		}
		Document doc = null;
		try {

			doc = reader.read(file);

		} catch (Exception e) {
			log4j.error("...解析XML文件[" + file.getAbsolutePath() + "]异常："
					+ e.getMessage());
		}
		return doc;
	}

	/**
	 * 获取 org.dom4j.Document 文档。
	 * 
	 * @param file 文件完整路径
	 * @return
	 */
	public static Document getDocAsFilePath(String filePath) {

		if (filePath == null) {
			log4j.warn("传入了空值！");
			return null;
		}
		File file = new File(filePath);
		if (!file.exists()) {
			log4j.error("文件[" + filePath + "]不存在");
			return null;
		}
		Document doc = null;
		try {

			doc = reader.read(file);

		} catch (Exception e) {
			log4j.error("...解析XML文件[" + file.getAbsolutePath() + "]异常："
					+ e.getMessage());
		} finally {
			file = null;
		}
		return doc;
	}

	/**
	 * 获取 org.dom4j.Document 文档。适用于大并发量时。
	 * 
	 * @param file File文件对象
	 * @return
	 */
	public static Document getDocAsStax(File file) {

		if (file == null) {
			log4j.warn("传入了空值！");
			return null;
		}

		STAXEventReader stax = null;
		Document doc = null;
		FileInputStream fis = null;

		try {
			stax = new STAXEventReader(factory);
			fis = new FileInputStream(file);
			doc = stax.readDocument(fis);

		} catch (Exception e) {
			log4j.error("...解析XML文件[" + file.getAbsolutePath() + "]异常："
					+ e.getMessage());
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (Exception e2) {
			}
			fis = null;
			stax = null;
		}
		return doc;
	}

	/**
	 * 获取 org.dom4j.Document 文档。
	 * 
	 * @param xmlString 格式良好的 XML 字符串
	 * @return
	 */
	public static Document getDoc(String xmlString) {

		if (xmlString == null) {
			log4j.warn("传入了空值！");
			return null;
		}

		Document doc = null;

		try {
			doc = DocumentHelper.parseText(xmlString);

		} catch (Exception e) {
			log4j.error("...解析XML内容异常：" + e.getMessage());
		}
		return doc;
	}

	/**
	 * 获取 org.dom4j.Document 文档。适用于大并发量时。
	 * 
	 * @param xmlString 格式良好的 XML 字符串
	 * @return
	 */
	public static Document getDocAsStax(String xmlString) {

		if (xmlString == null) {
			log4j.warn("传入了空值！");
			return null;
		}

		STAXEventReader stax = null;
		Document doc = null;
		Reader sreader = null;

		try {
			stax = new STAXEventReader(factory);
			sreader = new StringReader(xmlString);
			doc = stax.readDocument(sreader);

		} catch (Exception e) {
			log4j.error("...解析XML内容异常：" + e.getMessage());
		} finally {
			try {
				if (sreader != null) {
					sreader.close();
				}
			} catch (Exception e2) {
			}
			sreader = null;
			stax = null;
		}
		return doc;
	}

	/**
	 * 将指定的XML文档写入 filePath 文件中。
	 * 
	 * @param filePath 文件完整路径
	 * @param doc 将要写入的 org.dom4j.Document
	 * @return
	 */
	public static boolean writerDoc(String filePath, Document doc) {

		XMLWriter writer = new XMLWriter();

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath), UTF8));

			writer.setWriter(bw);

			writer.write(doc);

			return true;

		} catch (Exception e) {
			log4j.error("...写XML文件异常：" + e.getMessage());
		} finally {
			try {
				writer.flush();
				writer.close();
				writer = null;
				//writer已将流关闭，bw不用再次关闭
				bw = null;
			} catch (Exception e2) {
			}
		}

		return true;
	}

	/**
	 * 将格式良好的 xmlDoc 文档写入 filePath 文件中。
	 * 
	 * @param filePath 文件完整路径
	 * @param xmlDoc 格式良好的XML文档
	 * @return
	 */
	public static boolean writerDoc(String filePath, String xmlDoc) {

		XMLWriter writer = new XMLWriter();

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath), UTF8));

			writer.setEscapeText(false);
			writer.setWriter(bw);

			writer.write(xmlDoc);

			return true;

		} catch (Exception e) {
			log4j.error("...写XML文件异常：" + e.getMessage());
		} finally {
			try {
				writer.flush();
				writer.close();
				writer = null;
				//writer已将流关闭，bw不用再次关闭
				bw = null;
			} catch (Exception e2) {
			}
		}

		return true;
	}

	/**
	 * 将 XML 片段格式化为 org.dom4j.Element 元素。
	 * 
	 * @param xmlSegment
	 * @return 如有异常返回null
	 */
	public Element stringToElement(String xmlSegment) {

		Document doc = null;

		try {
			doc = DocumentHelper.parseText(xmlSegment);

		} catch (Exception e) {
			log4j.error("格式化xml片段异常：" + e.getMessage());
			return null;
		}
		return doc.getRootElement();
	}

	/**
	 * 格式化XML文档。<br/><br/>
	 * 
	 * @param doc org.dom4j.Document
	 * 
	 * @return
	 */
	public static String formatXml(Node node) {

		format.setEncoding(UTF8);

		StringWriter sw = new StringWriter();

		XMLWriter xw = new XMLWriter(sw, format);

		xw.setEscapeText(false);

		try {
			xw.write(node);
			xw.flush();
			xw.close();

		} catch (IOException e) {
			log4j.error("-1 格式化XML错误" + e.getMessage());
		} finally {
			try {
				if (xw != null) {
					xw.close();
					xw = null;
				}
			} catch (Exception e2) {
			}
		}

		return sw.toString();
	}

	public static String formatXml(String xmlDoc) {

		format.setEncoding(UTF8);

		StringWriter sw = new StringWriter();

		XMLWriter xw = new XMLWriter(sw, format);

		xw.setEscapeText(false);

		try {
			xw.write(xmlDoc);
			xw.flush();
			xw.close();

		} catch (IOException e) {
			log4j.error("-1 格式化XML错误" + e.getMessage());
		} finally {
			try {
				if (xw != null) {
					xw.close();
					xw = null;
				}
			} catch (Exception e2) {
			}
		}

		return sw.toString();
	}

	/**
	 * 格式化XML文档。
	 * 
	 * @param node 要转换的org.dom4j.Node
	 * @param charset 字符集
	 * @param isTrans 是否需要转义
	 * @return
	 */
	public static String formatXml(Node node, String charset, boolean isTrans) {

		format.setEncoding(charset);

		StringWriter sw = new StringWriter();

		XMLWriter xw = new XMLWriter(sw, format);

		xw.setEscapeText(isTrans);

		try {
			xw.write(node);
			xw.flush();
			xw.close();

		} catch (IOException e) {
			log4j.error("-1 格式化XML错误" + e.getMessage());
		} finally {
			try {
				if (xw != null) {
					xw.close();
					xw = null;
				}
			} catch (Exception e2) {
			}
		}

		return sw.toString();
	}

	/**
	 * 根据XPath表达式获取文档中指定元素的值。
	 * <br/><br/>
	 * 如果有多个元素则返回第一个元素的值；如果没找到任何有效元素，则返回默认值。
	 * <br/>
	 * 如果元素存在但没有提供任何值或提供了null值，则返回默认值。
	 * <br/>
	 * 如果元素存在子节点，将返回默认值。
	 * 
	 * @param xmlNode XML节点，它可以是文档(org.dom4j.Document)、节点(org.dom4j.Node)、元素(org.dom4j.Element)。
	 * @param xpath 有效的XPath表达式
	 * @param defaultValue 默认值
	 * @return 元素值
	 */
	public static String getValue(Node xmlNode, String xpath,
			String defaultValue) {

		Node node = null;

		if (xmlNode == null) {
			log4j.warn("...doc=null");
			return defaultValue;
		}

		try {
			node = xmlNode.selectSingleNode(xpath);
		} catch (InvalidXPathException e) {
			node = null;
			log4j.error("...错误的[" + xpath + "]表达式：" + e.getMessage());
		}

		if (node == null) {
			log4j.warn("...根据提供的[" + xpath + "]表达式没有找到指定的元素，请检查书写是否正确，现在启用默认值["
					+ defaultValue + "]");
			return defaultValue;
		}

		String val = node.getText().trim();

		if (HelpFunctions.isEmpty(val)) {
			return defaultValue;
		}
		return val;
	}

	/**
	 * 根据XPath表达式获取文档中指定元素的布尔值。
	 * <br/><br/>
	 * 如果有多个元素则返回第一个元素的值；如果没找到任何有效元素，则返回默认值。
	 * <br/>
	 * 如果元素内容非 true 或 false 字样，返回默认值
	 * <br/>
	 * 如果元素存在子节点，将返回默认值。
	 * 
	 * @param xmlNode XML节点，它可以是文档(org.dom4j.Document)、节点(org.dom4j.Node)、元素(org.dom4j.Element)。
	 * @param xpath 有效的XPath表达式
	 * @param defaultValue 默认值
	 * @return 元素值
	 */
	public static boolean getValue(Node xmlNode, String xpath,
			boolean defaultValue) {

		boolean flag = defaultValue;

		Node node = null;

		if (xmlNode == null) {
			log4j.warn("...xmlNode=null");
			return defaultValue;
		}

		try {
			node = xmlNode.selectSingleNode(xpath);
		} catch (InvalidXPathException e) {
			node = null;
			log4j.error("...错误的XPath表达式：" + e.getMessage());
		}

		if (node == null) {
			log4j.warn("...根据提供的[" + xpath + "]表达式没有找到指定的元素，请检查书写是否正确，现在启用默认值["
					+ defaultValue + "]");
			return flag;
		}

		try {
			flag = Boolean.valueOf(node.getText().trim());
		} catch (Exception e) {
			flag = defaultValue;
		}

		return flag;
	}

	/**
	 * 根据XPath表达式获取文档中指定元素的整型值。
	 * <br/><br/>
	 * 如果有多个元素则返回第一个元素的值；如果没找到任何有效元素，则返回默认值。
	 * <br/>
	 * 如果元素内容非数字格式，返回默认值
	 * <br/>
	 * 如果元素存在子节点，将返回默认值。
	 * 
	 * @param doc org.dom4j.Document
	 * @param xpath 有效的XPath表达式
	 * @param defaultValue 默认值
	 * @return 元素值
	 */
	public static int getValue(Node xmlNode, String xpath, int defaultValue) {

		int flag = defaultValue;

		Node node = null;

		if (xmlNode == null) {
			log4j.warn("...xmlNode=null");
			return defaultValue;
		}

		try {
			node = xmlNode.selectSingleNode(xpath);
		} catch (InvalidXPathException e) {
			node = null;
			log4j.error("...错误的[" + xpath + "]表达式：" + e.getMessage());
		}

		if (node == null) {
			log4j.warn("...根据提供的[" + xpath + "]表达式没有找到指定的元素，请检查书写是否正确，现在启用默认值["
					+ defaultValue + "]");
			return flag;
		}

		try {
			flag = Integer.valueOf(node.getText().trim());
		} catch (Exception e) {
			flag = defaultValue;
		}

		return flag;
	}

	/**
	 * 根据XPath表达式获取文档中指定元素的属性值。
	 * <br/><br/>
	 * 如果有多个元素则返回第一个元素相应的属性值；如果没找到任何有效元素的属性，则返回默认值。
	 * <br/>
	 * 如果元素属性存在但没有提供任何值或提供了null值，则返回默认值。
	 * 
	 * @param xmlNode XML节点，它可以是文档(org.dom4j.Document)、节点(org.dom4j.Node)、元素(org.dom4j.Element)。
	 * @param xpath 有效的XPath表达式
	 * @param attrName 属性名
	 * @param defaultValue 默认值
	 * @return 元素值
	 */
	public static String getAttributeValue(Node xmlNode, String xpath,
			String attrName, String defaultValue) {

		Node node = null;

		if (xmlNode == null) {
			log4j.warn("...xmlNode=null");
			return defaultValue;
		}

		try {
			node = xmlNode.selectSingleNode(xpath);
		} catch (InvalidXPathException e) {
			node = null;
			log4j.error("...错误的[" + xpath + "]表达式：" + e.getMessage());
		}

		if (node == null) {
			log4j.warn("...根据提供的[" + xpath + "]表达式没有找到指定的元素，请检查书写是否正确，现在启用默认值["
					+ defaultValue + "]");
			return defaultValue;
		}

		String val = node.valueOf("@" + attrName);

		if (HelpFunctions.isEmpty(val)) {
			return defaultValue;
		}
		return val;
	}

	/**
	 * 根据XPath表达式获取文档中指定元素属性的布尔值。
	 * <br/><br/>
	 * 如果有多个元素则返回第一个元素相应的属性值；如果没找到任何有效元素属性，则返回默认值。
	 * <br/>
	 * 如果元素属性不存在、内容非 true 或 false 字样，返回默认值
	 * 
	 * @param xmlNode XML节点，它可以是文档(org.dom4j.Document)、节点(org.dom4j.Node)、元素(org.dom4j.Element)。
	 * @param xpath 有效的XPath表达式
	 * @param attrName 属性名
	 * @param defaultValue 默认值
	 * @return 元素值
	 */
	public static boolean getAttributeValue(Node xmlNode, String xpath,
			String attrName, boolean defaultValue) {

		boolean flag = defaultValue;

		Node node = null;

		if (xmlNode == null) {
			log4j.warn("...xmlNode=null");
			return defaultValue;
		}

		try {
			node = xmlNode.selectSingleNode(xpath);
		} catch (InvalidXPathException e) {
			node = null;
			log4j.error("...错误的XPath表达式：" + e.getMessage());
		}

		if (node == null) {
			log4j.warn("...根据提供的[" + xpath + "]表达式没有找到指定的元素，请检查书写是否正确，现在启用默认值["
					+ defaultValue + "]");
			return flag;
		}

		try {
			flag = Boolean.valueOf(node.valueOf("@" + attrName));
		} catch (Exception e) {
			flag = defaultValue;
		}

		return flag;
	}

	/**
	 * 根据XPath表达式获取文档中指定元素属性的整型值。
	 * <br/><br/>
	 * 如果有多个元素则返回第一个元素相应的属性值；如果没找到任何有效元素属性，则返回默认值。
	 * <br/>
	 * 如果元素属性不存在或内容非数字格式，返回默认值
	 * 
	 * @param doc org.dom4j.Document
	 * @param xpath 有效的XPath表达式
	 * @param attrName 属性名
	 * @param defaultValue 默认值
	 * @return 元素值
	 */
	public static int getAttributeValue(Node xmlNode, String xpath,
			String attrName, int defaultValue) {

		int flag = defaultValue;

		Node node = null;

		if (xmlNode == null) {
			log4j.warn("...xmlNode=null");
			return defaultValue;
		}

		try {
			node = xmlNode.selectSingleNode(xpath);
		} catch (InvalidXPathException e) {
			node = null;
			log4j.error("...错误的[" + xpath + "]表达式：" + e.getMessage());
		}

		if (node == null) {
			log4j.warn("...根据提供的[" + xpath + "]表达式没有找到指定的元素，请检查书写是否正确，现在启用默认值["
					+ defaultValue + "]");
			return flag;
		}

		try {
			flag = Integer.valueOf(node.valueOf("@" + attrName));
		} catch (Exception e) {
			flag = defaultValue;
		}

		return flag;
	}

	/**
	 * 判断节点是否包含了元数据。
	 * 
	 * @param node
	 * @return
	 */
	public static boolean isCDATAValue(Node node) {

		if (node != null && !HelpFunctions.isEmpty(node.getText())) {

			String nv = node.asXML();

			boolean has = nv.indexOf("<![CDATA[") > -1;

			if (has) {
				return true;
			} else {
				return nv.indexOf("&lt;") > -1;
			}
		}
		return false;
	}
}
