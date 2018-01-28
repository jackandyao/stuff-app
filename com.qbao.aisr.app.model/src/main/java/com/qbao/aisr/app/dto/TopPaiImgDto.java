package com.qbao.aisr.app.dto;

import java.io.Serializable;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-05-09 14:04:32 +0800 (Tue, 09 May 2017) $$
 * $$LastChangedRevision: 793 $$
 * $$LastChangedBy: wangping $$
 */
public class TopPaiImgDto implements Serializable {
    private static final long serialVersionUID = "$Id: TopPaiImgDto.java 793 2017-05-09 06:04:32Z wangping $".hashCode();

    //
    private Long id;
    // 图片路径
    private String imgUrl;
    // 图片对应的关键字
    private String key;
    // 统计数
    private Integer count;

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

    @Override
    public String toString() {
        return "TopPaiImgDto{" + "id=" + id + ", imgUrl='" + imgUrl + '\'' + ", key='" + key + '\'' + ", count=" + count + '}';
    }
}
