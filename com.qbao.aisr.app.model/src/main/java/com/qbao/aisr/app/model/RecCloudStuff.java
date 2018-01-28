package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/3/13 下午2:17
 * $$LastChangedDate: 2017-03-13 15:48:46 +0800 (Mon, 13 Mar 2017) $$
 * $$LastChangedRevision: 179 $$
 * $$LastChangedBy: allen $$
 */
public class RecCloudStuff implements Serializable {
    private static final long serialVersionUID="$Id: RecCloudStuff.java 179 2017-03-13 07:48:46Z allen $".hashCode();

    //用户id
    private Long userId;

    //商品结果集
    private String stuffIds;

    private Date createTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStuffIds() {
        return stuffIds;
    }

    public void setStuffIds(String stuffIds) {
        this.stuffIds = stuffIds;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "RecCloudStuff{" +
                "userId=" + userId +
                ", stuffIds='" + stuffIds + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
