package com.platform.mobile.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.platform.base.SysException;

/**
 * 
 * ClassName：SessionListener
 * Description：用户会话超时监听
 * 
 * @author: 刘焕超
 * @date: 2015年5月27日 下午1:39:36
 *        note:
 *
 */
public class SessionListener extends Thread {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void run() {

		log.info("启动用户会话超时扫描任务");

		while (true) {

			try {
				SessionManager.getInstance().removeSessionInfoTimeOut();
			} catch (SysException e1) {
				log.error("检测用户会话超时失败:{}", e1.getMessage());
			}

			try {
				SessionListener.sleep(60 * 1000);
			} catch (InterruptedException e) {
			}
		}

	}
}
