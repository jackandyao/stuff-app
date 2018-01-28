package com.qbao.aisr.app.dto;

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
public class StuffAppealDetailDto implements Serializable {
    private static final long serialVersionUID = "$Id$".hashCode();
    private long appealId; //主键id
    private int appealStatus; //申诉状态: -1 提交申诉,0 平台审核, 1受理申诉 ,2 用户取消申诉,3申诉反馈
    private String orderId; //订单id
    private Date appealTime; //创建时间
    private String source; //订单来源 tmall, taobao,jd
    private String phoneType; //手机型号
    private String content; //申诉问题描述
    private String reason; //申诉原因
    private String comment; //备注原因
	
	public long getAppealId() {
		return appealId;
	}
	public void setAppealId(long appealId) {
		this.appealId = appealId;
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
	public Date getAppealTime() {
		return appealTime;
	}
	public void setAppealTime(Date appealTime) {
		this.appealTime = appealTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
    
}
