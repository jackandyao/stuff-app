package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-06 13:57:00 +0800 (Mon, 06 Mar 2017) $$
 * $$LastChangedRevision: 71 $$
 * $$LastChangedBy: liaijun $$
 */
public class TopPaiImg implements Serializable {
    private static final long serialVersionUID="$Id: TopPaiImg.java 71 2017-03-06 05:57:00Z liaijun $".hashCode();

    //
    private Integer id;
    // 图片路径
    private String imgUrl;
    // 图片对应的关键字
    private String key;
    // 统计数
    private Integer count;
    // 统计日期
    private Date createTime;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：图片路径
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取：图片路径
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置：图片对应的关键字
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取：图片对应的关键字
     */
    public String getKey() {
        return key;
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
