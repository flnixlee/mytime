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

@MappedSuperclass
@JacksonXmlRootElement(localName = "", namespace = "")
public class EntityBean implements Serializable {

    private static final long serialVersionUID = -3076811241619904594L;
    // @Transient // 不生成映射列

    public static final int DEFAULT_USER = 1;

    public EntityBean() {
    }

    @JsonSerialize(using = DateJsonSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATE_TIME", nullable = true)
    private Date lastUpdateTime;

    @JsonSerialize(using = DateJsonSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_TIME", nullable = true)
    private Date creationTime;

    @Column(name = "IS_ENABLE", nullable = true)
    private boolean enable = true;

    @Column(name = "CREATE_USER", nullable = true)
    private Integer createUser;// 创建人

    @Column(name = "LAST_UPDATE_USER", nullable = true)
    private Integer lastUpdateUser;// 修改人

    /**
     * 获取ID
     * 
     * @return
     */
    public int getId() {
	return 0;
    };

    /**
     * 设置ID
     * 
     * @param id
     */
    public void setId(int id) {

    }

    /**
     * 获取最后修改的时间
     * 
     * @return
     */
    public Date getLastUpdateTime() {
	return lastUpdateTime;
    }

    /**
     * 设置最后修改的时间
     * 
     * @param lastUpdateTime
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * 获取创建的时间
     * 
     * @return
     */
    public Date getCreationTime() {
	return creationTime;
    }

    /**
     * 设置创建的时间
     * 
     * @param creationTime
     */
    public void setCreationTime(Date creationTime) {
	this.creationTime = creationTime;
    }

    /**
     * 是否启用
     * 
     * @return
     */
    public boolean isEnable() {
	return enable;
    }

    /**
     * 是否启用
     * 
     * @param enable
     */
    public void setEnable(boolean enable) {
	this.enable = enable;
    }

    public Integer getCreateUser() {
	return createUser;
    }

    public void setCreateUser(Integer createUser) {
	this.createUser = createUser;
    }

    public Integer getLastUpdateUser() {
	return lastUpdateUser;
    }

    public void setLastUpdateUser(Integer lastUpdateUser) {
	this.lastUpdateUser = lastUpdateUser;
    }

}