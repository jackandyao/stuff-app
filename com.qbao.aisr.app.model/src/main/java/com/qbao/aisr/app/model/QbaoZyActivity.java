package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author zhangjun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-07 17:49:13 +0800 (Tue, 07 Mar 2017) $$
 * $$LastChangedRevision: 91 $$
 * $$LastChangedBy: zhangjun $$
 */
public class QbaoZyActivity implements Serializable {
    private static final long serialVersionUID="$Id: QbaozyClasses.java 91 2017-06-11 09:49:13Z zhangjun $".hashCode();

    private Long id;
    private String name;
    private Date onTime;
    private Date offTime;
    private Integer status;
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

	public Date getOnTime() {
		return onTime;
	}

	public void setOnTime(Date onTime) {
		this.onTime = onTime;
	}

	public Date getOffTime() {
		return offTime;
	}

	public void setOffTime(Date offTime) {
		this.offTime = offTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
