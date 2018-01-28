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
public class TopSearchKey implements Serializable {
    private static final long serialVersionUID = "$Id: TopSearchKey.java 102 2017-03-08 06:51:26Z wangping $".hashCode();

    //
    private long id;
    // 搜索关键字
    private String key;
    // URL
    private String url;
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

    /**
     * 设置：搜索关键字
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取：搜索关键字
     */
    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
