package com.qbao.aisr.app.dto;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;
@Generated("net.hexar.json2pojo")
public class SysMessageDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
   
    private int id;
    
    private Long user_id;
    
    private String messageIcon;
  
    private String messageTitle;
   
    private String messageContent;
   
    private String messageUrl;
    
    private int messageStatus;
    
    private Date createTime;
    
    private Date updateTime;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public String getMessageIcon() {
        return messageIcon;
    }
    public void setMessageIcon(String messageIcon) {
        this.messageIcon = messageIcon;
    }
    public String getMessageTitle() {
        return messageTitle;
    }
    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }
    public String getMessageContent() {
        return messageContent;
    }
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
    public String getMessageUrl() {
        return messageUrl;
    }
    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }
    public int getMessageStatus() {
        return messageStatus;
    }
    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
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
    
    public SysMessageDto build() {
        SysMessageDto sysMessageDto = new SysMessageDto();
        sysMessageDto.id=this.id;
        sysMessageDto.messageIcon=this.messageIcon;
        sysMessageDto.messageTitle=this.messageTitle;
        sysMessageDto.messageContent=this.messageContent;
        sysMessageDto.messageStatus=this.messageStatus;
        sysMessageDto.messageUrl=this.messageUrl;
        sysMessageDto.createTime=this.createTime;
        sysMessageDto.updateTime=this.updateTime;
        return sysMessageDto;
    }

}
