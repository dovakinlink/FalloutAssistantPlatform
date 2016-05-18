package com.platform.system.control;

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
import com.platform.system.bean.DictItemBean;
import com.platform.system.service.DictService;
import com.platform.tool.HelpFunctions;
import com.platform.tool.JsonUtils;

/**
 * 
 * ClassName：DictControl
 * Description：字典管理
 * 
 * @author: 刘焕超
 * @date: 2015年5月25日 下午8:00:15
 *        note:
 *
 */
@Controller
@Scope("session")
public class DictControl extends SysControl {

	private static final Logger log = LoggerFactory.getLogger(DictControl.class);

	@Autowired
	private DictService service;

	@RequestMapping("/sso/dict/index.do")
	public String index(HttpServletRequest request, HttpServletResponse response, String url, DictItemBean bean) {

		request.setAttribute("dictItemInfo", bean);

		return url;
	}

	/**
	 * 
	 * 方法描述：获取字典项列表
	 * 
	 * @author: 刘焕超
	 * @date: 2015年5月25日 下午8:06:51
	 * @param request
	 * @param response
	 * @param bean
	 * @param pageInfo
	 */
	@RequestMapping("/sso/dict/getList.do")
	public void getList(HttpServletRequest request, HttpServletResponse response, DictItemBean bean, PageInfo pageInfo) {

		log.debug("收到获取字典项列表请求");

		log.debug("query:" + JsonUtils.toJsonStr(bean));
		try {

			HelpFunctions.initPageInfoStartEnd(pageInfo);
			List<DictItemBean> list = service.getList(bean, pageInfo);
			pageInfo.setState(SysConst.RESULT_BEAN_STATE_PASS);
			pageInfo.setObject(list);
			pageInfo.setTotolPage(HelpFunctions.getPage(pageInfo.getPageSize(), pageInfo.getTotolPage()));

		} catch (Exception e) {
			pageInfo.setData(e.getMessage());
		}
		writeJsonData(response, pageInfo);

		log.debug("获取字典项列表请求结束");

	}

	/**
	 * 
	 * 方法描述：保存字典项
	 * 
	 * @author: 刘焕超
	 * @date: 2015年5月25日 下午8:06:43
	 * @param request
	 * @param response
	 * @param bean
	 * @param pageInfo
	 */
	@RequestMapping("/sso/dict/saveDictItem.do")
	public void saveDictItem(HttpServletRequest request, HttpServletResponse response, DictItemBean bean,
			PageInfo pageInfo) {

		log.debug("收到保存字典项请求");

		log.debug("query:" + JsonUtils.toJsonStr(bean));
		try {

			service.addDictItem(bean);
			pageInfo.setState(SysConst.RESULT_BEAN_STATE_PASS);

		} catch (Exception e) {
			pageInfo.setData(e.getMessage());
		}
		writeJsonData(response, pageInfo);

		log.debug("保存字典项结束");
	}

	/**
	 * 
	 * 方法描述：删除字典项
	 * 
	 * @author: 刘焕超
	 * @date: 2015年5月25日 下午8:06:43
	 * @param request
	 * @param response
	 * @param bean
	 * @param pageInfo
	 */
	@RequestMapping("/sso/dict/deleteDictItem.do")
	public void deleteDictItem(HttpServletRequest request, HttpServletResponse response, DictItemBean bean,
			PageInfo pageInfo) {

		log.debug("收到删除字典项请求");

		log.debug("query:" + JsonUtils.toJsonStr(bean));
		try {

			service.deleteDictItem(bean);
			pageInfo.setState(SysConst.RESULT_BEAN_STATE_PASS);

		} catch (Exception e) {
			pageInfo.setData(e.getMessage());
		}
		writeJsonData(response, pageInfo);

		log.debug("删除字典项结束");
	}

	/**
	 * 
	 * 方法描述：修改字典项
	 * 
	 * @author: 刘焕超
	 * @date: 2015年5月25日 下午8:06:43
	 * @param request
	 * @param response
	 * @param bean
	 * @param pageInfo
	 */
	@RequestMapping("/sso/dict/modifyDictItem.do")
	public void modifyDictItem(HttpServletRequest request, HttpServletResponse response, DictItemBean bean,
			PageInfo pageInfo) {

		log.debug("收到编辑字典项请求");

		log.debug("query:" + JsonUtils.toJsonStr(bean));
		try {

			service.modifyDictItem(bean);
			pageInfo.setState(SysConst.RESULT_BEAN_STATE_PASS);

		} catch (Exception e) {
			pageInfo.setData(e.getMessage());
		}
		writeJsonData(response, pageInfo);

		log.debug("编辑字典项结束");
	}
	
	/**
	 * 
	 * 方法描述：根据字典项ID获取字典项详情
	 * @author: 刘焕超
	 * @date: 2015年5月25日 下午11:09:15
	 * @param request
	 * @param response
	 * @param bean
	 * @param pageInfo
	 */
	@RequestMapping("/sso/dict/getDictItemById.do")
	public void getDictItemById(HttpServletRequest request, HttpServletResponse response, DictItemBean bean,
			PageInfo pageInfo) {

		log.debug("收到获取字典项详情请求");

		log.debug("query:" + JsonUtils.toJsonStr(bean));
		try {

			DictItemBean b = service.getDictItemBeanById(bean);
			pageInfo.setState(SysConst.RESULT_BEAN_STATE_PASS);
			pageInfo.setObject(b);
			
		} catch (Exception e) {
			pageInfo.setData(e.getMessage());
		}
		writeJsonData(response, pageInfo);

		log.debug("获取字典项详情结束");
	}

}
