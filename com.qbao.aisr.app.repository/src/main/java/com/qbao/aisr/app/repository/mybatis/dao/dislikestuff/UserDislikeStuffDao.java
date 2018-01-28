package com.qbao.aisr.app.repository.mybatis.dao.dislikestuff;

import com.qbao.aisr.app.model.StuffRecommend;
import com.qbao.aisr.app.model.UserDislikeStuff;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 商品推荐
 * 
 * @author xueming
 * @createTime 17/3/6 下午3:17
 * $$LastChangedDate: 2017-03-27 16:35:45 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 552 $$
 * $$LastChangedBy: louxueming $$
 */
@Component
public interface UserDislikeStuffDao {
    @Select("select * from user_dislike_stuff where user_id=#{userId}")
    @ResultMap("UserDislikeStuffMap")
    @DataSource("stuffSlaveDataSource")
    public UserDislikeStuff userDislikeStuff(@Param("userId") Long userId);

    @Update("update user_dislike_stuff set stuff_ids=#{stuffIds},update_time=#{updateTime} where user_id=#{userId}")
    @ResultMap("UserDislikeStuffMap")
    @DataSource("stuffMasterDataSource")
    public void updateUserDislikeStuff(@Param("userId") Long userId, @Param("stuffIds") String stuffIds, @Param("updateTime") Date updateTime);

    @Insert("insert user_dislike_stuff ( `user_id`,`stuff_ids`,  `create_time`,  `update_time`) values ( #{userId} , #{stuffIds} , #{createTime} , #{createTime})")
    @ResultMap("UserDislikeStuffMap")
    @DataSource("stuffMasterDataSource")
    public void insertUserDislikeStuff(@Param("userId") Long userId, @Param("stuffIds") Long stuffIds, @Param("createTime") Date createTime);




}
