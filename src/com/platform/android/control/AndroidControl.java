package com.platform.android.control;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.platform.android.bean.SearchCodeRequest;
import com.platform.android.bean.TestBean;
import com.platform.android.entity.CodeInfo;
import com.platform.android.entity.NewsBean;
import com.platform.android.service.AndroidService;
import com.platform.base.SysException;
import com.platform.bean.PageInfo;
import com.platform.tool.HelpFunctions;
import com.platform.tool.HttpClientUtil;

@Controller
@Scope("session")
public class AndroidControl {
	
	private static final Logger log = LoggerFactory.getLogger(AndroidControl.class);
	private static final String ERROR_CODE = "";
	
	@Autowired
	private AndroidService service;
	
	@RequestMapping("/service/searchcode.do")
	@ResponseBody
	public String searchCode(HttpServletRequest request, HttpServletResponse response){

		String result = "";
		List<CodeInfo> list = null;
		try{
			
			String requestStr = getRequestDataString(request);
			SearchCodeRequest searchCode = new SearchCodeRequest();
			
			JsonElement je = new JsonParser().parse(requestStr);
			searchCode.setContent(je.getAsJsonObject().get("content").getAsString());
			
			list = service.searchCodes(searchCode);
			if(!HelpFunctions.isEmpty(list)){
				result = new Gson().toJsonTree(list).toString();
				return result;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ERROR_CODE;
		
	}
	
	
	@RequestMapping("/service/getnews.do")
	@ResponseBody
	public String getNews(HttpServletRequest request, HttpServletResponse response, PageInfo page){
		String result = "";
		try{
		
			List<NewsBean> list = service.getNewsList(page);
			
			if(!HelpFunctions.isEmpty(list)){
				result = new Gson().toJsonTree(list).toString();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 原始数据解析并入库接口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/service/initdata.do")
	@ResponseBody
	public String initData(HttpServletRequest request, HttpServletResponse response){
	        File file = new File("d:\\AMMO CODES.txt");
	        BufferedReader reader = null;
	        
	        List<CodeInfo> codeInfoList = new ArrayList<CodeInfo>();

	        try{
	            reader = new BufferedReader(new FileReader(file));
	            String tempStr = null;
	            int line = 1;
	            while((tempStr = reader.readLine()) != null){
	                System.out.println("line" + line + ":" + tempStr);
	                String[] tempStruct = tempStr.split("\\–");
	                
	                String codeName = "";
	                String codeValue = "";
	                if(tempStruct.length > 2){
	                	StringBuilder sb = new StringBuilder("");
	                	for(int i = 0; i < tempStruct.length - 1; i++){
	                		sb.append(tempStruct[i]);
	                	}
	                	codeName = sb.toString();
	                	codeValue = tempStruct[tempStruct.length - 1].trim();
	                } else {
	                	codeName = tempStruct[0];
	                	codeValue = tempStruct[1].trim();
	                }
	                
	                CodeInfo ci = new CodeInfo();
	                ci.setC_code_name(codeName);
	                ci.setC_code_value(codeValue);
	                ci.setC_sys_type("0");
	                ci.setC_code_type_id("003");
	                ci.setC_code_type_des("AMMO CODES");
	                
	                codeInfoList.add(ci);
	                
	                line++;
	            }
	            reader.close();
	            
	            service.insertCodes(codeInfoList);
	        }catch (IOException e){

	        }
	        
	        return null;
	}
	
	/**
	 * 
	 * 方法描述：读取请求数据
	 * 
	 * @author: 刘焕超
	 * @date: 2015年5月21日 下午2:19:34
	 * @param request
	 * @param reqBean
	 * @return
	 * @throws SysException
	 */
	private String getRequestDataString(HttpServletRequest request) throws SysException {

		String retData = "";
		/**
		 * 接收客户端数据。
		 */
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

			bin.close();
			bin = null;
			retData = new String(baos.toByteArray(), HttpClientUtil.UTF8);
			log.info("---------------------------------" + retData);
			if (StringUtils.isBlank(retData)) {
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
