package com.qbao.aisr.app.service.module.impl;

import com.qbao.aisr.app.model.StuffModule;
import com.qbao.aisr.app.repository.mybatis.dao.module.StuffModuleDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.module.IStuffModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/6 上午10:28
 * $$LastChangedDate: 2017-03-27 15:27:31 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 549 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class StuffModuleServiceImpl implements IStuffModuleService {

    @Autowired
    private StuffModuleDao stuffModuleDao;

    @RedisCache(expire = 60 * 60, clazz = StuffModule.class, cacheType = CacheType.LIST)
    public List<StuffModule> findAvailableModules() {
        return stuffModuleDao.findAvailableModules();
    }

}
