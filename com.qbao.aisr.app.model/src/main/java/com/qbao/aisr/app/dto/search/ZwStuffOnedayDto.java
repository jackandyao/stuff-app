package com.qbao.aisr.app.dto.search;

import com.qbao.aisr.app.dto.StuffCouponDto;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * @author liaijun
 * @createTime 2017/7/4 12:16
 * $$LastChangedDate: 2017-07-07 11:28:33 +0800 (Fri, 07 Jul 2017) $$
 * $$LastChangedRevision: 586 $$
 * $$LastChangedBy: liaijun $$
 */
public class ZwStuffOnedayDto implements Serializable{
    private static final long serialVersionUID="$Id: ZwStuffDto.java 586 2017-07-07 03:28:33Z liaijun $".hashCode();
    private StuffCouponDto coupon;
    private Long id;
    private String name;
    private Double price;
    //最终折扣价格
    private Double finalPrice;
    private String imgUrl;
    private Integer OrderNum;
    private String source;
    private String rebateValue;
    private String linkUrl;


    DecimalFormat df = new DecimalFormat("0.00");

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrderNum() {
		return OrderNum;
	}

	public void setOrderNum(Integer orderNum) {
		OrderNum = orderNum;
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

	public StuffCouponDto getCoupon() {
		return coupon;
	}

	public void setCoupon(StuffCouponDto coupon) {
		this.coupon = coupon;
	}

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
