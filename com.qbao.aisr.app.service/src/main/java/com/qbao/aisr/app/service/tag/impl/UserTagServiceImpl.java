package com.qbao.aisr.app.service.tag.impl;

import com.qbao.aisr.app.model.UserTags;
import com.qbao.aisr.app.repository.mybatis.dao.user.UserTagsDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.repository.redis.dao.RedisDao;
import com.qbao.aisr.app.service.tag.IUserTagService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-04-07 10:03:34 +0800 (Fri, 07 Apr 2017) $$
 * $$LastChangedRevision: 653 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class UserTagServiceImpl implements IUserTagService {

    private Logger logger = Logger.getLogger(UserTagServiceImpl.class);
    @Autowired
    private UserTagsDao userTagsDao;

    @Autowired
    private RedisDao redisDao;

    /**
     *
     * @param userId
     * @param typeId   标签类型(个人标签 : 1 购物标签 :2)
     * @return
     */
    @RedisCache(expire = 60*30, clazz = UserTags.class, cacheType = CacheType.OBJECT)
    public UserTags findByUserId(long userId, int typeId) {
        UserTags userTags = userTagsDao.findUserTagsByUserId(userId, typeId);
        return userTags;
    }

    /**
     *
     * @param userId
     * @param typeId 标签类型(个人标签 : 1 购物标签 :2)
     * @param tags  "1,3,5,6,8"
     */
    public void modifyUserTags(long userId, int typeId, String tags) {
        logger.info("modify user tags : userId=["+"], typeId =[" +typeId+"] and tags =[" +tags+"]");
        userTagsDao.delUserTags(userId, typeId);
        userTagsDao.insertUserTags(userId, typeId, tags);
        String key  = generateCacheKey(userId,typeId);
        logger.info("try to delete the redis cache key = [" +key+ "]");
        redisDao.del(key);
    }

    private String generateCacheKey(long userId, int typeId){
        //    // 用类名、方法名、参数名作为缓存的key
         String cacheKey = getClass().getSimpleName().concat("_").concat("findByUserId").concat("_").concat(String.valueOf(userId)).concat("_").concat(String.valueOf(typeId));
        return cacheKey;
    }

    /**
     *
     * @param userId
     * @param typeId 标签类型(个人标签 : 1 购物标签 :2)
     */
    public void delUserTags(long userId, int typeId) {
        userTagsDao.delUserTags(userId, typeId);
    }

    /**
     *
     * @param userId
     * @param typeId 标签类型(个人标签 : 1 购物标签 :2)
     * @param tags  "1,3,5,6,8"
     */
    public void insertUserTags(long userId, int typeId, String tags) {
        userTagsDao.insertUserTags(userId, typeId, tags);
    }
}
