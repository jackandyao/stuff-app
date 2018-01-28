package com.qbao.aisr.app.dto;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/3/7 下午2:45
 * $$LastChangedDate: 2017-06-28 16:28:43 +0800 (Wed, 28 Jun 2017) $$
 * $$LastChangedRevision: 1369 $$
 * $$LastChangedBy: wangping $$
 */
@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class UserOrderDto implements Serializable {
    private static final long serialVersionUID="$Id: UserOrderDto.java 1369 2017-06-28 08:28:43Z wangping $".hashCode();
    //商品来源
    @SerializedName("source")
    private String source;
    // 订单编号
    @SerializedName("order_id")
    private String orderNo;

    //下单时间
    @SerializedName("order_time")
    private Date orderTime;



    //返宝券状态 0: 未返券,1已返券,2返券被收回(以后可能被用到)
    private Integer rebateStatus;

    private Long appealValue;

    //返利多少
    private Long rebateValue;

    //总计多少钱
    private BigDecimal amount;

    private String unit;//宝券还是RMB

    public Integer getRebateStatus() {
        return rebateStatus;
    }

    public void setRebateStatus(Integer rebateStatus) {
        this.rebateStatus = rebateStatus;
    }

    public Long getRebateValue() {
        return rebateValue;
    }

    public void setRebateValue(Long rebateValue) {
        this.rebateValue = rebateValue;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }



    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getAppealValue() {
        return appealValue;
    }

    public void setAppealValue(Long appealValue) {
        this.appealValue = appealValue;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("source", source)
                .append("orderNo", orderNo)
                .append("orderTime", orderTime)
                .append("rebateStatus", rebateStatus)
                .append("appealValue", appealValue)
                .append("rebateValue", rebateValue)
                .append("amount", amount)
                .append("unit", unit)
                .toString();
    }
}
