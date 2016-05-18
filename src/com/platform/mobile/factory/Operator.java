package com.platform.mobile.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.platform.base.SysException;
import com.platform.mobile.bean.ReqBean;
import com.platform.mobile.bean.ResBean;

/**
 * 
 * ClassName：Operator
 * Description：为客户端业务处理提供接口
 * 
 * @author: 刘焕超
 * @date: 2015-5-19 下午4:50:24
 *        note:
 * 
 */
public abstract class Operator implements InitializingBean {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 * 方法描述：业务操作
	 * 
	 * @author: 刘焕超
	 * @date: 2015-5-19 下午4:50:42
	 * @param reqBean
	 * @return
	 * @throws SysException
	 */
	public abstract ResBean operate(ReqBean reqBean) throws SysException;

	/**
	 * 
	 * 方法描述：获取业务处理类名，此处和终端请求内容中Method对应
	 * 
	 * @author: 刘焕超
	 * @date: 2015-5-19 下午4:50:55
	 * @return
	 */
	protected String getMethod() {
		String[] packages = this.getClass().getName().split("\\.");
		return packages[packages.length - 1].toLowerCase();
	};

	public void afterPropertiesSet() throws Exception {
		OperatorFactory.putOperator(getMethod(), this);
	}

}
