package com.Map.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

public class Common {
	@ApiModelProperty(dataType = "String",value = "id")
	@JsonIgnore
	private String _id;
	
	@ApiModelProperty(dataType = "Date",value = "createDate")
	@Field(value = "createDate")
	private Date createDate;
	
	@Field(value = "updateDate")
	@ApiModelProperty(dataType = "Date",value = "updateDate")
	private Date updateDate;
	
	@Field(value = "createBy")
	@ApiModelProperty(dataType = "String",value = "createBy")
	private String createBy;
	
	@Field(value = "updateBy")
	@ApiModelProperty(dataType = "String",value = "updateBy")
	private String updateBy;
	
	@Field(value = "isDeleted")
	@ApiModelProperty(dataType = "Boolean",value = "isDeleted")
	private Boolean isDeleted;
	
	public Common() {
		super();
		this._id = UUID.randomUUID().toString();
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
