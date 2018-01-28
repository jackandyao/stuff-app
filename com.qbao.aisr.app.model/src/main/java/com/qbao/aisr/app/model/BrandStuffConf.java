package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author zhangjun
 * @createTime 17/6/14 下午17:15 $$LastChangedDate: $$ $$LastChangedRevision: $$
 *             $$LastChangedBy: zhangjun $$
 */
public class BrandStuffConf implements Serializable {
	private static final long serialVersionUID = "$Id: BrandStuffConf.java 578 2017-06-14 17:16:59Z wangping $"
			.hashCode();
	private Long id; // 主键id
	private String brandName; // ad_brand 表 品牌名称
	private Long brandId; // ad_brand 表 id 品牌Id
	private Long stuffId; // stuff_promtion 表 id banner
	private String memo; // 备注
	private Date createTime; // 创建时间
	private Date updateTime; // 更新时间
	private String advertiser; // 广告主

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getStuffId() {
		return stuffId;
	}

	public void setStuffId(Long stuffId) {
		this.stuffId = stuffId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(String advertiser) {
		this.advertiser = advertiser;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
