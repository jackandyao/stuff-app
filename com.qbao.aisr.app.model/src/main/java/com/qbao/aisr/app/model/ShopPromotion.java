package com.qbao.aisr.app.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-04-10 11:47:22 +0800 (Mon, 10 Apr 2017) $$
 * $$LastChangedRevision: 662 $$
 * $$LastChangedBy: wangping $$
 */
public class ShopPromotion implements Serializable {
    private static final long serialVersionUID="$Id: ShopPromotion.java 662 2017-04-10 03:47:22Z wangping $".hashCode();

    // 统一店铺id
    private long id;
    // 实际店铺id
    private Long realShopId;
    // 店铺名称
    private String name;
    // 店铺链接
    private String url;
    // 店铺封面图片
    private String coverUrl;
    // 店铺logo图片链接
    private String logoUrl;
    // 店铺类目id
    private Integer categoryId;
    // 店铺状态 0:审核,1:正常,2关闭
    private Integer status;
    // 店铺来源:taobao,tmall,jd
    private String source;
    // 推广销量
    private Integer orderNum;
    // 点击量
    private Integer clickNum;
    //
    private Date createTime;
    //
    private Date updateTime;
    private String iosPromotionUrl;
    private String androidPromotionUrl;


    /**
     * 
     * 设置：统一店铺id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取：统一店铺id
     */
    public long getId() {
        return id;
    }

    /**
     * 设置：实际商品id
     */
    public void setRealShopId(Long realShopId) {
        this.realShopId = realShopId;
    }

    /**
     * 获取：实际商品id
     */
    public long getRealShopId() {
        return realShopId;
    }

    /**
     * 设置：店铺名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：店铺名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：店铺链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：店铺链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：店铺封面图片
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    /**
     * 获取：店铺封面图片
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * 设置：店铺logo图片链接
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    /**
     * 获取：店铺logo图片链接
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * 设置：店铺类目id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取：店铺类目id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置：店铺状态 0:审核,1:正常,2关闭
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：店铺状态 0:审核,1:正常,2关闭
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：店铺来源:taobao,tmall,jd
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取：店铺来源:taobao,tmall,jd
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置：推广销量
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取：推广销量
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置：点击量
     */
    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    /**
     * 获取：点击量
     */
    public Integer getClickNum() {
        return clickNum;
    }

    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public String getAndroidPromotionUrl() {
        return androidPromotionUrl;
    }

    public void setAndroidPromotionUrl(String androidPromotionUrl) {
        this.androidPromotionUrl = androidPromotionUrl;
    }

    public String getIosPromotionUrl() {
        return iosPromotionUrl;
    }

    public void setIosPromotionUrl(String iosPromotionUrl) {
        this.iosPromotionUrl = iosPromotionUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof ShopPromotion))
            return false;

        ShopPromotion that = (ShopPromotion) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getRealShopId(), that.getRealShopId())
                .append(getName(), that.getName())
                .append(getUrl(), that.getUrl())
                .append(getCoverUrl(), that.getCoverUrl())
                .append(getLogoUrl(), that.getLogoUrl())
                .append(getCategoryId(), that.getCategoryId())
                .append(getStatus(), that.getStatus())
                .append(getSource(), that.getSource())
                .append(getOrderNum(), that.getOrderNum())
                .append(getClickNum(), that.getClickNum())
                .append(getCreateTime(), that.getCreateTime())
                .append(getUpdateTime(), that.getUpdateTime())
                .append(getIosPromotionUrl(), that.getIosPromotionUrl())
                .append(getAndroidPromotionUrl(), that.getAndroidPromotionUrl())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getRealShopId())
                .append(getName())
                .append(getUrl())
                .append(getCoverUrl())
                .append(getLogoUrl())
                .append(getCategoryId())
                .append(getStatus())
                .append(getSource())
                .append(getOrderNum())
                .append(getClickNum())
                .append(getCreateTime())
                .append(getUpdateTime())
                .append(getIosPromotionUrl())
                .append(getAndroidPromotionUrl())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("realShopId", realShopId)
                .append("name", name)
                .append("url", url)
                .append("coverUrl", coverUrl)
                .append("logoUrl", logoUrl)
                .append("categoryId", categoryId)
                .append("status", status)
                .append("source", source)
                .append("orderNum", orderNum)
                .append("clickNum", clickNum)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .append("iosPromotionUrl", iosPromotionUrl)
                .append("androidPromotionUrl", androidPromotionUrl)
                .toString();
    }
}
