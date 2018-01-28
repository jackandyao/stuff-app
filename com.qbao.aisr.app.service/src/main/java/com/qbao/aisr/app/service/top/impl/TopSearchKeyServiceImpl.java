package com.qbao.aisr.app.service.top.impl;

import com.qbao.aisr.app.model.TopSearchKey;
import com.qbao.aisr.app.repository.mybatis.dao.top.TopSearchKeyDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.top.ITopSearchKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/6 下午3:33
 * $$LastChangedDate: 2017-03-07 10:25:05 +0800 (周二, 07 三月 2017) $$
 * $$LastChangedRevision: 81 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class TopSearchKeyServiceImpl implements ITopSearchKeyService {
    @Autowired
    private TopSearchKeyDao topSearchKeyDao;

    @RedisCache(expire = 60*30,clazz =TopSearchKey.class,cacheType = CacheType.LIST)
    public List<TopSearchKey> topSearchKey(int size) {
        if (size < 1) {
            size = 0;
        }
        return topSearchKeyDao.topSearchKey(size);
    }
}
