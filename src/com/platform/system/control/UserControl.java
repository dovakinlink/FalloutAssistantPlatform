package com.platform.system.control;

import java.util.List;

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
import com.platform.bean.PageInfo;
import com.platform.system.bean.UserBean;
import com.platform.system.service.UserService;
import com.platform.tool.JsonUtils;
import com.platform.tool.HelpFunctions;

/**
 * 
 * ClassName：UserControl
 * Description：用户信息管理
 * 
 * @author: 刘焕超
 * @date: 2015-5-18 下午2:51:24
 *        note:
 * 
 */
@Controller
@Scope("session")
public class UserControl extends SysControl {

	private static Logger log = LoggerFactory.getLogger(UserControl.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/sso/user/index.do")
	public String index(HttpServletRequest request, HttpServletResponse response, String url, UserBean userBean) {

		log.debug("请求跳转信息");

		request.setAttribute("UserInfo", userBean);

		return url;
	}

	@RequestMapping("/sso/user/getListUser.do")
	public void getListUser(HttpServletRequest request, HttpServletResponse response, UserBean userBean,
			PageInfo pageInfo) {

		log.debug("开始获取用户信息");
		log.debug("query:" + JsonUtils.toJsonStr(userBean));
		try {
			HelpFunctions.initPageInfoStartEnd(pageInfo);
			List<UserBean> list = userService.getListUser(userBean, pageInfo);

			pageInfo.setState(SysConst.RESULT_BEAN_STATE_PASS);
			pageInfo.setObject(list);
			pageInfo.setTotolPage(HelpFunctions.getPage(pageInfo.getPageSize(), pageInfo.getTotolPage()));

		} catch (Exception e) {
			pageInfo.setData(e.getMessage());
		}
		writeJsonData(response, pageInfo);
	}

	@RequestMapping("/sso/user/save.do")
	public void saveUser(HttpServletRequest request, HttpServletResponse response, UserBean userBean) {
		log.debug("收到保存用户信息请求");
		PageInfo pageInfo = new PageInfo();

		try {
			HttpSession session = request.getSession();
			UserBean user = (UserBean) session.getAttribute(SysConst.SESSION_USER_NAME);
			userBean.setCreateUserId(user.getUserId());
			userBean.setDeleteFlag(SysConst.DICT_DELETE_NO);
			userService.saveUser(userBean);
			pageInfo.setState(SysConst.RESULT_BEAN_STATE_PASS);

		} catch (Exception e) {
			pageInfo.setData(e.getMessage());
		}
		writeJsonData(response, pageInfo);

	}

	@RequestMapping("/sso/user/getUserInfoById.do")
	public void getUserInfoById(HttpServletRequest request, HttpServletResponse response, String userId) {

		log.debug("收到根据ID获取用户信息请求");
		PageInfo pageInfo = new PageInfo();

		try {
			UserBean bean = userService.getUserInfoById(userId);
			pageInfo.setObject(bean);
			pageInfo.setState(SysConst.RESULT_BEAN_STATE_PASS);

		} catch (Exception e) {
			pageInfo.setData(e.getMessage());
		}
		writeJsonData(response, pageInfo);

	}

	@RequestMapping("/sso/user/modify.do")
	public void modifyUser(HttpServletRequest request, HttpServletResponse response, UserBean userBean) {
		log.debug("收到修改用户信息请求");
		PageInfo pageInfo = new PageInfo();

		try {

			userService.modifyUserInfo(userBean);
			pageInfo.setState(SysConst.RESULT_BEAN_STATE_PASS);

		} catch (Exception e) {
			pageInfo.setData(e.getMessage());
		}
		writeJsonData(response, pageInfo);

	}

	@RequestMapping("/sso/user/modifyPwd.do")
	public void modifyUserPwd(HttpServletRequest request, HttpServletResponse response, UserBean userBean) {

		log.debug("收到修改用户密码信息请求");
		PageInfo pageInfo = new PageInfo();

		try {

			userService.modifyUserInfoPwd(userBean);
			pageInfo.setState(SysConst.RESULT_BEAN_STATE_PASS);

		} catch (Exception e) {
			pageInfo.setData(e.getMessage());
		}
		writeJsonData(response, pageInfo);

	}

	@RequestMapping("/sso/user/reset.do")
	public void resetUserPassword(HttpServletRequest request, HttpServletResponse response, UserBean userBean) {
		log.debug("收到重置信息请求");
		PageInfo pageInfo = new PageInfo();

		try {

			userService.resetUser(userBean);
			pageInfo.setState(SysConst.RESULT_BEAN_STATE_PASS);

		} catch (Exception e) {
			pageInfo.setData(e.getMessage());
		}
		writeJsonData(response, pageInfo);

	}

	@RequestMapping("/sso/user/delete.do")
	public void deleteUser(HttpServletRequest request, HttpServletResponse response, UserBean userBean) {
		log.debug("收到删除用户请求");
		PageInfo pageInfo = new PageInfo();

		try {

			userService.deleteUser(userBean);
			pageInfo.setState(SysConst.RESULT_BEAN_STATE_PASS);

		} catch (Exception e) {
			pageInfo.setData(e.getMessage());
		}
		writeJsonData(response, pageInfo);

	}
}
