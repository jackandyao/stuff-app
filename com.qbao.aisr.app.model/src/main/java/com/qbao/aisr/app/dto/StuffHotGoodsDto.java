package com.qbao.aisr.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 
 * 
 * @author liaijun
 * @email liaijun@qbao.com
 * @date 2017-03-10 16:50:57
 */
public class StuffHotGoodsDto implements Serializable {
    private static final long serialVersionUID="$Id: StuffHotGoodsDto.java 568 2017-03-29 05:50:32Z wangping $".hashCode();

    //
    private Long id;

    //
    private String name;

    private BigDecimal finalPrice;

    private String imgUrl;

    private String linkUrl;

    private String rebateValue;

    private String source;

    private Long orderNum;

    DecimalFormat df = new DecimalFormat("0.00");

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getRebateValue() {
        return rebateValue;
    }

    public void setRebateValue(String rebateValue) {
        this.rebateValue = rebateValue;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getFinalPrice() {
        return new BigDecimal(df.format(finalPrice));
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Override
    public String toString() {
        return "StuffHotGoodsDto{" + "id=" + id + ", name='" + name + '\'' + ", finalPrice=" + finalPrice + ", imgUrl='" + imgUrl + '\'' + ", linkUrl='" + linkUrl + '\'' + ", rebateValue='" + rebateValue + '\'' + ", source='" + source + '\'' + ", orderNum=" + orderNum + '}';
    }

}
