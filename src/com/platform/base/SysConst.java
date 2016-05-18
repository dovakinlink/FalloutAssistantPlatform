package com.platform.base;

/**
 * 
 * ClassName：SysConst
 * Description：系统常量
 * @author: 刘焕超
 * @date: 2015-5-18 上午11:25:48
 * note:
 *
 */
public interface SysConst {

	/**
	 * 系统配置文件路径
	 */
	public static final String CONFIG_XML_PATH = "/WEB-INF/classes/config.xml";

	/**
	 * 页面上下文路径
	 */
	public static final String VIEW_PATH = "path";
	/**
	 * 日期格式 ：yyyyMM
	 */
	public static final String DATE_FORMAT_YYYYMM = "yyyyMM";
	/**
	 * 日期格式 ：yyyyMMdd
	 */
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	/**
	 * 日期格式 :yyyyMMddHHmmss
	 */
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	/**
	 * 日期格式 : yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期格式 : yyyy-MM-dd 
	 */
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	/**
	 * 请求返回结果 成功
	 */
	public static final String RESULT_BEAN_STATE_PASS = "1";

	/**
	 * 请求返回结果	失败
	 */
	public static final String RESULT_BEAN_STATE_FAIL = "0";

	/**
	 * 系统主页面
	 */
	public static final String SERVER_MAIN_PAGE = "main/main";

	/**
	 * 登录URL,用于未登录时重定向
	 */
	public static final String LOGIN_URL = "/sso/login/index.do";

	/**
	 * session中存储的用户信息key
	 */
	public static final String SESSION_USER_NAME = "sysUserSessionInfo";

	/**
	 * 删除标志 删除
	 */
	public static final String DICT_DELETE_YES = "0";

	/**
	 * 删除标志 未删除
	 */
	public static final String DICT_DELETE_NO = "1";

	/**
	 * 系统版本号
	 */
	public static final String SYSTEM_VERSION = "system_version";

	/**
	 * 系统根路径存储名称
	 */
	public static final String SYSTEM_ROOT_PATH = "platform.root";

	/**
	 * 系统用户默认密码
	 * 111111
	 */
	public static final String SYSTEM_DEFAULT_PASSWORD = "96e79218965eb72c92a549dd5a330112";

	/**
	 * UTF-8编码
	 */
	public static final String UTF8 = "UTF-8";
	
}
