package com.qbao.aisr.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * 优惠券model
 * @author sjzhangjun
 */
public class StuffCouponDto implements Serializable{
	
	private static final long serialVersionUID = "$Id$".hashCode();
	
	//TODO 去掉优惠券ID private long id; // 主键
	private String link; // 优惠券链接
	private BigDecimal value; // 优惠幅度
	private String type; // 优惠券类型
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type="优惠券";
		//this.type = type;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
