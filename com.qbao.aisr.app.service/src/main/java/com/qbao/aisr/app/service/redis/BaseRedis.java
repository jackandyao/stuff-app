package com.qbao.aisr.app.service.redis;

import com.alibaba.fastjson.JSON;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.common.AbstractBaseRedisClusterDao;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xueming on 2017/3/14.
 */
@Component
public class BaseRedis extends AbstractBaseRedisClusterDao<String, String> {
    private static Logger logger = LoggerFactory.getLogger(BaseRedis.class);
    public Object getCache(String key, Class clazz, CacheType cacheType) {
        try {
            String result = this.jedisCluster.get(key);
            if (!StringUtils.isEmpty(result)) {
                if (CacheType.LIST.equals(cacheType)) {
                    List list = JSON.parseObject(result, List.class);
                    List retList = new ArrayList();
                    for (Object obj : list) {
                        retList.add(JSON.parseObject(obj.toString(), clazz));
                    }
                    return retList;
                } else if (CacheType.PAGE.equals(cacheType)) {
                    Page page = JSON.parseObject(result, Page.class);
                    List items = new ArrayList();
                    for (Object o : page.getItems()) {
                        items.add(JSON.parseObject(o.toString(), clazz));
                    }
                    page.setItems(items);
                    return page;
                } else {
                    return JSON.parseObject(result, clazz);
                }
            }
        } catch (Exception e) {
            logger.error("getFromCache exception" + ExceptionUtils.getFullStackTrace(e));
        }
        return null;
    }
}
