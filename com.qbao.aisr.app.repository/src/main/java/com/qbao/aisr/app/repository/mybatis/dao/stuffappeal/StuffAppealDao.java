package com.qbao.aisr.app.repository.mybatis.dao.stuffappeal;

import com.qbao.aisr.app.model.StuffAppeal;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangjun
 * @createTime 17/5/24 下午5:41
 * $$LastChangedDate: 2017-03-31 14:16:40 +0800 (Fri, 31 Mar 2017) $$
 * $$LastChangedRevision: 606 $$
 * $$LastChangedBy: zhangjun $$
 */
@Component
public interface StuffAppealDao {
	
    @Select("SELECT COUNT(*) FROM user_stuff_promotion_appeal WHERE USER_ID = #{userId} AND CREATE_TIME>=CONCAT(CURDATE(), ' 00:00:00') AND CREATE_TIME<=CONCAT(CURDATE(), ' 23:59:59')")
    @DataSource("stuffSlaveDataSource")
    public int countTodayStuffAppealsByUserId(@Param("userId") long userId);


    @Insert("INSERT INTO user_stuff_promotion_appeal(`user_id`, `order_id`, `source`, `device`, `phone_brand`, `phone_type`, `content`,`reason`,  `img_url`, `qq`, `phone`) VALUES(#{userId}, #{orderId}, #{source}, #{device}, #{phoneBrand}, #{phoneType}, #{content}, #{reason}, #{imgUrl}, #{qq}, #{phone})")
    @DataSource("stuffMasterDataSource")
	public void saveStuffAppeal(@Param("userId") long userId, @Param("orderId") String orderId
    		, @Param("source") String source, @Param("device") int device, @Param("phoneBrand") String phoneBrand, @Param("phoneType") String phoneType
    		, @Param("content") String content, @Param("reason") String reason, @Param("imgUrl") String imgUrl
    		, @Param("qq") String qq, @Param("phone") String phone);


    @Update("UPDATE user_stuff_promotion_appeal set status=2 where id = #{appealId} and user_id = #{userId}")
    @DataSource("stuffMasterDataSource")
	public void cancel(@Param("appealId") long appealId, @Param("userId") long userId);

    @Select("SELECT * FROM user_stuff_promotion_appeal WHERE user_id = #{userId} order by create_time desc")
    @ResultMap("StuffAppealMap")
    @DataSource("stuffSlaveDataSource")
	public List<StuffAppeal> findStuffAppealsByUserId(@Param("userId") long userId);
    
    @Select("SELECT * FROM user_stuff_promotion_appeal WHERE id = #{appealId}")
    @ResultMap("StuffAppealMap")
    @DataSource("stuffSlaveDataSource")
	public StuffAppeal findStuffAppealByAppealId(@Param("appealId") long appealId, @Param("userId") long userId);
    

}
