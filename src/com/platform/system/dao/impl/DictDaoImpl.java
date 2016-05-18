package com.platform.system.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.platform.bean.PageInfo;
import com.platform.common.dao.impl.CommonDaoImpl;
import com.platform.system.bean.DictItemBean;
import com.platform.system.bean.LogBean;
import com.platform.system.dao.DictDao;
import com.platform.system.dao.LogDao;
import com.platform.system.entity.DictItemInfo;
import com.platform.system.entity.LogInfo;
import com.platform.tool.HelpFunctions;


/**
 * 
 * ClassName：DictDaoImpl
 * Description：
 * @author: 刘焕超
 * @date: 2015年5月25日 下午7:44:32
 * note:
 *
 */
@Repository
public class DictDaoImpl extends CommonDaoImpl implements DictDao {

	@SuppressWarnings("unchecked")
	public List<DictItemInfo> getList(DictItemBean bean, PageInfo page) {

		List<DictItemInfo> list = new ArrayList<DictItemInfo>();
		StringBuilder whereSql = new StringBuilder("from DictItemInfo where 1=1 ");
		List<Object> params = new ArrayList<Object>();

		if (!HelpFunctions.isEmpty(bean.getDictId())) {

				whereSql.append("and dictId =? ");
				params.add(bean.getDictId());
		}

		whereSql.append(" order by dictId,seq ");
		
		list = (List<DictItemInfo>) getListByHQL(whereSql.toString(), params.toArray(), page);

		return list;

	}

}
