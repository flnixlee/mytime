package com.lift.framework.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import com.lift.framework.entity.EntityPageQuery;
import com.lift.framework.entity.EntityQuery;

/**
 * 实体对象的Service接口
 * @author WuNan2 2012-12-18
 *
 * @param <T>
 */
public interface EntityService<T extends Serializable> {
	/**
	 * 获取所有
	 * 
	 * @return
	 */
	List<T> getAll();
	
	/**
	 * 逻辑删除
	 * @param t
	 * @return
	 */
	void delete(T t);
	
	/***
	 * 原生sql
	 * @param sql
	 * @return
	 */
	int executeBySql(String sql);
	/**
	 * 逻辑删除
	 * @param t
	 * @return
	 */
	void deleteById(Serializable id);
	
	/**
	 * 物理删除
	 * @param t
	 * @return
	 */
	void remove(T t);
	
	/**
	 * 物理删除
	 * @param t
	 * @return
	 */
	void removeById(Serializable id);

	/**
	 * 插入
	 * 
	 * @param t
	 * @return
	 */
	T save(T t);

	/**
	 * 更新修改
	 * 
	 * @param t
	 * @return
	 */
	T update(T t);

	/**
	 * 保存或更新修改
	 * 
	 * @param t
	 * @return
	 */
	@Deprecated
	T saveOrUpdate(T t);

	/**
	 * 根据ID获取对象
	 * 
	 * @param id
	 * @return
	 */
	T getById(Serializable id);

	/**
	 * 根据Code获取对象
	 * 
	 * @param code
	 * @return
	 */
	T getByCode(String code);
	
	public List<T> getByProperty(String propertyName,Object value);
	
	/**
	 * SQL查询，返回多个结果
	 * @param hql
	 * @param args
	 * @return
	 */
	List<Object[]> queryBySql(String sql, Object... args);
	
	/**
	 * 返回单个普通对象，一般用于聚合函数查询
	 * @param sql
	 * @param args
	 * @return
	 */
	Object uniqueObjectBySql(String sql, Object... args);

	/**
	 * 唯一结果(Object类型，可以是Model对象，也可以是聚合函数返回的结果)
	 * 
	 * @param hql
	 * @param args
	 * @return
	 */
	Object uniqueObjectByHql(String hql, Object... args);
	
	/**
	 * 返回单个结果
	 * @param hql
	 * @param args
	 * @return
	 */
	T uniqueResultByHql(String hql, Object... args);
	
	/**
	 * 批量插入
	 * @param collection
	 * @return
	 */
	Iterable<T> batchSave(Iterable<T> collection);
	
	/**
	 * 批量插入或更新
	 * @param collection
	 * @return
	 */
	Iterable<T> batchSaveOrUpdate(Iterable<T> collection);
	
	/**
	 * 批量删除
	 * @param collection
	 * @return
	 */
	Iterable<T> batchDelete(Iterable<T> collection);
	
	/**
	 * 批量更新
	 * @param collection
	 * @return
	 */
	Iterable<T> batchUpdate(Iterable<T> collection);
	
	/**
	 * 创建Criteria条件
	 * @return
	 */
	Criteria createCriteria();
	
	/**
	 * 创建查询模板
	 * @param hql
	 * @param args
	 * @return
	 */
	EntityQuery<T> createQueryModelByHql(String hql, Object... args);

	/**
	 * 创建查询模板
	 * @param sql
	 * @param args
	 * @return
	 */
	EntityQuery<T> createQueryModelBySql(String sql, Object... args);

	/**
	 * 创建查询模板
	 * @param criteria
	 * @return
	 */
	EntityQuery<T> createQueryModelByCriteria(Criteria criteria);
	
	/**
	 * 创建查询模板
	 * @param example
	 * @return
	 */
	EntityQuery<T> createQueryModelByExample(Example example);
	
	/**
	 * 创建分页查询模板
	 * @param hql
	 * @param args
	 * @return
	 */
	EntityPageQuery<T> createPageQueryModelByHql(String hql, Object... args);
	
	/**
	 * 创建分页查询模板
	 * @param sql
	 * @param args
	 * @return
	 */
	EntityPageQuery<T> createPageQueryModelBySql(String sql, Object... args);
	
	/**
	 * 创建分页查询模板用原生态sql语句查询，查询结果转换为对象
	 * @param sql
	 * @param args
	 * @return
	 */
	EntityPageQuery<T> createPageQueryModelBySql(String sql,Class<T> clazz, Object... args);
	
	/**
	 * 创建分页查询模板
	 * @param criteria
	 * @return
	 */
	EntityPageQuery<T> createPageQueryModelByCriteria(Criteria criteria);
	
	/**
	 * 创建分页查询模板
	 * @param example
	 * @return
	 */
	EntityPageQuery<T> createPageQueryModelByExample(Example example);
}
