package com.qbao.aisr.app.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangping
 * @createTime 17/3/6 下午12:15
 * $$LastChangedDate: 2017-03-29 18:16:59 +0800 (Wed, 29 Mar 2017) $$
 * $$LastChangedRevision: 578 $$
 * $$LastChangedBy: wangping $$
 */
public class AdJu implements Serializable {
    private static final long serialVersionUID="$Id: AdJu.java 578 2017-03-29 10:16:59Z wangping $".hashCode();
    private long juId;
    private int adLocationId;
    private int status;
    private Date onTime;
    private Date offTime;
    private String advertiser;
    private Date createTime;
    private Date updateTime;

    public long getJuId() {
        return juId;
    }

    public void setJuId(long juId) {
        this.juId = juId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("juId", juId)
                .append("adLocationId", adLocationId)
                .append("status", status)
                .append("onTime", onTime)
                .append("offTime", offTime)
                .append("advertiser", advertiser)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .toString();
    }
}
