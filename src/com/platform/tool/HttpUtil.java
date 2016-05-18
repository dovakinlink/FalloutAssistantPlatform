package com.platform.tool;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * http工具类
 * @author 刘焕超
 * 2014-10-22 下午4:32:19
 */
public class HttpUtil {

	private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * 读取http请求内容
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String receiveData(HttpServletRequest request) throws Exception {

		String retData = "";

		BufferedInputStream bin = null;
		try {
			InputStream in = request.getInputStream();
			bin = new BufferedInputStream(in);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];

			int num = 0;
			while ((num = bin.read(buf)) != -1) {
				baos.write(buf, 0, num);
			}

			retData = new String(baos.toByteArray(), "utf-8");

		} catch (Exception e) {
			log.error("接收数据异常:" + e.getMessage(), e);
		} finally {
			if (bin != null) {
				bin.close();
			}

		}

		return retData;
	}

	/**
	 * 发送http响应内容
	 * @param response
	 * @param text
	 */
	public static void writeResponseData(HttpServletResponse response, String text) {

		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		try {

			response.getWriter().write(text);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			log.error("向HttpServletResponse中写数据异常", e);
		}
	}
}
