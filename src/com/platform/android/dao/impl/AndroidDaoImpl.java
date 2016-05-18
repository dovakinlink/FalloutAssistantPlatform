package com.platform.android.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.platform.android.dao.AndroidDao;
import com.platform.android.entity.CodeInfo;
import com.platform.android.entity.NewsBean;
import com.platform.bean.PageInfo;
import com.platform.common.dao.impl.CommonDaoImpl;

@Repository
public class AndroidDaoImpl extends CommonDaoImpl implements AndroidDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsBean> getNewsList(PageInfo page) {
		
		List<NewsBean> list = new ArrayList<NewsBean>();
		
		StringBuilder whereSql = new StringBuilder("from NewsBean where 1=1");
		List<Object> params = new ArrayList<Object>();
		
		list = (List<NewsBean>) getListByHQL(whereSql.toString(), params.toArray(),page);
		
		return list;
	}

	@Override
	@Transactional
	public void insertCodes(List<CodeInfo> codeInfoList) {
		
		try{
			for(CodeInfo codeInfo : codeInfoList){
				create(codeInfo);
			}
		}catch(Exception e){
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeInfo> searchCodesByName(String name) {
		
		try{
			List<CodeInfo> list = new ArrayList<CodeInfo>();
			
			StringBuilder whereSql = new StringBuilder("from CodeInfo where 1=1 ");
			
			List<Object> params = new ArrayList<Object>();
			
			params.add("%" + name + "%");
			whereSql.append("and c_code_name like ?");
			
			list = (List<CodeInfo>) getListByHQL(whereSql.toString(),params.toArray(),
					null);
			
			return list;
			
		}catch(Exception e){}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeInfo> searchCodesByCode(String code) {
		try{
			List<CodeInfo> list = new ArrayList<CodeInfo>();
			
			StringBuilder whereSql = new StringBuilder("from CodeInfo where 1=1");
			
			List<Object> params = new ArrayList<Object>();
			
			params.add(code);
			whereSql.append("and c_code_value like ?");
			
			list = (List<CodeInfo>) getListByHQL(whereSql.toString(),params.toArray(),
					null);
			
			return list;
			
		}catch(Exception e){}
		
		return null;
	}

}
