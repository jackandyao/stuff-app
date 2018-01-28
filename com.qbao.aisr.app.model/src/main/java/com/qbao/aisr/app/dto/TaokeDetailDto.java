package com.qbao.aisr.app.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * 
 * @author liaijun
 * @email liaijun@qbao.com
 * @date 2017-03-13 23:20:06
 */
public class TaokeDetailDto implements Serializable {
    private static final long serialVersionUID = "$Id: TaokeDetailDto.java 1369 2017-06-28 08:28:43Z wangping $".hashCode();

    // 商品名称
    private String name;
    // 商品数
    private Integer stuffNum;
    // 商品单价
    private BigDecimal finalPrice;
    // 商品图片地址
    private String imgUrl;
    // 商品详细页面
    private String clickUrl;
    //
    private String rebateValue;

    private Long stuffId;

    private String unit;//宝券还是RMB

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStuffNum() {
        return stuffNum;
    }

    public void setStuffNum(Integer stuffNum) {
        this.stuffNum = stuffNum;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getRebateValue() {
        return rebateValue;
    }

    public void setRebateValue(String rebateValue) {
        this.rebateValue = rebateValue;
    }

    public Long getStuffId() {
        return stuffId;
    }

    public void setStuffId(Long stuffId) {
        this.stuffId = stuffId;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
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
                .append("name", name)
                .append("stuffNum", stuffNum)
                .append("finalPrice", finalPrice)
                .append("imgUrl", imgUrl)
                .append("clickUrl", clickUrl)
                .append("rebateValue", rebateValue)
                .append("stuffId", stuffId)
                .append("unit", unit)
                .toString();
    }
}
