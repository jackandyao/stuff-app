package com.qbao.aisr.app.repository.mybatis.dao.user;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.qbao.aisr.app.model.UserTags;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-03 17:59:07 +0800 (Fri, 03 Mar 2017) $$
 * $$LastChangedRevision: 53 $$
 * $$LastChangedBy: wangping $$
 */
@Component
public interface UserTagsDao {
    @Select("select * from user_tags s where s.user_id = #{userId} and s.tag_type_id = #{typeId} limit 1")
    @ResultMap("UserTagsMap")
    @DataSource("stuffSlaveDataSource")
    public UserTags findUserTagsByUserId(@Param("userId") long userId, @Param("typeId") int typeId);

    @DataSource("stuffMasterDataSource")
    @ResultMap("UserTagsMap")
    @Update("update user_tags s set s.tag_detail_ids= #{tags} , s.update_time = now() where s.user_id= #{userId} and s.tag_type_id = #{typeId}")
    public void modifyUserTags(@Param("userId") long userId, @Param("typeId") int typeId, @Param("tags") String tags);

    @DataSource("stuffMasterDataSource")
    @Delete("delete  from user_tags  where user_id= #{userId} and tag_type_id = #{typeId}")
    public void delUserTags(@Param("userId") long userId, @Param("typeId") int typeId);

    @DataSource("stuffMasterDataSource")
    @Insert(" insert user_tags ( `user_id`,`tag_type_id`,  `tag_detail_ids`) values ( #{userId} , #{typeId} , #{tags})")
    public void insertUserTags(@Param("userId") long userId, @Param("typeId") int typeId, @Param("tags") String tags);
}
