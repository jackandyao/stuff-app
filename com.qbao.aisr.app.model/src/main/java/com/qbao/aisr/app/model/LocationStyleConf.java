package com.qbao.aisr.app.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangping
 * @createTime 17/3/6 下午12:02
 * $$LastChangedDate: 2017-03-07 17:49:13 +0800 (Tue, 07 Mar 2017) $$
 * $$LastChangedRevision: 91 $$
 * $$LastChangedBy: wangping $$
 */
public class LocationStyleConf implements Serializable {
    private static final long serialVersionUID = "$Id: LocationStyleConf.java 91 2017-03-07 09:49:13Z wangping $".hashCode();

    private int id;
    private int styleId;
    private int locationIndex;
    private String name;
    private String size;
    private int count;
    private int type;
    private Date createTime;
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public int getLocationIndex() {
        return locationIndex;
    }

    public void setLocationIndex(int locationIndex) {
        this.locationIndex = locationIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof LocationStyleConf))
            return false;

        LocationStyleConf that = (LocationStyleConf) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getStyleId(), that.getStyleId())
                .append(getLocationIndex(), that.getLocationIndex())
                .append(getCount(), that.getCount())
                .append(getType(), that.getType())
                .append(getName(), that.getName())
                .append(getSize(), that.getSize())
                .append(getCreateTime(), that.getCreateTime())
                .append(getUpdateTime(), that.getUpdateTime())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getStyleId())
                .append(getLocationIndex())
                .append(getName())
                .append(getSize())
                .append(getCount())
                .append(getType())
                .append(getCreateTime())
                .append(getUpdateTime())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("styleId", styleId)
                .append("locationIndex", locationIndex)
                .append("name", name)
                .append("size", size)
                .append("count", count)
                .append("type", type)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .toString();
    }
}
