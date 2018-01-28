package com.qbao.aisr.app.dto;

import com.qbao.aisr.app.model.TaokeDetail;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhangjun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-30 14:54:36 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 586 $$
 * $$LastChangedBy: zhangjun $$
 */
public class StuffAppealDto implements Serializable {
    private static final long serialVersionUID = "$Id$".hashCode();
    private long appealId; //主键id
    private int appealStatus; //申诉状态: -1 提交申诉,0 平台审核, 1受理申诉 ,2 用户取消申诉,3申诉反馈
    private String orderId; //订单id
    private Date appealTime; //创建时间
    private List<TaokeDetail> item;
    private int stuffNum; // 商品数
    private BigDecimal amount; //总价
    private int rebateValue; //补宝券/宝币值
    
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
	public int getAppealStatus() {
		return appealStatus;
	}
	public void setAppealStatus(int appealStatus) {
		this.appealStatus = appealStatus;
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
	
	public List<TaokeDetail> getItem() {
		return item;
	}
	public void setItem(List<TaokeDetail> item) {
		this.item = item;
	}
	
	public int getStuffNum() {
		return stuffNum;
	}
	public void setStuffNum(int stuffNum) {
		this.stuffNum = stuffNum;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
    
}
