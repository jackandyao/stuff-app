package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @author xueming
 * @email louxueming@qbao.com
 * @date 2017-03-06 15:12:58
 */
public class TopSearchStuffComplex implements Serializable {
    private static final long serialVersionUID = "$Id: TopSearchStuffComplex.java 102 2017-03-08 06:51:26Z wangping $".hashCode();

    private long id;//统一商品id
    private String name;//商品名称
    private String imgUrl;//商品图片链接
    private String url;//商品链接
    private BigDecimal finalPrice;
    private String rebateValue;//返利多少
    private String source;//商品来源:taobao,tmall,jd

    // 统计数
    private Integer count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
