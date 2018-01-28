package com.qbao.aisr.app.service.ad.impl;

import com.qbao.aisr.app.model.AdJu;
import com.qbao.aisr.app.repository.mybatis.dao.ad.AdJuDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.ad.IAdJuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/7 上午10:33
 * $$LastChangedDate: 2017-03-27 15:27:31 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 549 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class AdJuServiceImpl implements IAdJuService {
    @Autowired
    private AdJuDao adJuDao;
    @RedisCache(expire = 60 * 30, clazz = AdJu.class, cacheType = CacheType.LIST)
    public List<AdJu> findByLocationId(int locationId, int limit) {
        return adJuDao.findByLocationId(locationId, limit, new Date());
    }
}
