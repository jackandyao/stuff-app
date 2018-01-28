package com.qbao.aisr.app.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author zhangjun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-07 17:49:13 +0800 (Tue, 07 Mar 2017) $$
 * $$LastChangedRevision: 91 $$
 * $$LastChangedBy: wangping $$
 */
public class QbaozyClassesDto implements Serializable {
    private static final long serialVersionUID="$Id: QbaozyClasses.java 91 2017-06-11 09:49:13Z zhangjun $".hashCode();

    private Long id;
    private Long activityId;
    private Integer sort;
    private String dirName;
    private String url;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
    
    
}
