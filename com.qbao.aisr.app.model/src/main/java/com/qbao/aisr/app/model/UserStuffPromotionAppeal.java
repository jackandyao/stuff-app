package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户申诉数据表
 * 
 * @author liaijun
 * @email liaijun@qbao.com
 * @date 2017-06-01 09:53:44
 */
public class UserStuffPromotionAppeal implements Serializable {
    private static final long serialVersionUID = "$Id: UserStuffPromotionAppeal.java 1064 2017-06-01 03:23:47Z liaijun $".hashCode();

    // 主键id
    private Long id;
    // 用户id
    private Long userId;
    // 订单id
    private String orderId;
    // 订单来源 tmall, taobao,jd
    private String source;
    // 申诉状态: -1 提交申诉,0 平台审核, 1受理申诉 ,2 用户取消申诉,3申诉反馈
    private Integer status;
    // 申诉问题描述
    private String content;
    // 图片路径 多个路径用逗号分割
    private String imgUrl;
    // 设备类型1: ios , 2 :android'
    private Integer device;
    // 手机品牌
    private String phoneBrand;
    // 手机型号
    private String phoneType;
    // 申诉原因
    private String reason;
    // 补宝券/宝币值
    private Integer rebateValue;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date updateTime;
    // 处理者
    private String ops;
    // 备注原因
    private String comment;
    // 申诉人qq号码
    private String qq;
    // 申诉人手机号码
    private String phone;

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
     * 设置：用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置：订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取：订单id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置：订单来源 tmall, taobao,jd
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取：订单来源 tmall, taobao,jd
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置：申诉状态: -1 提交申诉,0 平台审核, 1受理申诉 ,2 用户取消申诉,3申诉反馈
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：申诉状态: -1 提交申诉,0 平台审核, 1受理申诉 ,2 用户取消申诉,3申诉反馈
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：申诉问题描述
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：申诉问题描述
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：图片路径 多个路径用逗号分割
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取：图片路径 多个路径用逗号分割
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置：设备类型1: ios , 2 :android'
     */
    public void setDevice(Integer device) {
        this.device = device;
    }

    /**
     * 获取：设备类型1: ios , 2 :android'
     */
    public Integer getDevice() {
        return device;
    }

    /**
     * 设置：手机品牌
     */
    public void setPhoneBrand(String phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    /**
     * 获取：手机品牌
     */
    public String getPhoneBrand() {
        return phoneBrand;
    }

    /**
     * 设置：手机型号
     */
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    /**
     * 获取：手机型号
     */
    public String getPhoneType() {
        return phoneType;
    }

    /**
     * 设置：申诉原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 获取：申诉原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置：补宝券/宝币值
     */
    public void setRebateValue(Integer rebateValue) {
        this.rebateValue = rebateValue;
    }

    /**
     * 获取：补宝券/宝币值
     */
    public Integer getRebateValue() {
        return rebateValue;
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
     * 设置：修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置：处理者
     */
    public void setOps(String ops) {
        this.ops = ops;
    }

    /**
     * 获取：处理者
     */
    public String getOps() {
        return ops;
    }

    /**
     * 设置：备注原因
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取：备注原因
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置：申诉人qq号码
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取：申诉人qq号码
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置：申诉人手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取：申诉人手机号码
     */
    public String getPhone() {
        return phone;
    }
}
