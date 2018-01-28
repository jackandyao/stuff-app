package com.qbao.aisr.app.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangping
 * @createTime 17/3/6 下午12:16
 * $$LastChangedDate: 2017-03-29 18:16:59 +0800 (Wed, 29 Mar 2017) $$
 * $$LastChangedRevision: 578 $$
 * $$LastChangedBy: wangping $$
 */
public class AdShop implements Serializable {
    private static final long serialVersionUID="$Id: AdShop.java 578 2017-03-29 10:16:59Z wangping $".hashCode();
    private long shopId;
    private int adLocationId;
    private int status;
    private Date onTime;
    private Date offTime;
    private Date createTime;
    private Date updateTime;
    private String advertiser;
    private String memo;

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public int getAdLocationId() {
        return adLocationId;
    }

    public void setAdLocationId(int adLocationId) {
        this.adLocationId = adLocationId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getOnTime() {
        return onTime;
    }

    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }

    public Date getOffTime() {
        return offTime;
    }

    public void setOffTime(Date offTime) {
        this.offTime = offTime;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("shopId", shopId)
                .append("adLocationId", adLocationId)
                .append("status", status)
                .append("onTime", onTime)
                .append("offTime", offTime)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .append("advertiser", advertiser)
                .append("memo", memo)
                .toString();
    }
}
