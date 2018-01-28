package com.qbao.aisr.app.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/7/10 下午1:55
 * $$LastChangedDate: 2017-07-10 18:30:55 +0800 (Mon, 10 Jul 2017) $$
 * $$LastChangedRevision: 1413 $$
 * $$LastChangedBy: liaijun $$
 */
public class FlashSaleConfDto implements Serializable {
    private static final long serialVersionUID = "$Id: FlashSaleConfDto.java 1413 2017-07-10 10:30:55Z liaijun $".hashCode();

    private Long id;
    private String name;
    private Date onTime;
    private Date offTime;
    private Boolean isStart;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getStart() {
        return isStart;
    }

    public void setStart(Boolean start) {
        isStart = start;
    }

    @Override
    public String toString() {
        return "FlashSaleConfDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", onTime=" + onTime +
                ", offTime=" + offTime +
                ", isStart=" + isStart +
                '}';
    }
}
