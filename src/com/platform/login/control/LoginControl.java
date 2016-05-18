package com.platform.login.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.base.SysConst;
import com.platform.base.SysControl;
import com.platform.system.bean.UserBean;
import com.platform.login.service.LoginService;
import com.platform.tool.JsonUtils;

/**
 * 
 * ClassName：LoginControl
 * Description：登录管理
 * @author: 刘焕超
 * @date: 2015-5-19 下午4:49:06
 * note:
 *
 */
@Controller
@Scope("session")
public class LoginControl extends SysControl {

	private static final Logger log = LoggerFactory.getLogger(LoginControl.class);

	@Autowired
	private LoginService loginService;
	

	@RequestMapping("/sso/login/index.do")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "login/login";
	}

	@RequestMapping("/login/checkUserInfo.do")
	public void checkUserInfo(HttpServletRequest request, HttpServletResponse response, UserBean bean) {

		log.info("收到[" + bean.getUserName() + "]用户登录系统请求");

		log.debug("LoginInfo:" + JsonUtils.toJsonStr(bean));
		UserBean ub = loginService.checkUserInfo(bean);
		if (SysConst.RESULT_BEAN_STATE_PASS.equals(ub.getState())) {
			HttpSession session = request.getSession();
			if (session.getAttribute(SysConst.SESSION_USER_NAME) != null) {
				session.removeAttribute(SysConst.SESSION_USER_NAME);
			}
			session.setAttribute(SysConst.SESSION_USER_NAME, ub);
		}

		writeJsonData(response, ub);
	}
}
