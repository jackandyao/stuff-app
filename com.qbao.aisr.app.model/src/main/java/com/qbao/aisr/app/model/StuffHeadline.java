package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 好货头条
 * @author liaijun
 * @email liaijun@qbao.com
 * @date 2017-03-06 11:14:21
 */
public class StuffHeadline implements Serializable {
    private static final long serialVersionUID="$Id: StuffHeadline.java 92 2017-03-07 09:52:42Z allen $".hashCode();
	//
	private Integer id;
	//好货头条
	private String msg;
	//头条详细页面
	private String linkUrl;
	//  头条状态 0 : 下架 1: 上架
	private Integer status;
	//上架时间
	private Date onTime;
	//下架时间
	private Date offTime;
	//
	private Date createTime;
	//
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：好货头条
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * 获取：好货头条
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * 设置：头条详细页面
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	/**
	 * 获取：头条详细页面
	 */
	public String getLinkUrl() {
		return linkUrl;
	}
	/**
	 * 设置：  头条状态 0 : 下架 1: 上架
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：  头条状态 0 : 下架 1: 上架
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：上架时间
	 */
	public void setOnTime(Date onTime) {
		this.onTime = onTime;
	}
	/**
	 * 获取：上架时间
	 */
	public Date getOnTime() {
		return onTime;
	}
	/**
	 * 设置：下架时间
	 */
	public void setOffTime(Date offTime) {
		this.offTime = offTime;
	}
	/**
	 * 获取：下架时间
	 */
	public Date getOffTime() {
		return offTime;
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
