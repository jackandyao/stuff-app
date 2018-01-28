package com.qbao.aisr.app.service.ad.impl;

import com.qbao.aisr.app.model.AdShop;
import com.qbao.aisr.app.repository.mybatis.dao.ad.AdShopDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.ad.IAdShopService;
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
public class AdShopServiceImpl implements IAdShopService {

    @Autowired
    private AdShopDao adShopDao;

    @RedisCache(expire = 60 * 30, clazz = AdShop.class, cacheType = CacheType.LIST)
    public List<AdShop> findByLocationId(int locationId, int limit) {

        return adShopDao.findByLocationId(locationId, limit, new Date());
    }
}
