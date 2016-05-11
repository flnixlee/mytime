package com.lift.framework.entity;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * 查询模板
 * 
 * @author WuNan2 2012-12-21
 *
 */
public class EntityQuery<T> {
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private Query query;
	private SQLQuery sqlQuery;
	private Criteria criteria;
	private Example example;
	private Class<T> clazz;

	public EntityQuery(SessionFactory sessionFactory, Query query) {
		this.sessionFactory = sessionFactory;
		this.query = query;
	}

	public EntityQuery(SessionFactory sessionFactory, SQLQuery sqlQuery) {
		this.sessionFactory = sessionFactory;
		this.sqlQuery = sqlQuery;
	}

	public EntityQuery(SessionFactory sessionFactory, Criteria criteria) {
		this.sessionFactory = sessionFactory;
		this.criteria = criteria;
	}

	public EntityQuery(SessionFactory sessionFactory, Example example, Class<T> clazz) {
		this.sessionFactory = sessionFactory;
		this.example = example;
		this.clazz = clazz;
	}

	/**
	 * 返回单个结果
	 * 
	 * @return
	 */
	public Object uniqueResult() {
		if (null != query) {
			return query.uniqueResult();
		} else if (null != sqlQuery) {
			return sqlQuery.uniqueResult();
		} else if (null != criteria) {
			return criteria.uniqueResult();
		}
		return getSession().createCriteria(clazz).add(example).uniqueResult();
	}

	/**
	 * 返回查询结果集
	 * 
	 * @return
	 */
	public List<?> query() {
		if (null != query) {
			return query.list();
		} else if (null != sqlQuery) {
			return sqlQuery.list();
		} else if (null != criteria) {
			return criteria.list();
		}
		return getSession().createCriteria(clazz).add(example).list();
	}

	public EntityQuery<T> setParameter(String parameterName, Object obj) {
		Query q = (query != null ? query : sqlQuery != null ? sqlQuery : null);
		if (null != q)
			q.setParameter(parameterName, obj);
		return this;
	}

	public EntityQuery<T> setParameter(int index, Object obj) {
		Query q = (query != null ? query : sqlQuery != null ? sqlQuery : null);
		if (null != q)
			q.setParameter(index, obj);
		return this;
	}

	public EntityQuery<T> addEntity(Class<T> clazz) {
		sqlQuery.addEntity(clazz);
		return this;
	}

	public EntityQuery<T> addEntity(String entityName, Class<T> clazz) {
		sqlQuery.addEntity(entityName, clazz);
		return this;
	}
}
