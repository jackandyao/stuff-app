package com.qbao.aisr.app.service.ju.impl;

import com.qbao.aisr.app.dto.StuffHotClassDto;
import com.qbao.aisr.app.model.StuffJu;
import com.qbao.aisr.app.repository.mybatis.dao.ju.StuffJuDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.ju.IStuffJuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/6 下午5:38
 * $$LastChangedDate: 2017-03-24 16:26:00 +0800 (Fri, 24 Mar 2017) $$
 * $$LastChangedRevision: 531 $$
 * $$LastChangedBy: liaijun $$
 */
@Service
public class StuffJuServiceImpl implements IStuffJuService {
    @Autowired
    private StuffJuDao stuffJuDao;

    @RedisCache(expire = 60 * 2, clazz = StuffJu.class, cacheType = CacheType.LIST)
    public List<StuffJu> findStuffJu(int size) {
        return stuffJuDao.findStuffJu(size, new Date());
    }

    @RedisCache(expire = 60 * 2, clazz = StuffJu.class, cacheType = CacheType.OBJECT)
    public StuffJu findStuffJuById(long id) {
        return stuffJuDao.findStuffJuById(id, new Date());
    }
}
