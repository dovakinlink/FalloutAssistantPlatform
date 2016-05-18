package com.platform.system.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.platform.bean.PageInfo;
import com.platform.common.dao.impl.CommonDaoImpl;
import com.platform.system.bean.LogBean;
import com.platform.system.dao.LogDao;
import com.platform.system.entity.LogInfo;
import com.platform.tool.HelpFunctions;


/**
 * 
 * ClassName：LogDaoImpl
 * Description：日志处理
 * @author: 刘焕超
 * @date: 2015年5月23日 上午11:45:54
 * note:
 *
 */
@Repository
public class LogDaoImpl extends CommonDaoImpl implements LogDao {

	@SuppressWarnings("unchecked")
	public List<LogInfo> getList(LogBean bean, PageInfo page) {

		List<LogInfo> list = new ArrayList<LogInfo>();
		StringBuilder whereSql = new StringBuilder("from LogInfo where 1=1 ");
		List<Object> params = new ArrayList<Object>();

		if (!HelpFunctions.isEmpty(bean.getTimeStart())) {

			Date date = HelpFunctions.strTodate(bean.getTimeStart());
			if (date != null) {
				whereSql.append("and createTime >=? ");
				params.add(date);
			}
		}

		if (!HelpFunctions.isEmpty(bean.getTimeEnd())) {
			Date date = HelpFunctions.strTodate(bean.getTimeEnd());
			if (date != null) {
				whereSql.append("and createTime <=? ");
				params.add(date);
			}
		}

		if (!HelpFunctions.isEmpty(bean.getType()) && !bean.getType().equals("00")) {
			whereSql.append("and type =? ");
			params.add(bean.getType());
		}

		if (!HelpFunctions.isEmpty(bean.getClientId())) {
			whereSql.append("and clientId =? ");
			params.add(bean.getClientId());
		}
		
		whereSql.append(" order by createTime desc ");
		
		list = (List<LogInfo>) getListByHQL(whereSql.toString(), params.toArray(), page);

		return list;

	}

}
