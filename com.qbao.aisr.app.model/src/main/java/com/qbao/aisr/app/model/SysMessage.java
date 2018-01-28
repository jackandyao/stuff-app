package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.qbao.aisr.app.dto.StuffRecommendDto;
import com.qbao.aisr.app.dto.SysMessageDto;

/**
 * 
     * @author 贾红平
     *
     * $LastChangedDate: 2017-05-17 13:47:49 +0800 (Wed, 17 May 2017) $
     * $LastChangedRevision: 857 $
     * $LastChangedBy: jiahongping $
 */
public class SysMessage implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    //主键ID
    private int id;
    //信息内容URL
    private String message_url;
    //消息标题
    private String message_title;
    //消息内容
    private String message_content;
    //消息图标
    private String message_icon;
    //用户编号
    private Long user_id;
    //消息状态
    private int message_status;
    //消息创建时间
    private Date create_time;
    //消息更新时间
    private Date update_time;
    
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMessage_url() {
        return message_url;
    }
    public void setMessage_url(String message_url) {
        this.message_url = message_url;
    }
    public String getMessage_title() {
        return message_title;
    }
    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }
    public String getMessage_content() {
        return message_content;
    }
    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }
    public String getMessage_icon() {
        return message_icon;
    }
    public void setMessage_icon(String message_icon) {
        this.message_icon = message_icon;
    }
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public int getMessage_status() {
        return message_status;
    }
    public void setMessage_status(int message_status) {
        this.message_status = message_status;
    }
    public Date getCreate_time() {
        return create_time;
    }
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    public Date getUpdate_time() {
        return update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("message_icon", message_icon)
                .append("message_title", message_title)
                .append("message_content", message_content)
                .append("message_url", message_url)
                .append("user_id", user_id)
                .append("message_status", message_status)
                .append("create_time", create_time)
                .append("update_time", update_time)
                .toString();
    }

}
