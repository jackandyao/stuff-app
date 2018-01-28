package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author liaijun
 * @email liaijun@qbao.com
 * @date 2017-03-06 17:30:44
 */
public class StuffJu implements Serializable {
    private static final long serialVersionUID = "$Id: StuffJu.java 83 2017-03-07 03:35:17Z wangping $".hashCode();
    //
    private Long id;
    // 商品类目id
    private String catId;
    // 聚好货名称
    private String name;
    //
    private String imgUrl;
    //
    private String linkUrl;
    // 状态 0:审核,1:上架,2下架
    private Integer status;
    //
    private Date createTime;
    //
    private Date updateTime;
    // 上架时间
    private Date onTime;
    // 下架时间
    private Date offTime;

    public Date getOffTime() {
        return offTime;
    }

    public void setOffTime(Date offTime) {
        this.offTime = offTime;
    }

    public Date getOnTime() {
        return onTime;
    }

    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }

    /**
     * 
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
     * 设置：商品类目id
     */
    public void setCatId(String catId) {
        this.catId = catId;
    }

    /**
     * 获取：商品类目id
     */
    public String getCatId() {
        return catId;
    }

    /**
     * 设置：聚好货名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：聚好货名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取：
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置：
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /**
     * 获取：
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * 设置：状态 0:审核,1:上架,2下架
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态 0:审核,1:上架,2下架
     */
    public Integer getStatus() {
        return status;
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
}
