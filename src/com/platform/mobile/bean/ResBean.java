package com.platform.mobile.bean;

/**
 * 
 * ClassName：reqBean
 * Description：响应信息
 * 
 * @author: 刘焕超
 * @date: 2015-5-19 上午10:39:02
 *        note:
 * 
 */
public class ResBean extends BaseBean {

	private static final long serialVersionUID = 4742222694931896552L;
	/**
	 * 返回结果
	 */
	private ResultBean result;

	public ResultBean getResult() {
		return result;
	}

	public void setResult(ResultBean result) {
		this.result = result;
	}

}
