package com.qbao.aisr.app.service.facade;

import com.qbao.aisr.app.model.CommMsg;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.comm.ICommMsgService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangjun
 * @createTime 17/5/30 上午11:59
 * $$LastChangedDate: 2017-03-30 16:51:33 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 595 $$
 * $$LastChangedBy: zhangjun $$
 */
@Component
public class CommMsgFacade {
	
	private Logger logger = Logger.getLogger(CommMsgFacade.class);
	
    @Autowired
    private ICommMsgService commMsgService;
    
    @RedisCache(expire = 60 * 30, clazz = CommMsg.class, cacheType = CacheType.OBJECT)
    public CommMsg findByTypeId(int typeId) {
        CommMsg msg = commMsgService.findByTypeId(typeId);
        logger.info("get  commMsg, the  typeId=[" + typeId + "]");
        return msg;
    }

}
