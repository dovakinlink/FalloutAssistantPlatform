package com.platform.android.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.android.bean.SearchCodeRequest;
import com.platform.android.dao.AndroidDao;
import com.platform.android.entity.CodeInfo;
import com.platform.android.entity.NewsBean;
import com.platform.android.service.AndroidService;
import com.platform.bean.PageInfo;
import com.platform.tool.HelpFunctions;

@Service
public class AndroidServiceImpl implements AndroidService {
	
	private static final Logger log = LoggerFactory.getLogger(AndroidServiceImpl.class);

	@Autowired
	private AndroidDao dao;
	
	@Override
	public List<NewsBean> getNewsList(PageInfo page) {

		List<NewsBean> list = dao.getNewsList(page);
		
		return list;
	}

	@Override
	public void insertCodes(List<CodeInfo> codeInfoList) {
		if(HelpFunctions.isEmpty(codeInfoList)) return;
		dao.insertCodes(codeInfoList);
	}

	@Override
	public List<CodeInfo> searchCodes(SearchCodeRequest searchCode) {
		
		String content = searchCode.getContent();
		List<CodeInfo> list = null;
		
		list = dao.searchCodesByName(content);
		
		if(HelpFunctions.isEmpty(list)){
			list = dao.searchCodesByCode(content);
		}
		
		return list;
	}

}
