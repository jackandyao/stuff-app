package com.qbao.aisr.app.service.menu.impl;

import com.qbao.aisr.app.model.StuffMenu;
import com.qbao.aisr.app.repository.mybatis.dao.menu.StuffMenuDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.menu.IStuffMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/5 下午9:39
 * $$LastChangedDate: 2017-03-27 15:27:31 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 549 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class IStuffMenuServiceImpl implements IStuffMenuService {

    @Autowired
    private StuffMenuDao stuffMenuDao;
    
    @RedisCache(expire = 60*60, clazz = StuffMenu.class, cacheType = CacheType.LIST)
    public List<StuffMenu> findAllStuffMenu() {
        return stuffMenuDao.findAvailableMenus();

    }
}
