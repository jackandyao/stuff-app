package com.qbao.aisr.app.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-03 17:59:07 +0800 (Fri, 03 Mar 2017) $$
 * $$LastChangedRevision: 53 $$
 * $$LastChangedBy: wangping $$
 */
public class TagDetail implements Serializable {

    private static final long serialVersionUID = "$Id: TagDetail.java 53 2017-03-03 09:59:07Z wangping $".hashCode();
    private Long id; //tag_detail id;
    private String name;
    private String icon;
    private Long count;
    private Integer tagTypeId;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getTagTypeId() {
        return tagTypeId;
    }

    public void setTagTypeId(Integer tagTypeId) {
        this.tagTypeId = tagTypeId;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("icon", icon)
                .append("count", count)
                .append("tagTypeId", tagTypeId)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .toString();
    }
}
