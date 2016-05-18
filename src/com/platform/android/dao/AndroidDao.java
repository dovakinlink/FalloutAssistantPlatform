package com.platform.android.dao;

import java.util.List;

import com.platform.android.entity.CodeInfo;
import com.platform.android.entity.NewsBean;
import com.platform.bean.PageInfo;
import com.platform.common.dao.CommonDao;

public interface AndroidDao extends CommonDao{

	/**
	 * 获取正文列表
	 * @return
	 */
	public List<NewsBean> getNewsList(PageInfo page);
	
	/**
	 * 
	 * @param codeInfoList
	 */
	public void insertCodes(List<CodeInfo> codeInfoList);
	
	public List<CodeInfo> searchCodesByName(String name);
	
	public List<CodeInfo> searchCodesByCode(String code);
}
