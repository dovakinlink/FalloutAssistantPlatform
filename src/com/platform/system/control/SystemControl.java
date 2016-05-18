package com.platform.system.control;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.base.SysConfigInfo;
import com.platform.base.SysConst;
import com.platform.base.SysControl;
import com.platform.base.SysException;
import com.platform.tool.HelpFunctions;
import com.platform.tool.JsonUtils;

/**
 * 
 * ClassName：SystemControl
 * Description：系统配置
 * @author: 刘焕超
 * @date: 2015年5月23日 上午11:18:52
 * note:
 *
 */
@Controller
@Scope("session")
public class SystemControl extends SysControl{

	private static Logger log = LoggerFactory.getLogger(SystemControl.class);

	@RequestMapping("/sso/topage.do")
	public String index(HttpServletRequest request, HttpServletResponse response, String url) {

		log.debug("请求跳转信息");
		return url;
	}
	
	@RequestMapping("/sso/sysConfig/getAllConfigInfo.do")
	public void getAllConfigInfo(HttpServletRequest request, HttpServletResponse response) {

		String json = "";
		try {
			json = JsonUtils.toJsonStr(SysConfigInfo.getAllConfigInfo());
			log.debug(json);
			if (HelpFunctions.isEmpty(json)) {
				json = JsonUtils.getResultJson("未获取到XML内容", SysConst.RESULT_BEAN_STATE_FAIL);
			}
		} catch (Exception e) {
			log.error("获取XML内容失败:" + e.getMessage(), e);
			json = JsonUtils.getResultJson("获取XML内容失败", SysConst.RESULT_BEAN_STATE_FAIL);
		}
		writeJsonData(response, json);
	}

	@RequestMapping("/sso/sysConfig/save.do")
	public void saveXmlInfo(HttpServletRequest request, HttpServletResponse response) {

		log.debug("开始保存配置文件信息");
		String json = "";
		@SuppressWarnings("unchecked")
		Enumeration<String> e = request.getParameterNames();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		while (e.hasMoreElements()) {
			HashMap<String, String> map = new HashMap<String, String>();
			String string = (String) e.nextElement();
			map.put("key", string);
			map.put("value", request.getParameter(string));
			log.debug(string + "----->" + request.getParameter(string));
			list.add(map);
		}

		try {
			SysConfigInfo.updateConfigXml(list);
			SysConfigInfo.initAllConfigInfo();
			json = JsonUtils.getResultJson("更新成功", SysConst.RESULT_BEAN_STATE_PASS);
		} catch (SysException e1) {
			json = JsonUtils.getResultJson("获取XML内容失败", SysConst.RESULT_BEAN_STATE_FAIL);
		}
		writeJsonData(response, json);

	}
	
	
}
