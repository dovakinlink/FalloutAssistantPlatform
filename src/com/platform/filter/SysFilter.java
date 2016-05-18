package com.platform.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.platform.base.SysConfigInfo;
import com.platform.base.SysConfigNode;
import com.platform.base.SysConst;
import com.platform.system.bean.UserBean;
import com.platform.tool.HelpFunctions;

/**
 * 
 * ClassName：SysFilter
 * Description：系统过滤器
 * 路径含sso的必须登录
 * 
 * @author: 刘焕超
 * @date: 2015-5-18 上午11:39:33
 *        note:
 * 
 */
public class SysFilter implements Filter {

	private static Logger log = LoggerFactory.getLogger(SysFilter.class);

	private static String CONTEXTPATH = "";

	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		if ("".equals(CONTEXTPATH)) {
			CONTEXTPATH = request.getContextPath() + "/";
		}

		String url = request.getRequestURI();

		try {
			// 系统根路径
			if (CONTEXTPATH.equals(url)) {
				session.removeAttribute(SysConst.SESSION_USER_NAME);
				response.sendRedirect(request.getContextPath() + SysConst.LOGIN_URL);
				return;
			}
			// 需验证会话请求
			if (url.indexOf("/sso/") > -1) {

				if (url.indexOf("/login/") > -1) {
					log.debug("收到登录请求");
					session.removeAttribute(SysConst.SESSION_USER_NAME);
				} else {
					UserBean user = (UserBean) session.getAttribute(SysConst.SESSION_USER_NAME);
					if (user == null || HelpFunctions.isEmpty(user.getUserId())) {
						log.debug("尚未登录,跳转到登录页面");
						response.sendRedirect(request.getContextPath() + SysConst.LOGIN_URL);
						return;
					}
					servletRequest.setAttribute(SysConst.SYSTEM_VERSION,
							SysConfigInfo.getConfigInfoByPath(SysConfigNode.VERSIONINFO));
				}
			}
		} catch (Exception e) {
			log.error("登录过滤失败:" + e.getMessage(), e);
			response.sendRedirect(request.getContextPath() + SysConst.LOGIN_URL);
			return;
		}
		chain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {
	}

}
