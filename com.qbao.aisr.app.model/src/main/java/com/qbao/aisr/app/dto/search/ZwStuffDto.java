package com.qbao.aisr.app.dto.search;

import com.qbao.aisr.app.dto.StuffCouponDto;
import org.dozer.Mapping;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * @author shuaizhihu
 * @createTime 2017/3/11 12:16
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
public class ZwStuffDto implements Serializable{

    private StuffCouponDto coupon;

    @Mapping("id")
    private long stuffId;
    private String name;
    @Mapping("finalPrice")
    private BigDecimal price;
    private String imgUrl;
    @Mapping("url")
    private String linkUrl;
    @Mapping("orderNum")
    private int saleCount;
    private String source;
    private String rebateValue;
    //券链接地址
    private String link;
    //优惠多少
    private double value;
    //推文
    private String introduce;

    //优惠券类型
    private String type;

    DecimalFormat df = new DecimalFormat("0.00");

    public long getStuffId() {
        return stuffId;
    }

    public void setStuffId(long stuffId) {
        this.stuffId = stuffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return new BigDecimal(df.format(price));
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public StuffCouponDto getCoupon() {
        return coupon;
    }

    public void setCoupon(StuffCouponDto coupon) {
        this.coupon = coupon;
    }
}
