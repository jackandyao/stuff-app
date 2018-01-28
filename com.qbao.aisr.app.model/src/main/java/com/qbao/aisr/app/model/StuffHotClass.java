package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author liaijun
 * @email liaijun@qbao.com
 * @date 2017-03-10 16:50:57
 */
public class StuffHotClass implements Serializable {
    private static final long serialVersionUID = "$Id: StuffHotClass.java 124 2017-03-10 10:15:26Z allen $".hashCode();

    //
    private Long id;
    //
    private Integer sort;
    //
    private String dirName;
    //
    private String url;
    //
    private Date createTime;
    //
    private Date updateTime;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取：
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置：
     */
    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    /**
     * 获取：
     */
    public String getDirName() {
        return dirName;
    }

    /**
     * 设置：
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return "StuffHotClass{" + "id=" + id + ", sort=" + sort + ", dirName='" + dirName + '\'' + ", url='" + url + '\'' + ", createTime=" + createTime + ", updateTime=" + updateTime + '}';
    }
}
