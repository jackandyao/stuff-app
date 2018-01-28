package com.qbao.aisr.app.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangping
 * @createTime 17/3/6 上午9:34
 * $$LastChangedDate: 2017-03-07 17:49:13 +0800 (Tue, 07 Mar 2017) $$
 * $$LastChangedRevision: 91 $$
 * $$LastChangedBy: wangping $$
 */
public class StuffModule implements Serializable {

    private static final long serialVersionUID = "$Id: StuffModule.java 91 2017-03-07 09:49:13Z wangping $".hashCode();
    private int id;
    private String title;
    private int styleId;
    private int status;
    private String more;
    private String titleIcon;
    private int displayOrder;
    private Date createTime;
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getTitleIcon() {
        return titleIcon;
    }

    public void setTitleIcon(String titleIcon) {
        this.titleIcon = titleIcon;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
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
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("styleId", styleId)
                .append("status", status)
                .append("more", more)
                .append("titleIcon", titleIcon)
                .append("displayOrder", displayOrder)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof StuffModule))
            return false;

        StuffModule that = (StuffModule) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getStyleId(), that.getStyleId())
                .append(getStatus(), that.getStatus())
                .append(getDisplayOrder(), that.getDisplayOrder())
                .append(getTitle(), that.getTitle())
                .append(getMore(), that.getMore())
                .append(getTitleIcon(), that.getTitleIcon())
                .append(getCreateTime(), that.getCreateTime())
                .append(getUpdateTime(), that.getUpdateTime())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getTitle())
                .append(getStyleId())
                .append(getStatus())
                .append(getMore())
                .append(getTitleIcon())
                .append(getDisplayOrder())
                .append(getCreateTime())
                .append(getUpdateTime())
                .toHashCode();
    }
}
