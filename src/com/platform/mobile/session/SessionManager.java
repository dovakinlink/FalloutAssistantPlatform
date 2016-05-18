package com.platform.mobile.session;

import java.util.Hashtable;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.platform.base.SysConfigInfo;
import com.platform.base.SysConfigNode;
import com.platform.base.SysException;
import com.platform.tool.HelpFunctions;

/**
 * 
 * ClassName：SessionManager
 * Description：用户会话信息管理
 * 
 * @author: 刘焕超
 * @date: 2015年5月27日 上午10:18:02
 *        note:
 *
 */
public class SessionManager {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * 会话信息缓存
	 */
	private static Hashtable<String, UserSession> userCache = new Hashtable<String, UserSession>();

	private static SessionManager sessionManager = null;

	public static SessionManager getInstance() {

		if (sessionManager == null) {
			sessionManager = new SessionManager();
		}
		return sessionManager;
	}

	/**
	 * 
	 * 方法描述：添加会话信息
	 * 
	 * @author: 刘焕超
	 * @date: 2015年5月27日 上午11:05:56
	 * @param sessionId
	 * @param obj
	 * @throws SysException
	 */
	public void addSessionInfo(String sessionId, UserSession obj) throws SysException {

		if (HelpFunctions.isEmpty(sessionId)) {
			throw new SysException("会话ID不能为空");
		}

		if (obj == null) {
			throw new SysException("会话内容不能为空");
		}

		userCache.put(sessionId, obj);
	}

	/**
	 * 
	 * 方法描述：移出会话信息
	 * 
	 * @author: 刘焕超
	 * @date: 2015年5月27日 上午11:08:23
	 * @param sessionId
	 * @throws SysException
	 */
	public void removeSessionInfo(String sessionId) throws SysException {

		if (HelpFunctions.isEmpty(sessionId)) {
			return;
		}

		if (userCache.containsKey(sessionId)) {
			userCache.remove(sessionId);
		}
	}

	/**
	 * 
	 * 方法描述：删除会话超时用户信息
	 * 
	 * @author: 刘焕超
	 * @date: 2015年5月27日 上午11:44:50
	 * @throws SysException
	 */
	public void removeSessionInfoTimeOut() throws SysException {

		log.debug("开始检测会话超时信息");

		Long timeout = 30 * 60 * 1000L;
		try {
			timeout = Long.valueOf(SysConfigInfo.getConfigInfoByPath(SysConfigNode.SESSIONTIMEOUT).getNodeValue());

		} catch (Exception e) {
			log.error("会话超时时间配置错误，采用默认超时时间30分钟");
		}

		Set<String> keys = userCache.keySet();
		log.debug("共{}个在线会话", keys.size());

		Long timenow = System.currentTimeMillis();

		for (String key : keys) {
			Long time = userCache.get(key).getLastModifyTime();
			if (timenow - time > timeout) {
				userCache.remove(key);
			}
		}
		
		log.debug("检测后剩余{}个会话信息", userCache.size());

	}
}
