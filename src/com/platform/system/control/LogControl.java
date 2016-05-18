package com.platform.system.control;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.base.SysConst;
import com.platform.base.SysControl;
import com.platform.bean.PageInfo;
import com.platform.system.bean.LogBean;
import com.platform.system.service.LogService;
import com.platform.tool.HelpFunctions;
import com.platform.tool.HttpUtil;
import com.platform.tool.JsonUtils;
import com.platform.tool.ReadLogFile;


/**
 * 
 * ClassName：LogControl
 * Description：日志管理
 * @author: 刘焕超
 * @date: 2015年5月23日 上午11:40:43
 * note:
 *
 */
@Controller
@Scope("session")
public class LogControl extends SysControl{

	private static final Logger log = LoggerFactory.getLogger(LogControl.class);

	@Autowired
	private LogService service;

	/**
	 * 日志跳转
	 * @param request
	 * @param response
	 * @param url
	 * @return
	 */
	@RequestMapping("/sso/log/index.do")
	public String index(HttpServletRequest request, HttpServletResponse response, String url, String logId) {

		request.setAttribute("logId", HelpFunctions.getStringNotNull(logId));

		return url;
	}

	/**
	 * 获取日志列表
	 * @param request
	 * @param response
	 * @param bean
	 * @param pageInfo
	 */
	@RequestMapping("/sso/log/getList.do")
	public void getList(HttpServletRequest request, HttpServletResponse response, LogBean bean, PageInfo pageInfo) {

		log.debug("收到获取日志列表请求");

		log.debug("query:" + JsonUtils.toJsonStr(bean));
		try {
			
			HelpFunctions.initPageInfoStartEnd(pageInfo);
			List<LogBean> list = service.getList(bean, pageInfo);
			pageInfo.setState(SysConst.RESULT_BEAN_STATE_PASS);
			pageInfo.setObject(list);
			pageInfo.setTotolPage(HelpFunctions.getPage(pageInfo.getPageSize(), pageInfo.getTotolPage()));

		} catch (Exception e) {
			pageInfo.setData(e.getMessage());
		}
		writeJsonData(response, pageInfo);

		log.debug("获取日志列表请求结束");

	}

	/**
	 * 获取日志详细
	 * @param request
	 * @param response
	 * @param logId
	 */
	@RequestMapping("/sso/log/detail.do")
	public void getDetail(HttpServletRequest request, HttpServletResponse response, String logId) {

		log.debug("收到获取日志详细请求");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("result", "false");

		try {

			LogBean bean = service.getLogBeanById(logId);
			map.put("bean", bean);

			map.put("result", "true");
		} catch (Exception e) {
			map.put("data", e.getMessage());
		}

		writeJsonData(response, map);

		log.debug("获取日志详细结束");
	}

	/**
	 * 获取log4j文件列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/sso/log/getFileList.do")
	public void getLog4jFileList(HttpServletRequest request, HttpServletResponse response) {

		log.debug("开始查询日志文件");
		File[] logs = null;
		List<String> fileNames = new ArrayList<String>();

		try {

			String path = System.getProperty(SysConst.SYSTEM_ROOT_PATH);
			logs = new File(path + "logs").listFiles();

		} catch (Exception e) {
			log.error("获取日志文件失败:" + e.getMessage());
			return;
		}
		log.debug("共获取到" + logs.length + "个日志文件");

		try {
			List<File> list = HelpFunctions.FileSort(logs);

			for (File f : list) {
				fileNames.add(f.getName());
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		writeJsonData(response, fileNames);
	}

	/**
	 * 获取log4j日志文件详细内容
	 * @param request
	 * @param response
	 * @param name
	 * @param num
	 * @param charset
	 */
	@RequestMapping("/sso/log/getFileInfo.do")
	public void getFileInfo(HttpServletRequest request, HttpServletResponse response, String logFile, String num,
			String charset) {

		if (HelpFunctions.isEmpty(charset)) {
			charset = "UTF-8";
		}

		Long n = 0L;
		try {
			n = Long.valueOf(num);

		} catch (Exception e) {
			HttpUtil.writeResponseData(response, "行数必须是数字");
			return;
		}

		String path = System.getProperty(SysConst.SYSTEM_ROOT_PATH);
		logFile = path + System.getProperty("file.separator") + "logs" + System.getProperty("file.separator") + logFile;

		try {
			
			HttpUtil.writeResponseData(response, ReadLogFile.read(logFile, charset, n));

		} catch (Exception e) {

			HttpUtil.writeResponseData(response, e.getMessage());

		}
	}

}
