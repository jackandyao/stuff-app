package com.qbao.aisr.app.service.dislikestuff.impl;

import com.google.common.collect.Lists;
import com.qbao.aisr.app.model.UserDislikeStuff;
import com.qbao.aisr.app.repository.mybatis.dao.dislikestuff.UserDislikeStuffDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.repository.redis.dao.RedisDao;
import com.qbao.aisr.app.service.dislikestuff.IUserDislikeStuffService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author xueming
 * @createTime 17/3/6 下午3:33
 * $$LastChangedDate: 2017-04-26 11:41:36 +0800 (Wed, 26 Apr 2017) $$
 * $$LastChangedRevision: 723 $$
 * $$LastChangedBy: wangping $$
 */

@Service
public class UserDislikeStuffServiceImpl implements IUserDislikeStuffService {
    private Logger logger = Logger.getLogger(UserDislikeStuffServiceImpl.class);
    @Autowired
    private UserDislikeStuffDao dao;

    @Autowired
    private RedisDao redisDao;

    @RedisCache(expire = 60*30,clazz =Long.class,cacheType = CacheType.LIST)
    public List<Long>  fetchDisLikeStuffIds(Long userId){
        List<Long> ids = Lists.newArrayList();
        UserDislikeStuff userDislikeStuff = dao.userDislikeStuff(userId);
        if(userDislikeStuff!=null){
            String stuffIds = userDislikeStuff.getStuffIds();
            String[] stuffIdsArr = stuffIds.split(",");
            for(String stuffId:stuffIdsArr){
                ids.add(Long.parseLong(stuffId));
            }
        }
        return ids;
    }
    @Transactional
    public Boolean updateUserDislikeStuff(Long userId, Long stuffId) {
        if (userId == null || stuffId == null) {
            return false;
        }
        try {
            UserDislikeStuff userDislikeStuff = dao.userDislikeStuff(userId);
            if(userDislikeStuff!=null){
                String stuffIds = userDislikeStuff.getStuffIds();
                if(stuffIds!=null&&stuffIds.trim().length()>0){
                    if(stuffIds.contains(stuffId.toString())){
                        return true;
                    }
                    if(stuffIds.split(",").length>=1){
                        stuffIds = stuffIds+","+stuffId;
                    }else{
                        stuffIds = stuffId.toString();
                    }
                }else{
                    stuffIds = stuffId.toString();
                }
                dao.updateUserDislikeStuff(userId, stuffIds,new Date());
                //更新缓存
                String key  = generateCacheKey(userId);
                logger.info("try to delete the redis cache key = [" +key+ "]");
                redisDao.del(key);
                return true;
            }else{
                dao.insertUserDislikeStuff(userId, stuffId,new Date());
                //更新缓存
                String key  = generateCacheKey(userId);
                logger.info("try to delete the redis cache key = [" +key+ "]");
                redisDao.del(key);
                return true;
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
            return false;
        }
    }
    private String generateCacheKey(long userId){
        //    // 用类名、方法名、参数名作为缓存的key
        String cacheKey = getClass().getSimpleName().concat("_").concat("fetchDisLikeStuffIds").concat("_").concat(String.valueOf(userId));
        return cacheKey;
    }

}
