package com.platform.android.service;

import java.util.List;

import com.platform.android.bean.SearchCodeRequest;
import com.platform.android.entity.CodeInfo;
import com.platform.android.entity.NewsBean;
import com.platform.bean.PageInfo;

public interface AndroidService {

	public List<NewsBean> getNewsList(PageInfo page);
	
	public List<CodeInfo> searchCodes(SearchCodeRequest searchCode);
	
	public void insertCodes(List<CodeInfo> codeInfoList);
		
}
