package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;



/**
 * 限时购活动商品配置表
 * 
 * @author liaijun
 * @email liaijun@qbao.com
 * @date 2017-06-02 15:37:03
 */
public class BannerFlashSaleStuffConf implements Serializable {
	private static final long serialVersionUID="$Id: BannerFlashSaleStuffConf.java 1116 2017-06-07 10:30:21Z wangping $".hashCode();
	
	//主键id
	private Long id;
	//stuff_promtion 表 商品名称
	private String stuffName;
	//ad_banner 表 id
	private Integer bannerId;
	//stuff_promtion 表 id
	private Long stuffId;
	//备注
	private String memo;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//广告主
	private String advertiser;
	//上架时间
	private Date onTime;
	//下架时间
	private Date offTime;

	/**
	 * 设置：主键id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：stuff_promtion 表 商品名称
	 */
	public void setStuffName(String stuffName) {
		this.stuffName = stuffName;
	}
	/**
	 * 获取：stuff_promtion 表 商品名称
	 */
	public String getStuffName() {
		return stuffName;
	}
	/**
	 * 设置：ad_banner 表 id
	 */
	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}
	/**
	 * 获取：ad_banner 表 id
	 */
	public Integer getBannerId() {
		return bannerId;
	}
	/**
	 * 设置：stuff_promtion 表 id
	 */
	public void setStuffId(Long stuffId) {
		this.stuffId = stuffId;
	}
	/**
	 * 获取：stuff_promtion 表 id
	 */
	public Long getStuffId() {
		return stuffId;
	}
	/**
	 * 设置：备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 获取：备注
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：广告主
	 */
	public void setAdvertiser(String advertiser) {
		this.advertiser = advertiser;
	}
	/**
	 * 获取：广告主
	 */
	public String getAdvertiser() {
		return advertiser;
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
}
