package com.qbao.aisr.app.repository.mybatis.dao.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

/**
 * 我的订单
 * 
 * @author liaijun
 * @createTime 17/6/1 上午9:10
 * $$LastChangedDate: 2017-06-07 18:30:21 +0800 (Wed, 07 Jun 2017) $$
 * $$LastChangedRevision: 1116 $$
 * $$LastChangedBy: wangping $$
 */
@Component
public interface UserAppealDao {

    @Select("select sum(rebate_value) from user_stuff_promotion_appeal l where l.user_id=#{userId} and order_id=#{orderId} and status=3 and source=#{source}")
    @DataSource("stuffSlaveDataSource")
    public Long sumUserStuffPromotionRebateByUserIdStatus(@Param("userId") Long userId, @Param("orderId") String orderId, @Param("source") String source);

    @Select("select sum(rebate_value) from user_stuff_promotion_appeal l where l.user_id=#{userId} and status=3 and is_pay=#{isPay}")
    @DataSource("stuffSlaveDataSource")
    public Long sumUserStuffPromotionRebateByUserId(@Param("userId") Long userId,@Param("isPay") Integer isPay);

}
