package com.qbao.aisr.app.dto.search;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.qbao.aisr.app.dto.StuffCouponDto;

/**
 * @author liaijun
 * @createTime 2017/7/4 12:16
 * $$LastChangedDate: 2017-07-07 11:28:33 +0800 (Fri, 07 Jul 2017) $$
 * $$LastChangedRevision: 586 $$
 * $$LastChangedBy: liaijun $$
 */
public class ZwStuffDetailDto implements Serializable{
    private static final long serialVersionUID="$Id: ZwStuffDto.java 586 2017-07-07 03:28:33Z liaijun $".hashCode();
    private StuffCouponDto coupon;
    private Long id;
    private String name;
    private Double price;
    //最终折扣价格
    private Double finalPrice;
    private String imgUrl;
    private String linkUrl;
    private Integer saleCount;
    private String source;
    private String rebateValue;
    //推文
    private String copyWriter;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    DecimalFormat df = new DecimalFormat("0.00");

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRebateValue() {
        return rebateValue;
    }

    public void setRebateValue(String rebateValue) {
        this.rebateValue = rebateValue;
    }

    public String getCopyWriter() {
        return copyWriter;
    }

    public void setCopyWriter(String copyWriter) {
        this.copyWriter = copyWriter;
    }

	public StuffCouponDto getCoupon() {
		return coupon;
	}

	public void setCoupon(StuffCouponDto coupon) {
		this.coupon = coupon;
	}

}
