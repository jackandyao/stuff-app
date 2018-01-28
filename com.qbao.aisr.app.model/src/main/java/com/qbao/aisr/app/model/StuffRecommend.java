package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author xueming
 * @email louxueming@qbao.com
 * @date 2017-03-06 15:12:58
 */
public class StuffRecommend implements Serializable {
    private static final long serialVersionUID = "$Id: StuffRecommend.java 102 2017-03-08 06:51:26Z wangping $".hashCode();
    private long userId;
    private String stuffIds;
    private String buyIds;
    private String viewIds;
    private String likeIds;
    private Date createTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStuffIds() {
        return stuffIds;
    }

    public void setStuffIds(String stuffIds) {
        this.stuffIds = stuffIds;
    }
    
    public String getBuyIds() {
		return buyIds;
	}

	public void setBuyIds(String buyIds) {
		this.buyIds = buyIds;
	}

	public String getViewIds() {
		return viewIds;
	}

	public void setViewIds(String viewIds) {
		this.viewIds = viewIds;
	}

	public String getLikeIds() {
		return likeIds;
	}

	public void setLikeIds(String likeIds) {
		this.likeIds = likeIds;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
