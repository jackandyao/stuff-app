package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-27 17:26:04 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 555 $$
 * $$LastChangedBy: liaijun $$
 */
public class UserStuffPai implements Serializable {
    private static final long serialVersionUID = "$Id: UserStuffPai.java 555 2017-03-27 09:26:04Z liaijun $".hashCode();

    //
    private Integer id;
    // 图片路径
    private String imgUrl;
    // 图片对应的关键字
    private String key;
    //
    private Long userId;
    // 统计日期
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserStuffPai{" + "id=" + id + ", path='" + imgUrl + '\'' + ", key='" + key + '\'' + ", userId=" + userId + ", createTime=" + createTime + '}';
    }
}
