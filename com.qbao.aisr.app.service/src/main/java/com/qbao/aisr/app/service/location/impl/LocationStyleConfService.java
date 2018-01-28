package com.qbao.aisr.app.service.location.impl;

import com.qbao.aisr.app.model.LocationStyleConf;
import com.qbao.aisr.app.repository.mybatis.dao.location.LocationStyleConfDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.location.ILocationStyleConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/6 下午1:58
 * $$LastChangedDate: 2017-03-27 15:27:31 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 549 $$
 * $$LastChangedBy: wangping $$
 */

@Service
public class LocationStyleConfService implements ILocationStyleConfService {

    @Autowired
    private LocationStyleConfDao locationStyleConfDao;

    @RedisCache(expire = 60 * 30, clazz = LocationStyleConf.class, cacheType = CacheType.LIST)
    public List<LocationStyleConf> findConfByStyleId(int styleId) {
        return locationStyleConfDao.findLocationByStyleId(styleId);
    }

    @RedisCache(expire = 60 * 30, clazz = LocationStyleConf.class, cacheType = CacheType.OBJECT)
    public LocationStyleConf findConfById(int id) {
        return locationStyleConfDao.findConfById(id);
    }
}
