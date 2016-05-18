package com.platform.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * ClassName：SysBaseImpl
 * Description：
 * @author: 刘焕超
 * @date: 2015-5-18 上午10:54:53
 * note:
 *
 */
public class SysBaseImpl {

	protected static ApplicationContext context;
	private static final Logger log = LoggerFactory.getLogger(SysBaseImpl.class);

	static {
		try {
			String[] xmlPaths = new String[] { "spring-mvc.xml" };
			context = new ClassPathXmlApplicationContext(xmlPaths);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}
}
