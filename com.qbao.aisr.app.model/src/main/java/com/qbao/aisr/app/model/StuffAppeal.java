package com.qbao.aisr.app.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangjun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-30 14:54:36 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 586 $$
 * $$LastChangedBy: zhangjun $$
 */
public class StuffAppeal implements Serializable {
    private static final long serialVersionUID = "$Id$".hashCode();
    private long appealId; //主键id
    private long userId; //用户id
    private String orderId; //订单id
    private String source; //订单来源 tmall, taobao,jd
    private int appealStatus; //申诉状态: -1 提交申诉,0 平台审核, 1受理申诉 ,2 用户取消申诉,3申诉反馈
    private String content; //申诉问题描述
    private String imgUrl; //图片路径 多个路径用逗号分割
    private int device; //设备类型1: ios , 2 :android
    private String phoneBrand; //手机品牌
    private String phoneType; //手机型号
    private String reason; //申诉原因
    private int rebateValue; //补宝券/宝币值
    private Date appealTime; //创建时间
    private Date updateTime; //修改时间
    private String ops; //处理者
    private String comment; //备注原因
    private String qq; //申诉人qq号码
    private String phone; //申诉人手机号码
	
	public long getAppealId() {
		return appealId;
	}
	public void setAppealId(long appealId) {
		this.appealId = appealId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getAppealStatus() {
		return appealStatus;
	}
	public void setAppealStatus(int appealStatus) {
		this.appealStatus = appealStatus;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getDevice() {
		return device;
	}
	public void setDevice(int device) {
		this.device = device;
	}
	public String getPhoneBrand() {
		return phoneBrand;
	}
	public void setPhoneBrand(String phoneBrand) {
		this.phoneBrand = phoneBrand;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getRebateValue() {
		return rebateValue;
	}
	public void setRebateValue(int rebateValue) {
		this.rebateValue = rebateValue;
	}
	public Date getAppealTime() {
		return appealTime;
	}
	public void setAppealTime(Date appealTime) {
		this.appealTime = appealTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getOps() {
		return ops;
	}
	public void setOps(String ops) {
		this.ops = ops;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
    
}
