package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author zhangjun
 * @email sjzhangjun@qbao.com
 * @date 2017-05-30 9:30:44
 */
public class CommMsg implements Serializable {
	private static final long serialVersionUID = "$Id$".hashCode();

	private long msgId; // 主键
	private String msg; // 消息内容
	private int type; // 0, 活动 1 返券规则
	private String title; // 消息标题
	private String imgUrl; // 图片URL
	private String linkUrl; // 消息链接地址
	private int status; // 消息状态 0 下架 1 上架
	private Date createTime; // 创建时间
	private Date updateTime; // 更新时间
	private Date onTime; // 消息上架时间
	private Date offTime; // 消息下架时间

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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

}
