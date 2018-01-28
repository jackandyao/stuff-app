package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author zhangjun
 * @createTime 17/6/14 下午17:15 $$LastChangedDate: $$ $$LastChangedRevision: $$
 *             $$LastChangedBy: zhangjun $$
 */
public class AdBrand implements Serializable {
	private static final long serialVersionUID = "$Id: AdBrand.java 578 2017-06-14 17:16:59Z wangping $"
			.hashCode();
	private Long id; // 品牌活动id
	private String name; // 活动名称
	private String imgUrl; // 品牌活动图片
	private Date onTime; // 上架时间
	private Date offTime; // 下架时间
	private Integer status; // 状态 0 下架 1 上架
	private Date createTime; // 创建时间
	private Date updateTime; // 更新时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getOnTime() {
		return onTime;
	}

	public void setOnTime(Date onTime) {
		this.onTime = onTime;
	}

	public Date getOffTime() {
		return offTime;
	}

	public void setOffTime(Date offTime) {
		this.offTime = offTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
