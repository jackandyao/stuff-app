package com.qbao.aisr.app.service.facade;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.qbao.aisr.app.dto.BannerDto;
import com.qbao.aisr.app.dto.SysMessageDto;
import com.qbao.aisr.app.model.SysMessage;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.message.ISysMessageService;

/**
 * 
     * @author 贾红平
     * 封装消息的面板
     * $LastChangedDate: 2017-05-18 11:45:01 +0800 (Thu, 18 May 2017) $
     * $LastChangedRevision: 874 $
     * $LastChangedBy: jiahongping $
 */
@Component
public class SysMessageFacade {
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private ISysMessageService sysMessageService;
    
    ///@RedisCache(expire = 60 * 30, clazz = SysMessageDto.class, cacheType = CacheType.LIST)
    public List<SysMessageDto>findSysMessage(Long userId){
        List<SysMessage> slist= sysMessageService.querySysMessageByUserId(userId);
        List<SysMessageDto>sdtoList= Lists.newArrayList();
        if (slist!=null & slist.size()>0) {
            for(SysMessage mess:slist){
                SysMessageDto sdto = new SysMessageDto();
                sdto.setId(mess.getId());
                sdto.setMessageContent(mess.getMessage_content());
                sdto.setMessageIcon(mess.getMessage_icon());
                sdto.setMessageTitle(mess.getMessage_title());
                sdto.setMessageStatus(mess.getMessage_status());
                sdto.setUser_id(mess.getUser_id());
                sdto.setMessageUrl(mess.getMessage_url());
                sdto.setCreateTime(mess.getCreate_time());
                sdtoList.add(sdto);
            }
        }
        logger.info("sysmessage list size:"+sdtoList.size());
        return sdtoList;
    }
    
    public int updateSysMessage(SysMessage sysMessage){
        return sysMessageService.updateSysMessageStatus(sysMessage);
    }
}
