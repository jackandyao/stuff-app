package com.qbao.aisr.app.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * 品牌团dto
 * @author sjzhangjun
 */
public class AdBrandDto implements Serializable{
	
	private static final long serialVersionUID = "$Id$".hashCode();
	
	private Long brandId; // 品牌id
	private String brandName; // 品牌名称
	private String imgUrl; // 图片地址
	private String offSale; // 促销信息
	
	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getOffSale() {
		return offSale;
	}

	public void setOffSale(String offSale) {
		this.offSale = offSale;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
