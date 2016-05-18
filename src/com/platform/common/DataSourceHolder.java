package com.platform.common;

/**
 * 
 * ClassName：DataSourceHolder
 * Description：多数据源控制,静态值和spring中配置的key对应
 * @author: 刘焕超
 * @date: 2015-5-19 下午4:47:38
 * note:
 *
 */
public class DataSourceHolder {
	public final static String DATA_SOURCE_PLATFORM = "platfrom";

	public final static String DATA_SOURCE_UPDATE = "update";

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setCustomerType(String customerType) {

		contextHolder.set(customerType);

	}

	public static String getCustomerType() {

		return contextHolder.get();

	}

	public static void clearCustomerType() {

		contextHolder.remove();

	}
}
