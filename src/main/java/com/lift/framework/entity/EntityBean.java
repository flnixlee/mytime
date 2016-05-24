package com.lift.framework.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.lift.framework.util.json.serializer.DateJsonSerializer;

/**
 * 基类
 * @author Administrator
 * @date 2016年5月24日 下午10:38:15
 */
@SuppressWarnings("serial")
@MappedSuperclass
@JacksonXmlRootElement(localName = "", namespace = "")
public class EntityBean implements Serializable {

	public EntityBean() {
	}

	@Column(name = "CREATOR", nullable = true, length = 32)
	private String creator;// 创建人

	@JsonSerialize(using = DateJsonSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TM", nullable = true)
	private Date createTm;

	@Column(name = "LAST_UPDATE_USER", nullable = true, length = 32)
	private String lastUpdateUser;// 修改人

	@JsonSerialize(using = DateJsonSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATE_TM", nullable = true)
	private Date lastUpdateTm;

	@Column(name = "ENABLE", nullable = true)
	private boolean enable = true;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public Date getLastUpdateTm() {
		return lastUpdateTm;
	}

	public void setLastUpdateTm(Date lastUpdateTm) {
		this.lastUpdateTm = lastUpdateTm;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}