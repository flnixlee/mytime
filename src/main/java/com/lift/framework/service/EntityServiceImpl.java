package com.lift.framework.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.lift.framework.dao.Dao;
import com.lift.framework.entity.EntityPageQuery;
import com.lift.framework.entity.EntityQuery;

/**
 * 实体对象的Service实现类 力求实体对象Service类没有特殊要求不需要添加任何代码
 * 
 * @param <T>
 */
public abstract class EntityServiceImpl<T extends Serializable> implements EntityService<T> {// 泛型参数

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public EntityServiceImpl() {
		// 获取泛型参数
		Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		if (type instanceof Class) {
			clazz = (Class<T>) type;
		}
	}

	@Autowired
	protected Dao<T> dao;

	public List<T> getAll() {
		return dao.getAll(clazz);
	}

	public T save(T t) {
		return dao.save(t);
	}

	public T update(T t) {
		return dao.update(t);
	}

	@Deprecated
	public T saveOrUpdate(T t) {
		return dao.saveOrUpdate(t);
	}

	public T getById(Serializable id) {
		return dao.getById(id, clazz);
	}

	@SuppressWarnings("unchecked")

	public T getByCode(String code) {
		return (T) createCriteria().add(Restrictions.eq("code", code)).uniqueResult();
	}

	@SuppressWarnings("unchecked")

	public List<T> getByProperty(String propertyName, Object value) {
		return (List<T>) createCriteria().add(Restrictions.eq(propertyName, value)).list();
	}

	public Object uniqueObjectByHql(String hql, Object... args) {
		return dao.uniqueObjectByHql(hql, args);
	}

	public T uniqueResultByHql(String hql, Object... args) {
		return dao.uniqueRuesultByHql(hql, args);
	}

	public Iterable<T> batchSave(Iterable<T> list) {
		return dao.batchSave(list);
	}

	public Iterable<T> batchSaveOrUpdate(Iterable<T> list) {
		return dao.batchSaveOrUpdate(list);
	}

	public Iterable<T> batchDelete(Iterable<T> list) {
		return dao.batchDelete(list);
	}

	public Iterable<T> batchUpdate(Iterable<T> list) {
		return dao.batchUpdate(list);
	}

	public Criteria createCriteria() {
		return dao.createCriteria(clazz);
	}

	public List<Object[]> queryBySql(String sql, Object... args) {
		return dao.queryBySql(sql, args);
	}

	public EntityQuery<T> createQueryModelByHql(String hql, Object... args) {
		return dao.createQueryModelByHql(hql, args);
	}

	public EntityQuery<T> createQueryModelBySql(String sql, Object... args) {
		return dao.createQueryModelBySql(sql, args);
	}

	public EntityQuery<T> createQueryModelByCriteria(Criteria criteria) {
		return dao.createQueryModelByCriteria(criteria);
	}

	public EntityQuery<T> createQueryModelByExample(Example example) {
		return dao.createQueryModelByExample(example, clazz);
	}

	public EntityPageQuery<T> createPageQueryModelByHql(String hql, Object... args) {
		return dao.createPageQueryModelByHql(hql, args);
	}

	public EntityPageQuery<T> createPageQueryModelBySql(String sql, Object... args) {
		return dao.createPageQueryModelBySql(sql, args);
	}

	public EntityPageQuery<T> createPageQueryModelBySql(String sql, Class<T> clazz, Object... args) {
		return dao.createPageQueryModelBySql(sql, clazz, args);
	}

	public EntityPageQuery<T> createPageQueryModelByCriteria(Criteria criteria) {
		return dao.createPageQueryModelByCriteria(criteria);
	}

	public EntityPageQuery<T> createPageQueryModelByExample(Example example) {
		return dao.createPageQueryModelByExample(example, clazz);
	}

	public Object uniqueObjectBySql(String sql, Object... args) {
		return dao.uniqueObjectBySql(sql, args);
	}

	public void delete(T t) {
		dao.delete(t);
	}

	public void deleteById(Serializable id) {
		dao.deleteById(id, clazz);
	}

	public void remove(T t) {
		dao.remove(t);
	}

	public void removeById(Serializable id) {
		dao.removeById(id, clazz);
	}

	public int executeBySql(String sql) {
		return dao.executeBySql(sql);
	}
}
