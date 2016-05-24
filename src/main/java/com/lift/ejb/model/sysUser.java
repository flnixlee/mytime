package com.lift.ejb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lift.framework.entity.EntityBean;

/**
 * 用户信息
 * @author Administrator
 * @date 2016年5月24日 下午10:47:45
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity(name = "TS_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class sysUser extends EntityBean {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id; // 主键
	
}
