package com.qbao.aisr.app.model.search;

import java.io.Serializable;

/**
 * @author shuaizhihu
 * @createTime 2017/3/11 12:03
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 * 站内搜索 商品实体
 */
public class ZnStuff implements Serializable{

    private static final long serialVersionUID="$Id$".hashCode();
    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品价格（钱宝币）
     */
    private Long productPrice;

    /**
     * 产品图片
     */
    private String mainImg;

    /**
     * 发布状态 1：上架 2：下架
     */
    private Integer publishState;

    /**
     * 审核状态
     */
    private Integer finalAuditState;

    /**
     * 销量
     */
    private Long saleNum;

    /**
     * 收藏量
     */
    private Long collectCount;

    /**
     * 商家类型
     */
    private Integer merchantType;

    /**
     * 商家等级
     */
    private Integer currentLv;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 商品点赞数
     */
    private Integer spuThumb;

    /**
     * 是否参加宝购
     */
    private Integer hasBaogou;

    /**
     * 是否参加雷拍
     */
    private Integer hasLeiPai;
    /**
     * 是否好货
     */
    private Integer isHaohuo;
    /**
     * 好货得分
     */
    private Float haohuoScore;
    /**
     * 新品
     */
    private Integer newStatus;
    /**
     * 钱宝自营
     */
    private Integer qbaoBuy;
    /**
     * 特殊活动日
     */
    private String specialDay;

    /**
     * 来源
     */
    private String fromSource = "钱宝";

    /**
     * 好货指数url
     */
    private String haohuoUrl;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public Integer getPublishState() {
        return publishState;
    }

    public void setPublishState(Integer publishState) {
        this.publishState = publishState;
    }

    public Integer getFinalAuditState() {
        return finalAuditState;
    }

    public void setFinalAuditState(Integer finalAuditState) {
        this.finalAuditState = finalAuditState;
    }

    public Long getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Long saleNum) {
        this.saleNum = saleNum;
    }

    public Long getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Long collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    public Integer getCurrentLv() {
        return currentLv;
    }

    public void setCurrentLv(Integer currentLv) {
        this.currentLv = currentLv;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getSpuThumb() {
        return spuThumb;
    }

    public void setSpuThumb(Integer spuThumb) {
        this.spuThumb = spuThumb;
    }

    public Integer getHasBaogou() {
        return hasBaogou;
    }

    public void setHasBaogou(Integer hasBaogou) {
        this.hasBaogou = hasBaogou;
    }

    public Integer getHasLeiPai() {
        return hasLeiPai;
    }

    public void setHasLeiPai(Integer hasLeiPai) {
        this.hasLeiPai = hasLeiPai;
    }

    public Integer getIsHaohuo() {
        return isHaohuo;
    }

    public void setIsHaohuo(Integer isHaohuo) {
        this.isHaohuo = isHaohuo;
    }

    public Float getHaohuoScore() {
        return haohuoScore;
    }

    public void setHaohuoScore(Float haohuoScore) {
        this.haohuoScore = haohuoScore;
    }

    public Integer getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    public Integer getQbaoBuy() {
        return qbaoBuy;
    }

    public void setQbaoBuy(Integer qbaoBuy) {
        this.qbaoBuy = qbaoBuy;
    }

    public String getSpecialDay() {
        return specialDay;
    }

    public void setSpecialDay(String specialDay) {
        this.specialDay = specialDay;
    }

    public String getFromSource() {
        return fromSource;
    }

    public void setFromSource(String fromSource) {
        this.fromSource = fromSource;
    }

    public String getHaohuoUrl() {
        return haohuoUrl;
    }

    public void setHaohuoUrl(String haohuoUrl) {
        this.haohuoUrl = haohuoUrl;
    }
}
