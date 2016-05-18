package com.platform.common.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.platform.bean.PageInfo;
import com.platform.common.dao.CommonDao;

/**
 * 
 * ClassName：CommonDaoImpl
 * Description：通用接口实现
 * @author: 刘焕超
 * @date: 2015-5-18 上午11:36:58
 * note:
 *
 */
@Repository
public class CommonDaoImpl extends HibernateDaoSupport implements CommonDao {

	@Autowired
	private void setHibernateSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public Object create(Object entity) {
		return getHibernateTemplate().save(entity);
	}

	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);

	}

	public void update(Object entity) {
		getHibernateTemplate().update(entity);

	}

	public Object getObjectById(Class<?> c, Serializable id) {
		return getHibernateTemplate().get(c, id);
	}

	public Object getObjectByHQL(final String select, final Object[] values, final PageInfo pageInfo) {
		HibernateCallback<Object> selectCallback = new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(select);
				if (values != null) {
					for (int i = 0; i < values.length; i++)
						query.setParameter(i, values[i]);
				}
				if (pageInfo != null) {
					return query.setFirstResult(pageInfo.getStartPage()).setMaxResults(pageInfo.getEndPage())
							.uniqueResult();
				}
				return query.uniqueResult();
			}
		};
		return getHibernateTemplate().execute(selectCallback);
	}

	public Object getObjectByHQLAsCache(final String select, final Object[] values, final PageInfo pageInfo) {
		HibernateCallback<Object> selectCallback = new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(select);
				query.setCacheable(true);
				if (values != null) {
					for (int i = 0; i < values.length; i++)
						query.setParameter(i, values[i]);
				}
				if (pageInfo != null) {
					return query.setFirstResult(pageInfo.getStartPage()).setMaxResults(pageInfo.getEndPage())
							.uniqueResult();
				}
				return query.uniqueResult();
			}
		};
		return getHibernateTemplate().execute(selectCallback);
	}

	public List<?> getListBySQL(final String select, final Object[] values, final Class<?> c, final PageInfo pageInfo) {
		HibernateCallback<Object> selectCallback = new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) {
				Query query;
				if (c == null)
					query = session.createSQLQuery(select);
				else {
					Map<?, ?> map = session.getSessionFactory().getAllClassMetadata();
					if (map.containsKey(c.getName())) {
						query = session.createSQLQuery(select).addEntity(c);
					} else {
						query = session.createSQLQuery(select).setResultTransformer(Transformers.aliasToBean(c));
					}
				}
				if (values != null) {
					for (int i = 0; i < values.length; i++)
						query.setParameter(i, values[i]);
				}

				if (pageInfo != null) {
					return query.setFirstResult(pageInfo.getStartPage()).setMaxResults(pageInfo.getEndPage()).list();
				}

				return query.list();
			}
		};
		return (List<?>) getHibernateTemplate().execute(selectCallback);
	}

	public List<?> getListByHQL(final String select, final Object[] values, final PageInfo pageInfo) {

		HibernateCallback<Object> selectCallback = new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(select);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				if (pageInfo != null) {

					pageInfo.setTotolPage(query.list().size());
					return query.setFirstResult(pageInfo.getStartPage()).setMaxResults(pageInfo.getPageSize()).list();
				}

				return query.list();
			}
		};
		List<?> list = (List<?>) getHibernateTemplate().execute(selectCallback);
		return list;
	}

	public List<?> getListByHQLByCache(final String select, final Object[] values, final PageInfo pageInfo) {

		HibernateCallback<Object> selectCallback = new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(select);
				query.setCacheable(true);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				if (pageInfo != null) {
					pageInfo.setTotolPage(query.list().size());
					return query.setFirstResult(pageInfo.getStartPage()).setMaxResults(pageInfo.getEndPage()).list();
				}

				return query.list();
			}
		};
		List<?> list = (List<?>) getHibernateTemplate().execute(selectCallback);
		return list;
	}

	public void deleteByHQL(String hsql, String value) {

		Session session = this.getSessionFactory().openSession();

		session.createQuery(hsql).setString("value", value).executeUpdate();

		session.close();

	}
}
