package com.qbao.aisr.app.service.ad.impl;

import com.qbao.aisr.app.model.AdQbao;
import com.qbao.aisr.app.repository.mybatis.dao.ad.AdQbaoDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.ad.IAdQbaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-27 15:27:31 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 549 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class AdQbaoServiceImpl implements IAdQbaoService {

    @Autowired
    private AdQbaoDao adQbaoDao;

    @Override
    @RedisCache(expire = 60 * 30, clazz = AdQbao.class, cacheType = CacheType.LIST)
    public List<AdQbao> findByLocationId(int locationId, int limit) {
        return adQbaoDao.findBannerByLocationId(locationId,limit,new Date());
    }
}
