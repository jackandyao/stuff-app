package com.qbao.aisr.app.model.search;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shuaizhihu
 * @createTime 2017/3/11 12:04
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 * 站外搜索 商品实体
 */
public class ZwStuff implements Serializable{

    private static final long serialVersionUID="$Id$".hashCode();

    private long id;

    //商品源id
    private long realStuffId;

    //商品名称
    private String name;

    //原价格
    private double reservePrice;

    //最终折扣价格
    private double finalPrice;

    //返券模板id
    private long rebateId;

    //主图地址
    private String imgUrl;

    //商品原地址
    private String url;

    //商品推广地址
    private String iosPromotionUrl;

    private String androidPromotionUrl;

    //推广佣金
    private long promotionRate;

    //商品类目
    private String catId;

    //商品状态
    private int status;

    //商品来源
    private String source;

    //店铺id

    private long shopId;

    //店铺名称
    private String shopName;

    //30天销量
    private int orderNum;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;
    private String catIdPath;
    private String catName;
    private String catNamePath;
    //券链接地址
    private String link;
    //优惠多少
    private double value;
    //推文
    private String introduce;
    //商品活动关键字, 多个活动用,分割
    private String activities;
    //优惠券类型
    private String type;
    //券开始时间
    private Date startTime;

    //券结束时间
    private Date endTime;
    private String pcPromotionUrl;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRealStuffId() {
        return realStuffId;
    }

    public void setRealStuffId(long realStuffId) {
        this.realStuffId = realStuffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(double reservePrice) {
        this.reservePrice = reservePrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public long getRebateId() {
        return rebateId;
    }

    public void setRebateId(long rebateId) {
        this.rebateId = rebateId;
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

    public String getIosPromotionUrl() {
        return iosPromotionUrl;
    }

    public void setIosPromotionUrl(String iosPromotionUrl) {
        this.iosPromotionUrl = iosPromotionUrl;
    }

    public long getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(long promotionRate) {
        this.promotionRate = promotionRate;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCatIdPath() {
        return catIdPath;
    }

    public String getAndroidPromotionUrl() {
        return androidPromotionUrl;
    }

    public void setAndroidPromotionUrl(String androidPromotionUrl) {
        this.androidPromotionUrl = androidPromotionUrl;
    }

    public void setCatIdPath(String catIdPath) {
        this.catIdPath = catIdPath;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatNamePath() {
        return catNamePath;
    }

    public void setCatNamePath(String catNamePath) {
        this.catNamePath = catNamePath;
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

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPcPromotionUrl() {
        return pcPromotionUrl;
    }

    public void setPcPromotionUrl(String pcPromotionUrl) {
        this.pcPromotionUrl = pcPromotionUrl;
    }
}
