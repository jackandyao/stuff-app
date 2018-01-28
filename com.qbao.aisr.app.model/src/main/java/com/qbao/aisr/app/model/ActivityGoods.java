package com.qbao.aisr.app.model;

/**
 * 活动商品
 * @author wangjg
 *
 */
public class ActivityGoods {

	//商品id
	private Long stuffId;
	//商品名称
	private String name;
	//商品图片链接
	private String imgUrl;
	//商品链接
	private String url;
	//销量
	private Integer orderNum;
	//商品原价
	private Double reservePrice;
	//商品最终价格
	private Double finalPrice;
	//券后价
	private Double couponPrice;
	//优惠券金额
	private Double couponAmount;
	//领券购买链接
	private String couponLink;
	//推荐理由
	private String copyWriter;
	//商品来源
	private String source;
//	@Transient
	private Integer point;//积分

	public Long getStuffId() {
		return stuffId;
	}

	public void setStuffId(Long stuffId) {
		this.stuffId = stuffId;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(Double reservePrice) {
		this.reservePrice = reservePrice;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Double getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(Double couponPrice) {
		this.couponPrice = couponPrice;
	}

	public String getCopyWriter() {
		return copyWriter;
	}

	public void setCopyWriter(String copyWriter) {
		this.copyWriter = copyWriter;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getCouponLink() {
		return couponLink;
	}

	public void setCouponLink(String couponLink) {
		this.couponLink = couponLink;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Double getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(Double couponAmount) {
		this.couponAmount = couponAmount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
