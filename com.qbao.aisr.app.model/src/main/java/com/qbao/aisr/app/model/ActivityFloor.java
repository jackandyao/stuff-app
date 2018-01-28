package com.qbao.aisr.app.model;

import java.util.List;

/**
 * 活动楼层项目
 * @author wangjg
 *
 */
public class ActivityFloor {
	
	//id
	private Long id;
	//名称
	private String name;
	//图片链接
	private String imgUrl;
	//跳转链接
	private String linkUrl;
	//位置id
	private Integer locationId;
	//楼层
	private Integer floorId;
	//@Transient
	private List<ActivityGoods> goods;
	
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
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public Integer getFloorId() {
		return floorId;
	}
	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}
	public List<ActivityGoods> getGoods() {
		return goods;
	}
	public void setGoods(List<ActivityGoods> goods) {
		this.goods = goods;
	}

}
