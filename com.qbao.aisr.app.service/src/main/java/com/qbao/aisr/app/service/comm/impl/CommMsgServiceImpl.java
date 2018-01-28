package com.qbao.aisr.app.service.comm.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.aisr.app.model.CommMsg;
import com.qbao.aisr.app.repository.mybatis.dao.comm.CommMsgDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.comm.ICommMsgService;

/**
 * @author zhangjun
 * @createTime 17/3/7 上午10:33
 * $$LastChangedDate: 2017-03-27 15:27:31 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 549 $$
 * $$LastChangedBy: zhangjun $$
 */
@Service
public class CommMsgServiceImpl implements ICommMsgService {
    @Autowired
    private CommMsgDao commMsgDao;
    @RedisCache(expire = 60 * 30, clazz = CommMsg.class, cacheType = CacheType.OBJECT)
    public CommMsg findByTypeId(int typeId) {
        return commMsgDao.findByTypeId(typeId);
    }
}
