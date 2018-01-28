package com.qbao.aisr.app.service.ad.impl;

import com.qbao.aisr.app.model.AdStuff;
import com.qbao.aisr.app.repository.mybatis.dao.ad.AdStuffDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.ad.IAdStuffService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/6 下午1:28
 * $$LastChangedDate: 2017-03-27 15:27:31 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 549 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class AdStuffServiceImpl implements IAdStuffService {

    @Autowired
    private AdStuffDao adStuffDao;
    //
    //    public List<AdStuff> findByModuelAndLocationId(int moduleId, int locationId, int limit) {
    //        return adStuffDao.findByModuleLocationId(moduleId, locationId, limit, new Date());
    //    }

    @RedisCache(expire = 60 * 30, clazz = AdStuff.class, cacheType = CacheType.LIST)
    public List<AdStuff> findByLocationId(int locationId, int limit) {
        return adStuffDao.findByLocationId(locationId, limit, new Date());
    }

    @Override
    @RedisCache(expire = 60 * 30, clazz = AdStuff.class, cacheType = CacheType.LIST)
    public List<AdStuff> findByLocationId(int locationId) {
        return adStuffDao.findAllByLocationId(locationId, new Date());
    }
    @RedisCache(expire = 60 * 30, clazz = AdStuff.class, cacheType = CacheType.LIST)
    public List<AdStuff> findBySource(String source, int limit) {
        return adStuffDao.findBySource(StringUtils.lowerCase(source), limit, new Date());
    }

    @RedisCache(expire = 60 * 30, clazz = AdStuff.class, cacheType = CacheType.LIST)
    public List<AdStuff> findByNotFrom(String source, int limit) {
        return adStuffDao.findByNotFrom(StringUtils.lowerCase(source), limit, new Date());
    }
}
