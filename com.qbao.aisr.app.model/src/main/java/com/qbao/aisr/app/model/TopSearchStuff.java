package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author liaijun
 * @email liaijun@qbao.com
 * @date 2017-03-06 15:12:58
 */
public class TopSearchStuff implements Serializable {
    private static final long serialVersionUID = "$Id: TopSearchStuff.java 131 2017-03-11 06:45:19Z louxueming $".hashCode();

    //
    private long id;
    // 商品id
    private Long stuffId;
    // 统计数
    private Integer count;
    // 统计日期
    private Date createTime;

    /**
     * 设置：
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public long getId() {
        return id;
    }

    public Long getStuffId() {
        return stuffId;
    }

    public void setStuffId(Long stuffId) {
        this.stuffId = stuffId;
    }

    /**
     * 设置：统计数
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取：统计数
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置：统计日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：统计日期
     */
    public Date getCreateTime() {
        return createTime;
    }
}
