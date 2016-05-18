package com.platform.android.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.android.bean.TestBean;


@Controller
@Scope("session")
public class AndroidTestControl {

	private static final Logger log = LoggerFactory.getLogger(AndroidTestControl.class);

	@RequestMapping("/test/test.do")
	public void testHttp(HttpServletRequest request, HttpServletResponse response, TestBean bean){
		TestBean ub = bean;
		ub.getName();
		return;
	}
}
