package com.qbao.aisr.app.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangping
 * @createTime 17/3/6 下午12:15
 * $$LastChangedDate: 2017-06-16 17:05:05 +0800 (Fri, 16 Jun 2017) $$
 * $$LastChangedRevision: 1248 $$
 * $$LastChangedBy: shuaizhihu $$
 */
public class AdStuff implements Serializable {
    private static final long serialVersionUID="$Id: AdStuff.java 1248 2017-06-16 09:05:05Z shuaizhihu $".hashCode();
    private long stuffId;
    private int adLocationId;
    private int status;
    private Date onTime;
    private Date offTime;
    private Date createTime;
    private Date updateTime;
    private String advertiser;
    private String source;
    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public long getStuffId() {
        return stuffId;
    }

    public void setStuffId(long stuffId) {
        this.stuffId = stuffId;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("stuffId", stuffId)
                .append("adLocationId", adLocationId)
                .append("status", status)
                .append("onTime", onTime)
                .append("offTime", offTime)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .append("advertiser", advertiser)
                .append("source", source)
                .toString();
    }
}
