package com.qbao.aisr.app.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.qbao.aisr.app.model.StuffPromotion;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-14 16:37:52 +0800 (Tue, 14 Mar 2017) $$
 * $$LastChangedRevision: 206 $$
 * $$LastChangedBy: allen $$
 */
public class ShopPromotionDto implements Serializable {
    private static final long serialVersionUID = "$Id: ShopPromotionDto.java 206 2017-03-14 08:37:52Z allen $".hashCode();

    // 统一店铺id
    private long id;
    // 实际商品id
    private long realShopId;
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

    // 商店所属商品
    private List<StuffPromotionDto> list;

    public List<StuffPromotionDto> getList() {
        return list;
    }

    public void setList(List<StuffPromotionDto> list) {
        this.list = list;
    }

    public void setRealShopId(long realShopId) {
        this.realShopId = realShopId;
    }

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
    public void setRealShopId(Integer realShopId) {
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

    @Override
    public String toString() {
        return "ShopPromotionDto{" + "id=" + id + ", realShopId=" + realShopId + ", name='" + name + '\'' + ", url='" + url + '\'' + ", coverUrl='" + coverUrl + '\'' + ", logoUrl='" + logoUrl + '\'' + ", categoryId=" + categoryId + ", status=" + status + ", source='" + source + '\'' + ", orderNum=" + orderNum + ", clickNum=" + clickNum + ", list=" + list + '}';
    }
}
