package com.platform.base;

import java.util.HashMap;

/**
 * 
 * ClassName：DictConst
 * Description：字典常量
 * 
 * @author: 刘焕超
 * @date: 2015-5-18 上午10:53:19
 *        note:
 * 
 */
public interface DictConst {
	
	public final static String RESBEAN_SUCCESS="1";
	public final static String RESBEAN_ERROR="0";

	public final static String DELETE_YES = "0";
	public final static String DELETE_NO = "1";

	public final static HashMap<String, String> LOGTYPE = new HashMap<String, String>() {

		private static final long serialVersionUID = 4091643361151770275L;

		{
			put("1", "test");
		}

	};
}
