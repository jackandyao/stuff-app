package com.qbao.aisr.app.repository.mybatis.dao.stuffrecommend;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;

/**
 * @author zhangjun
 * @createTime 17/5/7 上午9:32
 * $$LastChangedDate: 2017-07-06 14:00:22 +0800 (Thu, 06 Jul 2017) $$
 * $$LastChangedRevision: 580 $$
 * $$LastChangedBy: zhangjun $$
 */
@Repository
public interface RecStuffDao {

	@RedisCache(expire = 60 * 60, clazz = String.class, cacheType = CacheType.OBJECT)
    @DataSource("stuffSlaveDataSource")
    @Select("select stuff_ids from rec_stuff_like where stuff_id = #{stuffId} ")
    public String findStuffLikeByStuffId(@Param("stuffId") long stuffId);

    @RedisCache(expire = 60 * 60, clazz = String.class, cacheType = CacheType.OBJECT)
    @DataSource("stuffSlaveDataSource")
    @Select("select stuff_ids from rec_stuff_relate where stuff_id = #{stuffId} ")
    public String findStuffRelateByStuffId(@Param("stuffId") long stuffId);
    
    @RedisCache(expire = 60 * 60, clazz = String.class, cacheType = CacheType.OBJECT)
    @DataSource("stuffSlaveDataSource")
    @Select("select stuff_ids from rec_user_stuff where user_id = 0 ")
    public String hot();
    
    @RedisCache(expire = 60 * 60, clazz = String.class, cacheType = CacheType.OBJECT)
    @DataSource("stuffSlaveDataSource")
    @Select("select stuff_ids from rec_user_stuff where user_id = #{userId} ")
    public String guess(@Param("userId") long userId);
    
    @RedisCache(expire = 60 * 60, clazz = String.class, cacheType = CacheType.OBJECT)
    @DataSource("stuffSlaveDataSource")
    @Select("select stuff_ids from rec_oneday_stuff where user_id = #{userId} ")
    public String findOnedayStuffByStuffId(@Param("userId") long userId);

}
