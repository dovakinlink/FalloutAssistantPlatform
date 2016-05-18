package com.platform.base;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.platform.tool.JsonUtils;

/**
 * 
 * ClassName：SysControl
 * Description：系统控制类 
 * 
 * @author: 刘焕超
 * @date: 2015-5-18 下午2:34:40
 *        note:
 * 
 */
public class SysControl {

	private static final Logger log = LoggerFactory.getLogger(SysControl.class);

	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

	/**
	 * 
	 * 方法描述：返回响应信息,供业务类使用
	 * @author: 刘焕超
	 * @date: 2015-5-19 下午4:44:22
	 * @param response
	 * @param obj
	 */
	public static void writeJsonData(HttpServletResponse response, Object obj) {

		response.setContentType(CONTENT_TYPE);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		try {
			String json = JsonUtils.toJsonStr(obj);
			log.debug("json:" + json);
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			log.error("向HttpServletResponse中写数据异常", e);
		}
	}
}
