package com.platform.base.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.platform.base.SysConst;

public class RichFreeMarkerView extends FreeMarkerView {

	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
		super.exposeHelpers(model, request);
		model.put(SysConst.VIEW_PATH, request.getContextPath());
	}
}