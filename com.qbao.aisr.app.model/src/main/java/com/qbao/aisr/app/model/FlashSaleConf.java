package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/7/10 下午1:55
 * $$LastChangedDate: 2017-07-10 18:30:55 +0800 (Mon, 10 Jul 2017) $$
 * $$LastChangedRevision: 1413 $$
 * $$LastChangedBy: liaijun $$
 */
public class FlashSaleConf implements Serializable {
    private static final long serialVersionUID = "$Id: FlashSaleConf.java 1413 2017-07-10 10:30:55Z liaijun $".hashCode();

    private Long id;
    private Integer status;
    private String name;
    private Date onTime;
    private Date offTime;
    private Date createTime;
    private Date updateTime;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "FlashSaleConf{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", onTime=" + onTime +
                ", offTime=" + offTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
