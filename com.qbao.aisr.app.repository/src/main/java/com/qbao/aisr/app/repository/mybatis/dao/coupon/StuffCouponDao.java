package com.qbao.aisr.app.repository.mybatis.dao.coupon;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qbao.aisr.app.model.StuffCoupon;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;

/**
 * @author zhangjun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-04-03 17:43:08 +0800 (Mon, 03 Apr 2017) $$
 * $$LastChangedRevision: 630 $$
 * $$LastChangedBy: zhangjun $$
 */
@Repository
public interface StuffCouponDao {

	@RedisCache(expire = 60 * 10, clazz = StuffCoupon.class, cacheType = CacheType.OBJECT)
    @Select("select * from stuff_coupon where stuff_id=#{stuffId} and start_time<=now() and end_time>=now() limit 0,1")
    @ResultMap("StuffCouponMap")
    @DataSource("stuffSlaveDataSource")
    public StuffCoupon findStuffCouponsByStuffId(@Param("stuffId") long stuffId);

}
