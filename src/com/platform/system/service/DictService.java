package com.platform.system.service;

import java.util.List;

import com.platform.base.SysException;
import com.platform.bean.PageInfo;
import com.platform.system.bean.DictItemBean;

/**
 * 
 * ClassName：DictService
 * Description：字典接口
 * 
 * @author: 刘焕超
 * @date: 2015年5月25日 下午7:47:44
 *        note:
 *
 */
public interface DictService {

	/**
	 * 
	 * 方法描述：添加字典项
	 * 
	 * @author: 刘焕超
	 * @date: 2015年5月25日 下午7:49:51
	 * @param bean
	 * @throws SysException
	 */
	public void addDictItem(DictItemBean bean) throws SysException;

	/**
	 * 
	 * 方法描述：删除字典项
	 * 
	 * @author: 刘焕超
	 * @date: 2015年5月25日 下午7:50:01
	 * @param bean
	 * @throws SysException
	 */
	public void deleteDictItem(DictItemBean bean) throws SysException;

	/**
	 * 
	 * 方法描述：修改字典项
	 * 
	 * @author: 刘焕超
	 * @date: 2015年5月25日 下午7:50:09
	 * @param bean
	 * @throws SysException
	 */
	public void modifyDictItem(DictItemBean bean) throws SysException;

	/**
	 * 
	 * 方法描述：查询字典项
	 * 
	 * @author: 刘焕超
	 * @date: 2015年5月25日 下午7:50:17
	 * @param bean
	 * @param page
	 * @return
	 * @throws SysException
	 */
	public List<DictItemBean> getList(DictItemBean bean, PageInfo page) throws SysException;

	/**
	 * 
	 * 方法描述：根据字典项ID获取字典项信息
	 * @author: 刘焕超
	 * @date: 2015年5月25日 下午11:06:01
	 * @param bean
	 * @return
	 * @throws SysException
	 */
	public DictItemBean getDictItemBeanById(DictItemBean bean) throws SysException;
}
