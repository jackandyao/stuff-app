package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/7/10 下午2:00
 * $$LastChangedDate: 2017-07-10 18:30:55 +0800 (Mon, 10 Jul 2017) $$
 * $$LastChangedRevision: 1413 $$
 * $$LastChangedBy: liaijun $$
 */
public class FlashSaleStuff  implements Serializable {
    private static final long serialVersionUID = "$Id: FlashSaleStuff.java 1413 2017-07-10 10:30:55Z liaijun $".hashCode();
    private Long id;
    private Long flashSaleConfId;
    private Long stuffId;
    private Date createTime;
    private Date updateTime;
    private Integer displayOrder;

    @Override
    public String toString() {
        return "FlashSaleStuff{" +
                "id=" + id +
                ", flashSaleConfId=" + flashSaleConfId +
                ", stuffId=" + stuffId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", displayOrder=" + displayOrder +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlashSaleConfId() {
        return flashSaleConfId;
    }

    public void setFlashSaleConfId(Long flashSaleConfId) {
        this.flashSaleConfId = flashSaleConfId;
    }

    public Long getStuffId() {
        return stuffId;
    }

    public void setStuffId(Long stuffId) {
        this.stuffId = stuffId;
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

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}
