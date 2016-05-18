package com.platform.tool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * ClassName：JsonUtils
 * Description：JSON工具类
 * @author: 刘焕超
 * @date: 2015-5-18 下午1:40:33
 * note:
 *
 */
public class JsonUtils {

	private static ObjectMapper mapper = new ObjectMapper();

	private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
	
	/**
	 * 
	 * 方法描述：转换对象为JSON字符串
	 * @author: 刘焕超
	 * @date: 2015-5-18 下午1:40:44
	 * @param value
	 * @return
	 */
	public static String toJsonStr(Object value) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			mapper.setDateFormat(dateFormat);
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			log.error("转换对象为JSON字符串异常", e);
		}
		return "";
	}
	
	/**
	 * 
	 * 方法描述：转化json字符串为对象
	 * @author: 刘焕超
	 * @date: 2015-5-18 下午1:40:55
	 * @param context
	 * @param valueType
	 * @return
	 */
	public static <T> T readValue(String context, Class<T> valueType){
		try {
			return  mapper.readValue(context, valueType);
		} catch (Exception e) {
			log.error("转化json字符串为对象异常", e);
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：转换JSon对象为制定类对象
	 * @author: 刘焕超
	 * @date: 2015-5-18 下午1:41:04
	 * @param context
	 * @param collectionClass
	 * @param elementCLasses
	 * @return
	 */
	public static <T> T readValue(String context, Class<?> collectionClass,  Class<?>... elementCLasses){
		try {
			//return  mapper.readValue(context, new TypeReference<SynRst<SynDepartment>>() {});
			return  mapper.readValue(context, getCollectionJavaType(collectionClass, elementCLasses));
		} catch (Exception e) {
			log.error("转化json字符串为对象异常", e);
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：根据类对象获得JavaType类型
	 * @author: 刘焕超
	 * @date: 2015-5-18 下午1:41:12
	 * @param collectionClass
	 * @param elementCLasses
	 * @return
	 */
	private static JavaType getCollectionJavaType(Class<?> collectionClass, Class<?>... elementCLasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementCLasses);
	}
	
	/**
	 * 获取返回结果json
	 * @param msg
	 * @param state
	 * @return
	 */
	public static String getResultJson(String msg,String state) {
		
		HashMap<String, String> errorInfo = new HashMap<String, String>();
		errorInfo.put("state", state);
		errorInfo.put("data", msg);
		
		return toJsonStr(errorInfo);
	}
}
