package com.platform.mobile.api;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.base.SysConst;
import com.platform.base.SysControl;
import com.platform.base.SysException;
import com.platform.mobile.bean.ReqBean;
import com.platform.mobile.bean.ResBean;
import com.platform.mobile.bean.ResultBean;
import com.platform.mobile.factory.OperatorFactory;
import com.platform.tool.JsonUtils;

/**
 * 
 * ClassName：MobileHttpApi
 * Description：终端HTTP接口,返回格式为JSON
 * 
 * @author: 刘焕超
 * @date: 2015-5-19 上午11:08:07
 *        note:
 * 
 */
@Controller
public class MobileHttpApi extends SysControl {

	private static Logger log = LoggerFactory.getLogger(MobileHttpApi.class);

	@RequestMapping("/mobile/http/api.do")
	public void checkUserInfo(HttpServletRequest request, HttpServletResponse response, ReqBean reqBean) {

		// 客户端信息
		String clientInfo = request.getRemoteHost() + ":" + request.getRemotePort();
		reqBean.setClientInfo(clientInfo);

		// 响应信息
		ResBean resBean = new ResBean();
		
		try {
			log.info("收到[" + reqBean.getClientInfo() + "]用户请求");
			String reqestContent = getRequestDataString(request, reqBean);

			log.debug("{}-->请求内容：{}", reqBean.getClientInfo(), reqestContent);
			
			//请求内容中存在Object对象，SpringMVC不能将不固定参数进行转换，此处用JSON进行转换
			ReqBean rq = JsonUtils.readValue(reqestContent, ReqBean.class);
			
			BeanUtils.copyProperties(rq, reqBean);
			BeanUtils.copyProperties(rq, resBean);
			
			resBean = OperatorFactory.getOperator(reqBean).operate(reqBean);

		} catch (SysException e) {
			log.error("{}-->业务处理失败:{}", reqBean.getClientInfo(), e.getMessage());

			ResultBean rb = new ResultBean();
			rb.setInfo(e.getMessage());
			resBean.setResult(rb);

		}

		writeJsonData(response, resBean);
	}

	/**
	 * 
	 * 方法描述：读取请求数据
	 * @author: 刘焕超
	 * @date: 2015年5月21日 下午2:19:34
	 * @param request
	 * @param reqBean
	 * @return
	 * @throws SysException
	 */
	private String getRequestDataString(HttpServletRequest request, ReqBean reqBean) throws SysException {

		String retData = "";
		/**
		 * 接收客户端数据。
		 */
		BufferedInputStream bin = null;
		try {

			@SuppressWarnings("unchecked")
			Enumeration<String> headers = request.getHeaderNames();
			while (headers.hasMoreElements()) {
				String header = headers.nextElement();
				log.debug("{}:{}-->{}", reqBean.getClientInfo(), header, request.getHeader(header));
			}

			InputStream in = request.getInputStream();
			bin = new BufferedInputStream(in);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];

			int num = 0;
			while ((num = bin.read(buf)) != -1) {
				baos.write(buf, 0, num);
			}

			bin.close();
			bin = null;
			retData = new String(baos.toByteArray(), SysConst.UTF8);

			if(StringUtils.isBlank(retData)){
				throw new SysException("请求内容不能为空");
			}
		} catch (IOException e) {
			log.error("获取请求原始数据出错。", e);
			throw new SysException("获取请求原始数据出错。");
		} finally {
			try {
				if (bin != null) {
					bin.close();
				}
			} catch (Exception e2) {
			}
		}

		return retData;

	}

}
