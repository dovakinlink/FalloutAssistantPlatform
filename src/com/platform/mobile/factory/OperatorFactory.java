package com.platform.mobile.factory;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.platform.base.SysException;
import com.platform.mobile.bean.ReqBean;

/**
 * 
 * ClassName：OperatorFactory
 * Description：终端业务处理工厂
 * 
 * @author: 刘焕超
 * @date: 2015-5-19 下午4:52:12
 *        note:
 * 
 */
public class OperatorFactory {

	private static Logger log = LoggerFactory.getLogger(OperatorFactory.class);

	private static final Map<String, Operator> operators = new HashMap<String, Operator>();

	public static void putOperator(String method, Operator operator) {
		operators.put(method, operator);
	}

	/**
	 * 根据请求{@linkplain RequestBean#getMethod()}参数返回对应的业务实现。
	 */
	public static Operator getOperator(ReqBean reqBean) throws SysException {

		log.debug("{}-->开始获取业务处理类{}", reqBean.getClientInfo(), reqBean.getMethod().toLowerCase());

		Operator o = operators.get(reqBean.getMethod().toLowerCase());
		if (o != null) {
			log.debug("{}-->业务处理类获取成功", reqBean.getClientInfo());
			return o;
		} else {
			log.debug("{}-->业务处理类{}获取失败", reqBean.getClientInfo(), reqBean.getMethod().toLowerCase());
			throw new SysException("客户端请求方法不存在。方法名是" + reqBean.getMethod() + "。");
		}
	}
}
