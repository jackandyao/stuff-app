package com.qbao.aisr.app.repository.mybatis.dao.message;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.qbao.aisr.app.model.SysMessage;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

/**
 * 
     * @author 贾红平
     * 通知消息访问数据接口
     * $LastChangedDate: 2017-05-17 18:35:14 +0800 (Wed, 17 May 2017) $
     * $LastChangedRevision: 868 $
     * $LastChangedBy: jiahongping $
 */
@Component
public interface SysMessageDao {
    
    @Select("select id,user_id,message_content,message_icon,message_status,message_url,message_title,create_time,update_time from sys_message where user_id=#{userId}")
    @ResultMap("SysMessageMap")
    @DataSource("stuffSlaveDataSource")
    public abstract List<SysMessage>querySysMessageByUserId(@Param("userId")Long userId);
    
    @DataSource("stuffSlaveDataSource")
    public abstract int updateSysMessageStatus(SysMessage sysMessage);
   
}
