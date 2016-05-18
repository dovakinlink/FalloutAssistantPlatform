package com.platform.base;

/**
 * 异常类
 * @author 刘焕超
 * 2014-9-11 上午11:47:03
 */
public class SysException extends Exception {

	private static final long serialVersionUID = 2295129033357234705L;

	public SysException(String error) {
		super(error);
	}
}
