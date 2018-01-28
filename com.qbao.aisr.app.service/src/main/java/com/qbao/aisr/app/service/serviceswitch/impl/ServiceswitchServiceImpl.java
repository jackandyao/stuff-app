package com.qbao.aisr.app.service.serviceswitch.impl;

import com.qbao.aisr.app.model.StuffServiceSwitch;
import com.qbao.aisr.app.repository.mybatis.dao.serviceswitch.StuffServiceSwitchDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.serviceswitch.IServiceswitchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangjun
 * @createTime 2017/3/30 16:43
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Service
public class ServiceswitchServiceImpl implements IServiceswitchService {

    Logger logger = LoggerFactory.getLogger(ServiceswitchServiceImpl.class);

    @Autowired
    StuffServiceSwitchDao stuffServiceSwitchDao;

	@Override
	@RedisCache(expire = 60 * 60 , clazz = StuffServiceSwitch.class, cacheType = CacheType.OBJECT)
	public StuffServiceSwitch findStuffServiceSwitchByKey(String key) {
		return stuffServiceSwitchDao.findStuffServiceSwitchByKey(key);
	}

}
