package com.qbao.aisr.app.repository.mybatis.dao.user;

import com.qbao.aisr.app.model.UserStuffPromotion;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 我的订单
 * @author liaijun
 * @createTime 17/3/7 下午2:10
 * $$LastChangedDate: 2017-07-03 15:18:26 +0800 (Mon, 03 Jul 2017) $$
 * $$LastChangedRevision: 1402 $$
 * $$LastChangedBy: zhangjun $$
 */
@Component
public interface UserOrderDao {

    @DataSource("stuffMasterDataSource")
    @ResultMap("userStuffPromotionMap")
    @Update("update user_stuff_promotion s set s.status= 0, s.update_time = now() where s.id=#{id}")
    public void modifyUserStuffPromo(@Param("id") long id);

    @Select("select * from user_stuff_promotion u where u.id=#{id}")
    @ResultMap("userStuffPromotionMap")
    @DataSource("stuffSlaveDataSource")
    public  UserStuffPromotion  findUserStuffPromotionById(@Param("id") Long id);


//    @Select("select *  from user_stuff_promotion u where u.user_id=#{userId} and u.`status`=1 and u.return_coupon_status != -1 limit  #{page},#{size}")
//    @ResultMap("userStuffPromotionMap")
//    @DataSource("stuffSlaveDataSource")
//    public List<UserStuffPromotion> findUserStuffPromotionByUserId(@Param("userId")Long userId,@Param("page")int page,@Param("size")int size);

    @Select("select *  from user_stuff_promotion u where u.user_id=#{userId} and u.`status`=1 and u.return_coupon_status != -1 order by create_time desc ")
    @ResultMap("userStuffPromotionMap")
    @DataSource("stuffSlaveDataSource")
    public List<UserStuffPromotion> findUserStuffPromotionByUserId(@Param("userId")Long userId);

    //    @Select("select * from user_stuff_promotion u where u.user_id=#{userId} and u.`status`=1 and u.return_coupon_status=#{status} limit  #{page},#{size}")
//    @ResultMap("userStuffPromotionMap")
//    @DataSource("stuffSlaveDataSource")
//    public List<UserStuffPromotion> findUserStuffPromotionByUserIdStatus(@Param("userId")Long userId,@Param("page")int page,@Param("size")int size,@Param("status")Integer status);


    @Select("select * from user_stuff_promotion u where u.user_id=#{userId} and u.`status`=1 and u.return_coupon_status=#{status}  order by create_time desc")
    @ResultMap("userStuffPromotionMap")
    @DataSource("stuffSlaveDataSource")
    public List<UserStuffPromotion> findUserStuffPromotionByUserIdStatus(@Param("userId")Long userId,@Param("status")Integer status);


    @Select("select  sum(rebate_value) from user_stuff_promotion u where u.user_id=#{userId} and u.`status`=1 ")
    @DataSource("stuffSlaveDataSource")
    public Long sumUserStuffPromotionByUserId(@Param("userId") Long userId);

    @Select("select sum(rebate_value) from user_stuff_promotion u where  u.user_id=#{userId} and u.`status`=1 and u.return_coupon_status=#{status} ")
    @DataSource("stuffSlaveDataSource")
    public Long sumUserStuffPromotionByUserIdStatus(@Param("userId") Long userId, @Param("status") Integer status);

    @Select("select sum(rebate_value) from user_stuff_promotion u where  u.user_id=#{userId} and u.return_coupon_status= 1 ")
    @DataSource("stuffSlaveDataSource")
    public Long totalReturnRebate(@Param("userId") Long userId);

//    @Select("select  count(1) from user_stuff_promotion u where u.user_id=#{userId} and u.`status`=1 ")
//    @DataSource("stuffSlaveDataSource")
//    public Long countUserStuffPromotionByUserId(@Param("userId")Long userId);

    @Select("select count(1) from taoke_detail where order_id in ( select order_id from user_stuff_promotion where user_id = #{userId} and status = 1 and return_coupon_status = #{status}) and stuff_id is not null")
    @DataSource("stuffSlaveDataSource")
    public Long countUserStuffPromotionByUserIdStatus(@Param("userId")Long userId,@Param("status")Integer status);

    @DataSource("stuffMasterDataSource")
    // @ResultMap("userStuffPromotionMap")
    // @Insert("insert into user_stuff_promotion (user_id,order_id,source,create_time,update_time,status)
    // value(#{userId},#{orderId},#{source},#{nowTime},#{nowTime},#{status})")
    public void saveUserStuffPromo(@Param("promotion") UserStuffPromotion promotion);
    
    @DataSource("stuffMasterDataSource")
    public void updateUserStuffPromo(@Param("promotion") UserStuffPromotion promotion);

    @Select("select * from user_stuff_promotion u where u.order_Id=#{orderId} and u.source=#{source}")
    @ResultMap("userStuffPromotionMap")
    @DataSource("stuffMasterDataSource")
    public UserStuffPromotion findUserStuffPromotionByOrderId(@Param("orderId") String orderId,@Param("source") String source);

    @Select("select sum(rebate_value) from user_stuff_promotion u where u.user_id=#{userId} and u.order_id=#{orderId} and source=#{source}")
    @DataSource("stuffSlaveDataSource")
    public Long findUserStuffPromotionByOrderIdSource(@Param("userId") Long userId, @Param("orderId") String orderId, @Param("source") String source);


}
