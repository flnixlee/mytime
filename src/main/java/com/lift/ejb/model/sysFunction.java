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
 * 功能菜单相关信息
 * @author Administrator
 * @date 2016年5月24日 下午10:47:45
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity(name = "TS_FUNCTION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class sysFunction extends EntityBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id; // 主键

	@Column(name = "PARENT_ID", nullable = true)
	private int parentId; // 父键

	@Column(name = "NAME", nullable = true, length = 128)
	private String name; // 名称

	@Column(name = "CODE", nullable = true, length = 64)
	private String code; // 编码

	@Column(name = "TYPE", nullable = true)
	private Integer type; // 类型

	@Column(name = "URL", nullable = true, length = 128)
	private String url; // 路径

	@Column(name = "LEVEL", nullable = true)
	private Integer level; // 层级

	@Column(name = "ICON", nullable = true, length = 128)
	private String icon; // 图标

	@Column(name = "HIDE", nullable = true)
	private Boolean hide; // 是否隐藏

	@Column(name = "DESCRIPTION", nullable = true, length = 256)
	private String description; // 描述

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getHide() {
		return hide;
	}

	public void setHide(Boolean hide) {
		this.hide = hide;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
