package com.qbao.aisr.app.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/7 下午2:45
 * $$LastChangedDate: 2017-06-28 16:28:43 +0800 (Wed, 28 Jun 2017) $$
 * $$LastChangedRevision: 1369 $$
 * $$LastChangedBy: wangping $$
 */
@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class UserOrderInfoDto implements Serializable {
    private static final long serialVersionUID="$Id: UserOrderInfoDto.java 1369 2017-06-28 08:28:43Z wangping $".hashCode();
    private Long id;
    private String orderNo;
    // 返宝券状态 0: 未返券,1已返券,2返券被收回(以后可能被用到)
    private Integer rebateStatus;

    private List<TaokeDetailDto> item;

    // 总计多少钱
    private BigDecimal amount;

    private Integer stuffNum;

    private Long rebateValue;

    private String unit;//宝券还是RMB

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TaokeDetailDto> getItem() {
        return item;
    }

    public void setItem(List<TaokeDetailDto> item) {
        this.item = item;
    }

    public Integer getStuffNum() {
        return stuffNum;
    }

    public void setStuffNum(Integer stuffNum) {
        this.stuffNum = stuffNum;
    }

    public Long getRebateValue() {
        return rebateValue;
    }

    public void setRebateValue(Long rebateValue) {
        this.rebateValue = rebateValue;
    }

    public Integer getRebateStatus() {
        return rebateStatus;
    }

    public void setRebateStatus(Integer rebateStatus) {
        this.rebateStatus = rebateStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("orderNo", orderNo)
                .append("rebateStatus", rebateStatus)
                .append("item", item)
                .append("amount", amount)
                .append("stuffNum", stuffNum)
                .append("rebateValue", rebateValue)
                .append("unit", unit)
                .toString();
    }
}
