package com.qbao.aisr.app.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-07 17:49:13 +0800 (Tue, 07 Mar 2017) $$
 * $$LastChangedRevision: 91 $$
 * $$LastChangedBy: wangping $$
 */
public class AdBanner implements Serializable {
    private static final long serialVersionUID="$Id: AdBanner.java 91 2017-03-07 09:49:13Z wangping $".hashCode();

    //
    private Long id;
    //
    private String imgUrl;
    //
    private String linkUrl;
    //
    private Integer status;
    //
    private Date onTime;
    //
    private Date offTime;

    private int moduleId;
    // banner_location 表id banner 位置id
    private Integer adLocationId;
    // banner 名称
    private String name;
    // 备注
    private String memo;
    //
    private Date createTime;
    //
    private Date updateTime;
    // 广告主
    private String advertiser;

    /**
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
     * 设置：
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取：
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置：
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /**
     * 获取：
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * 设置：
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：
     */
    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }

    /**
     * 获取：
     */
    public Date getOnTime() {
        return onTime;
    }

    /**
     * 设置：
     */
    public void setOffTime(Date offTime) {
        this.offTime = offTime;
    }

    /**
     * 获取：
     */
    public Date getOffTime() {
        return offTime;
    }

    /**
     * 设置：banner_location 表id banner 位置id
     */
    public void setAdLocationId(Integer adLocationId) {
        this.adLocationId = adLocationId;
    }

    /**
     * 获取：banner_location 表id banner 位置id
     */
    public Integer getAdLocationId() {
        return adLocationId;
    }

    /**
     * 设置：banner 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：banner 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 获取：备注
     */
    public String getMemo() {
        return memo;
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

    /**
     * 设置：广告主
     */
    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    /**
     * 获取：广告主
     */
    public String getAdvertiser() {
        return advertiser;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("imgUrl", imgUrl)
                .append("linkUrl", linkUrl)
                .append("status", status)
                .append("onTime", onTime)
                .append("offTime", offTime)
                .append("moduleId", moduleId)
                .append("adLocationId", adLocationId)
                .append("name", name)
                .append("memo", memo)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .append("advertiser", advertiser)
                .toString();
    }
}
