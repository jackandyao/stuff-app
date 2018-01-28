package com.qbao.aisr.app.service.ju.impl;

import com.qbao.aisr.app.model.StuffHeadline;
import com.qbao.aisr.app.repository.mybatis.dao.ju.StuffHeadlineDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.ju.IStuffHeadlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/6 下午1:51
 * $$LastChangedDate: 2017-03-27 10:50:26 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 541 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class StuffHeadlineServiceImpl implements IStuffHeadlineService {
    @Autowired
    private StuffHeadlineDao stuffHeadlineDao;

    @RedisCache(expire = 60 * 10, clazz = StuffHeadline.class, cacheType = CacheType.LIST)
    public List<StuffHeadline> findStuffPromotionByCatId(int size) {
        if(size<1){
            size=0;
        }
        return stuffHeadlineDao.findStuffPromotionByCatId(size,new Date());
    }
}
