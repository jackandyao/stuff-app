package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/4/1 上午10:49
 * $$LastChangedDate: 2017-04-01 17:27:04 +0800 (Sat, 01 Apr 2017) $$
 * $$LastChangedRevision: 625 $$
 * $$LastChangedBy: liaijun $$
 */
public class BannerStuffConf implements Serializable {
    private static final long serialVersionUID = "$Id: BannerStuffConf.java 625 2017-04-01 09:27:04Z liaijun $".hashCode();

    private Long id;

    private String stuffName;

    private Long bannerId;

    private String title;

    private Integer level;

    private Long stuffId;

    private String memo;

    //
    private Date createTime;
    //
    private Date updateTime;

    private String advertiser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStuffName() {
        return stuffName;
    }

    public void setStuffName(String stuffName) {
        this.stuffName = stuffName;
    }

    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }

    public Long getStuffId() {
        return stuffId;
    }

    public void setStuffId(Long stuffId) {
        this.stuffId = stuffId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "BannerStuffConf{" + "id=" + id + ", stuffName='" + stuffName + '\'' + ", bannerId=" + bannerId + ", stuffId=" + stuffId + ", memo='" + memo + '\'' + ", createTime=" + createTime + ", updateTime=" + updateTime + ", advertiser='" + advertiser + '\'' + '}';
    }
}
