package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * 优惠券model
 * @author sjzhangjun
 */
public class StuffCoupon implements Serializable{
	
	private static final long serialVersionUID = "$Id$".hashCode();
	
	private long id; // 主键
	private String couponId; // 优惠券id
	private long stuffId; // 商品id
	private long realStuffId; // 真实商品id
	private String link; // 优惠券链接
	private BigDecimal value; // 优惠幅度
	private String introduce; // 推广文案
	private String type; // 优惠券类型
	private int totalCount; // 总库存
	private String source; // 来源
	private Date startTime; // 开始时间
	private Date endTime; // 结束时间
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public long getStuffId() {
		return stuffId;
	}

	public void setStuffId(long stuffId) {
		this.stuffId = stuffId;
	}

	public long getRealStuffId() {
		return realStuffId;
	}

	public void setRealStuffId(long realStuffId) {
		this.realStuffId = realStuffId;
	}

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
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
