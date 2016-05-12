package com.lift.framework.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lift.framework.entity.EntityBean;
import com.lift.framework.entity.EntityPageQuery;
import com.lift.framework.entity.EntityQuery;

/**
 * Hibernate实现的Dao类 力求实体对象Dao类没有特殊要求不需要添加任何代码
 * 
 * @author WuNan2 2012-12-18
 * 
 * @param <T>
 */
@SuppressWarnings("rawtypes")
@Repository(value = "dao")
public class HibernateDaoImpl<T> implements Dao<T> {

	// 批量处理数量
	private static int BATCH_SIZE = 30;

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}

	public T getById(Serializable id, Class<T> clazz) {
		return (T) getSession().get(clazz, id);
	}

	@Deprecated
	public T saveOrUpdate(T t) {
		getSession().saveOrUpdate(updateBean(t));
		return t;
	}

	public T save(T t) {
		getSession().save(updateBean(t));
		return t;
	}

	public T update(T t) {
		getSession().update(updateBean(t));
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<T> queryByHql(String hql, Object... args) {
		Query query = getSession().createQuery(hql);
		applyParameter(query, args);
		return query.list();
	}

	@SuppressWarnings("unchecked")

	public List<T> queryByExample(Example example, Class<T> clazz) {
		return getSession().createCriteria(clazz).add(example).list();
	}

	@SuppressWarnings("unchecked")

	public List<T> queryByCriteria(Criteria criteria) {
		return criteria.list();
	}

	@SuppressWarnings("unchecked")

	public List<Object[]> queryBySql(String sql, Object... args) {
		Query query = getSession().createSQLQuery(sql);
		applyParameter(query, args);

		return query.list();
	}

	public Object uniqueObjectBySql(String hql, Object... args) {
		Query query = getSession().createSQLQuery(hql);
		applyParameter(query, args);

		return query.uniqueResult();
	}

	@SuppressWarnings("unchecked")

	public T uniqueRuesultByHql(String hql, Object... args) {
		Query query = getSession().createQuery(hql);
		applyParameter(query, args);

		return (T) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(Class<T> clazz) {
		return getSession().createCriteria(clazz).list();
	}

	public Criteria createCriteria(Class<T> clazz) {
		return getSession().createCriteria(clazz);
	}

	public void delete(T t) {
		if (t instanceof EntityBean) {
			((EntityBean) t).setEnable(false);
		}
		getSession().update(updateBean(t));
	}

	public void deleteById(Serializable id, Class<T> clazz) {
		T t = (T) getSession().get(clazz, id);
		if (t instanceof EntityBean) {
			((EntityBean) t).setEnable(false);
		}
		getSession().update(updateBean(t));
	}

	public Iterable<T> batchSave(Iterable<T> iterable) {
		Session session = getSession();
		Iterator<T> it = iterable.iterator();
		for (int i = 0; it.hasNext(); i++) {
			session.save(updateBean(it.next()));
			if (i % BATCH_SIZE == 0) {
				session.flush();
				session.clear();
			}
		}
		return iterable;
	}

	public Iterable<T> batchSaveOrUpdate(Iterable<T> iterable) {
		Session session = getSession();
		Iterator<T> it = iterable.iterator();
		for (int i = 0; it.hasNext(); i++) {
			session.saveOrUpdate(updateBean(it.next()));
			if (i % BATCH_SIZE == 0) {
				session.flush();
				session.clear();
			}
		}
		return iterable;
	}

	public Iterable<T> batchDelete(Iterable<T> iterable) {
		Session session = getSession();
		Iterator<T> it = iterable.iterator();
		for (int i = 0; it.hasNext(); i++) {
			T t = updateBean(it.next());
			if (t instanceof EntityBean) {
				((EntityBean) t).setEnable(false);
			}
			session.update(t);
			if (i % BATCH_SIZE == 0) {
				session.flush();
				session.clear();
			}
		}
		return iterable;
	}

	public Iterable<T> batchUpdate(Iterable<T> iterable) {
		Session session = getSession();
		Iterator<T> it = iterable.iterator();
		for (int i = 0; it.hasNext(); i++) {
			session.update(updateBean(it.next()));
			if (i % BATCH_SIZE == 0) {
				session.flush();
				session.clear();
			}
		}
		return iterable;
	}

	private void applyParameter(Query query, Object... args) {
		if (null != args && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
	}

	public EntityQuery<T> createQueryModelByHql(String hql, Object... args) {
		Query query = getSession().createQuery(hql);
		applyParameterPageQueryModelByHql(query, args);

		return new EntityQuery<T>(sessionFactory, query);
	}

	public EntityQuery<T> createQueryModelBySql(String sql, Object... args) {
		SQLQuery query = getSession().createSQLQuery(sql);
		applyParameter(query, args);

		return new EntityQuery<T>(sessionFactory, query);
	}

	public EntityQuery<T> createQueryModelByCriteria(Criteria criteria) {
		return new EntityQuery<T>(sessionFactory, criteria);
	}

	public EntityQuery<T> createQueryModelByExample(Example example, Class<T> clazz) {
		return new EntityQuery<T>(sessionFactory, example, clazz);
	}

	public EntityPageQuery<T> createPageQueryModelByHql(String hql, Object... args) {
		Query query = getSession().createQuery(hql);
		applyParameterPageQueryModelByHql(query, args);

		String totalSql = hql.replaceFirst("^((select.*\\s+from)|(from))", "select count(*) from");
		Query totalQuery = getSession().createQuery(totalSql);
		applyParameterPageQueryModelByHql(totalQuery, args);

		return new EntityPageQuery<T>(sessionFactory, query, totalQuery);
	}

	/**
	 * 参数设置判断 args中参数为List的做特殊处理只能够允许一个list对象
	 * 
	 * @param query
	 * @param args
	 */
	private void applyParameterPageQueryModelByHql(Query query, Object... args) {
		if (null != args && args.length > 0) {
			int listCount = 0;
			for (int i = 0; i < args.length; i++) {
				// 判断参数类型是List
				if (args[i] instanceof List) {
					List list = (List) args[i];
					if (list != null && list.size() > 0) {
						listCount = list.size();
						// 循环设置参数
						for (int t = 0; t < list.size(); t++) {
							query.setParameter(i + t, list.get(t));
						}
					}
				} else {
					query.setParameter(i + listCount, args[i]);
				}
			}
		}
	}

	public EntityPageQuery<T> createPageQueryModelBySql(String sql, Object... args) {
		SQLQuery query = getSession().createSQLQuery(sql);
		applyParameter(query, args);
		String totalSql = sql.replaceFirst("^((select .*\\s+from)|(from)|(select *.*\\s+from))", "select count(*) from");
		SQLQuery totalQuery = getSession().createSQLQuery(totalSql);
		applyParameter(totalQuery, args);

		return new EntityPageQuery<T>(sessionFactory, query, totalQuery);
	}

	public EntityPageQuery<T> createPageQueryModelBySql(String sql, Class clazz, Object... args) {
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(clazz);
		applyParameter(query, args);
		String totalSql = sql.replaceFirst("^((select .*\\s+from)|(from)|(select *.*\\s+from))", "select count(*) from");
		SQLQuery totalQuery = getSession().createSQLQuery(totalSql);
		applyParameter(totalQuery, args);

		return new EntityPageQuery<T>(sessionFactory, query, totalQuery);
	}

	public EntityPageQuery<T> createPageQueryModelByCriteria(Criteria criteria) {
		return new EntityPageQuery<T>(sessionFactory, criteria);
	}

	public EntityPageQuery<T> createPageQueryModelByExample(Example example, Class<T> clazz) {
		return new EntityPageQuery<T>(sessionFactory, example, clazz);
	}

	@SuppressWarnings("unchecked")

	public List<T> queryByExample(T t) {
		return getSession().createCriteria(t.getClass()).add(Example.create(t)).list();
	}

	public Object uniqueObjectByHql(String hql, Object... args) {
		Query query = getSession().createQuery(hql);
		applyParameter(query, args);

		return query.uniqueResult();
	}

	@SuppressWarnings("unchecked")

	public T uniqueRuesultByExample(T t) {
		return (T) getSession().createCriteria(t.getClass()).add(Example.create(t)).uniqueResult();
	}

	@SuppressWarnings("unchecked")

	public T uniqueRuesultByCriteria(Criteria criteria) {
		return (T) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")

	public T uniqueRuesultByExample(Example example, Class<T> clazz) {
		return (T) getSession().createCriteria(clazz).add(example).uniqueResult();
	}

	private T updateBean(T t) {
		if (t instanceof EntityBean) {
			EntityBean bean = (EntityBean) t;
			//
			// HttpServletRequest httpRequest = null;
			// try {
			// httpRequest = ((ServletRequestAttributes)
			// RequestContextHolder.currentRequestAttributes()).getRequest();
			// } catch (Exception e) {
			//
			// }
			// int user = httpRequest==null ||
			// httpRequest.getAttribute(Constant.CURRENT_USER) == null ?
			// EntityBean.DEFAULT_USER : (Integer) httpRequest
			// .getAttribute(Constant.CURRENT_USER);

			// if (0 == bean.getCreationUser()) {
			// bean.setCreationUser(user);
			// }
			// if (null == bean.getCreationTime()) {
			// bean.setCreationTime(new Date());
			// }
			//
			// bean.setLastUpdateUser(user);
			bean.setLastUpdateTime(new Date());
		}
		return t;
	}

	public Iterable<T> batchRemove(Iterable<T> iterable) {
		Session session = getSession();
		Iterator it = iterable.iterator();
		for (int i = 0; it.hasNext(); i++) {
			session.delete(it.next());
			if (i % BATCH_SIZE == 0) {
				session.flush();
				session.clear();
			}
		}
		return iterable;
	}

	public void remove(T t) {
		getSession().delete(t);
	}

	public void removeById(Serializable id, Class<T> clazz) {
		getSession().delete(getSession().get(clazz, id));
	}

	public int executeBySql(String sql) {
		return getSession().createSQLQuery(sql).executeUpdate();
	}
}
