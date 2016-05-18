package com.platform.system.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.platform.mobile.session.SessionListener;

/**
 * 
 * ClassName：SystemInit
 * Description：系统启动初始化信息
 * 
 * @author: 刘焕超
 * @date: 2015年5月27日 下午1:49:13
 *        note:
 *
 */
@Component
public class SystemInit implements InitializingBean {

	private static Logger log = LoggerFactory.getLogger(SystemInit.class);

	public void afterPropertiesSet() throws Exception {

		log.info("系统启动，开始初始化信息");

		log.debug("启动会话信息超时扫描任务");

		new SessionListener().start();

		log.debug("启动会话信息超时扫描任务结束");
	}

}
