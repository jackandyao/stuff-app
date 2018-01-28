package com.qbao.aisr.app.service.ad.impl;

import com.qbao.aisr.app.model.AdBanner;
import com.qbao.aisr.app.model.BannerFlashSaleStuffConf;
import com.qbao.aisr.app.model.BannerStuffConf;
import com.qbao.aisr.app.repository.mybatis.dao.ad.AdBannerDao;
import com.qbao.aisr.app.repository.mybatis.dao.ad.BannerSaleStuffConfDao;
import com.qbao.aisr.app.repository.mybatis.dao.ad.BannerStuffConfDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.ad.IAdBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-06-07 18:30:21 +0800 (Wed, 07 Jun 2017) $$
 * $$LastChangedRevision: 1116 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class AdBannerServiceImpl implements IAdBannerService {

    @Autowired
    private AdBannerDao adBannerDao;
    @Autowired
    private BannerStuffConfDao bannerStuffConfDao;

    @Autowired
    private BannerSaleStuffConfDao bannerSaleStuffConfDao;

    //    public List<AdBanner> findBannerByModuleAndLocationId(int moduleId, int locationId, int limit) {
    //        return adBannerDao.findBannerByModuleAndLocationId(moduleId, locationId, limit, new Date());
    //    }

    @RedisCache(expire = 60 * 30, clazz = AdBanner.class, cacheType = CacheType.LIST)
    public List<AdBanner> findBannerByLocationId(int locationId,  int limit) {
        return adBannerDao.findBannerByLocationId(locationId, limit, new Date());
    }

    @RedisCache(expire = 60 * 30, clazz = BannerStuffConf.class, cacheType = CacheType.LIST)
    public List<BannerStuffConf> findBannerStuffConfByBannerId(Integer bannerId) {
        return bannerStuffConfDao.findBannerStuffConfByBannerId(bannerId);
    }

    @RedisCache(expire = 60 * 30, clazz = AdBanner.class, cacheType = CacheType.OBJECT)
    public AdBanner findBannerById(Integer id) {
        return adBannerDao.findBannerById(id);
    }


    @RedisCache(expire = 60 * 30, clazz = BannerFlashSaleStuffConf.class, cacheType = CacheType.LIST)
    public List<BannerFlashSaleStuffConf> findBannerSaleStuffConfByBannerId(Integer bannerId) {
        return  bannerSaleStuffConfDao.findBannerSaleStuffConfByBannerId(bannerId,new Date());
    }
}
