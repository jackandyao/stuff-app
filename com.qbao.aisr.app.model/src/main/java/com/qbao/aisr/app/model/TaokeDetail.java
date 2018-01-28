package com.qbao.aisr.app.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @author liaijun
 * @email liaijun@qbao.com
 * @date 2017-03-13 23:20:06
 */
public class TaokeDetail implements Serializable {
    private static final long serialVersionUID = "$Id: TaokeDetail.java 1369 2017-06-28 08:28:43Z wangping $".hashCode();

    //
    private Long id;
    // 商品名称
    private String name;
    // 创建时间
    private Date orderTime;
    // 点击时间
    private Date clickTime;
    // 商品信息
    private String stuffInfo;
    // 商品id
    private Long stuffId;
    // 掌柜旺旺
    private String wangwang;
    // 所属店铺
    private String shopName;
    // 商品数
    private Integer stuffNum;
    // 商品单价
    private BigDecimal price;
    // 订单状态
    private String orderStatus;
    // 订单类型
    private String orderType;
    // 收入比率
    private Double incomeRate;
    // 分成比率
    private Double sharingRate;
    // 佣金比率
    private Double commissionRate;
    // 补贴比率
    private Double subsidyRate;
    // 补贴类型
    private String subsidyType;
    // 成交平台
    private String platform;
    // 第三方服务
    private String thirdPartySerivce;
    // 订单编号
    private Long orderId;
    // 类目名称
    private String categoryName;
    // 来源媒体id
    private Long sourceId;
    // 来源媒体名称
    private String sourceName;
    // 广告位ID
    private Long advId;
    // 广告位名称
    private String advName;
    //
    private Date createTime;
    // 商品图片地址
    private String imgUrl;
    // 商品详细页面
    private String iosClickUrl;
    // 商品详细页面
    private String androidClickUrl;
    //
    private String rebateValue;

    //
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    private Integer rebateType;//返利类型 0  宝券 1 RMB

    /**
     * 
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：创建时间
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getOrderTime() {
        return orderTime;
    }

    public Integer getRebateType() {
        return rebateType;
    }

    public void setRebateType(Integer rebateType) {
        this.rebateType = rebateType;
    }

    /**

     * 设置：点击时间
     */
    public void setClickTime(Date clickTime) {
        this.clickTime = clickTime;
    }

    /**
     * 获取：点击时间
     */
    public Date getClickTime() {
        return clickTime;
    }

    /**
     * 设置：商品信息
     */
    public void setStuffInfo(String stuffInfo) {
        this.stuffInfo = stuffInfo;
    }

    /**
     * 获取：商品信息
     */
    public String getStuffInfo() {
        return stuffInfo;
    }

    /**
     * 设置：商品id
     */
    public void setStuffId(Long stuffId) {
        this.stuffId = stuffId;
    }

    /**
     * 获取：商品id
     */
    public Long getStuffId() {
        return stuffId;
    }

    /**
     * 设置：掌柜旺旺
     */
    public void setWangwang(String wangwang) {
        this.wangwang = wangwang;
    }

    /**
     * 获取：掌柜旺旺
     */
    public String getWangwang() {
        return wangwang;
    }

    /**
     * 设置：所属店铺
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 获取：所属店铺
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 设置：商品数
     */
    public void setStuffNum(Integer stuffNum) {
        this.stuffNum = stuffNum;
    }

    /**
     * 获取：商品数
     */
    public Integer getStuffNum() {
        return stuffNum;
    }

    /**
     * 设置：商品单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取：商品单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置：订单状态
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取：订单状态
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置：订单类型
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取：订单类型
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置：收入比率
     */
    public void setIncomeRate(Double incomeRate) {
        this.incomeRate = incomeRate;
    }

    /**
     * 获取：收入比率
     */
    public Double getIncomeRate() {
        return incomeRate;
    }

    /**
     * 设置：分成比率
     */
    public void setSharingRate(Double sharingRate) {
        this.sharingRate = sharingRate;
    }

    /**
     * 获取：分成比率
     */
    public Double getSharingRate() {
        return sharingRate;
    }

    /**
     * 设置：佣金比率
     */
    public void setCommissionRate(Double commissionRate) {
        this.commissionRate = commissionRate;
    }

    /**
     * 获取：佣金比率
     */
    public Double getCommissionRate() {
        return commissionRate;
    }

    /**
     * 设置：补贴比率
     */
    public void setSubsidyRate(Double subsidyRate) {
        this.subsidyRate = subsidyRate;
    }

    /**
     * 获取：补贴比率
     */
    public Double getSubsidyRate() {
        return subsidyRate;
    }

    /**
     * 设置：补贴类型
     */
    public void setSubsidyType(String subsidyType) {
        this.subsidyType = subsidyType;
    }

    /**
     * 获取：补贴类型
     */
    public String getSubsidyType() {
        return subsidyType;
    }

    /**
     * 设置：成交平台
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * 获取：成交平台
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * 设置：第三方服务
     */
    public void setThirdPartySerivce(String thirdPartySerivce) {
        this.thirdPartySerivce = thirdPartySerivce;
    }

    /**
     * 获取：第三方服务
     */
    public String getThirdPartySerivce() {
        return thirdPartySerivce;
    }

    /**
     * 设置：订单编号
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取：订单编号
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置：类目名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获取：类目名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置：来源媒体id
     */
    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * 获取：来源媒体id
     */
    public Long getSourceId() {
        return sourceId;
    }

    /**
     * 设置：来源媒体名称
     */
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    /**
     * 获取：来源媒体名称
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * 设置：广告位ID
     */
    public void setAdvId(Long advId) {
        this.advId = advId;
    }

    /**
     * 获取：广告位ID
     */
    public Long getAdvId() {
        return advId;
    }

    /**
     * 设置：广告位名称
     */
    public void setAdvName(String advName) {
        this.advName = advName;
    }

    /**
     * 获取：广告位名称
     */
    public String getAdvName() {
        return advName;
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
     * 设置：商品图片地址
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取：商品图片地址
     */
    public String getImgUrl() {
        return imgUrl;
    }

    public String getIosClickUrl() {
        return iosClickUrl;
    }

    public void setIosClickUrl(String iosClickUrl) {
        this.iosClickUrl = iosClickUrl;
    }

    public String getAndroidClickUrl() {
        return androidClickUrl;
    }

    public void setAndroidClickUrl(String androidClickUrl) {
        this.androidClickUrl = androidClickUrl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * 设置：
     */
    public void setRebateValue(String rebateValue) {
        this.rebateValue = rebateValue;
    }

    /**
     * 获取：
     */
    public String getRebateValue() {
        return rebateValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
