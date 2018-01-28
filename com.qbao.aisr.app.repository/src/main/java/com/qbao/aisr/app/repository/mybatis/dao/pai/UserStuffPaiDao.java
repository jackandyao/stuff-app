package com.qbao.aisr.app.repository.mybatis.dao.pai;

import com.qbao.aisr.app.model.TopPaiImg;
import com.qbao.aisr.app.model.UserStuffPai;
import com.qbao.aisr.app.model.UserStuffPromotion;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-28 18:15:38 +0800 (Tue, 28 Mar 2017) $$
 * $$LastChangedRevision: 564 $$
 * $$LastChangedBy: liaijun $$
 */
@Component
public interface UserStuffPaiDao {
    @DataSource("stuffSlaveDataSource")
    @Select("select * from user_stuff_pai  where user_id=#{userId} order by create_time desc limit  #{start},#{rows}")
    @ResultMap("userStuffPaiMap")
    public List<UserStuffPai> findUserPaiList(@Param("userId") Long userId, @Param("start") int start, @Param("rows") int rows);

    @DataSource("stuffSlaveDataSource")
    @Select("select count(1) from user_stuff_pai where user_id=#{userId}")
    public Long countUserPaiImg(@Param("userId") Long userId);

    @DataSource("stuffMasterDataSource")
    @Insert(" insert user_stuff_pai ( `user_id`,`img_url`,  `create_time`) values ( #{userId} , #{path} , #{creatTime})")
    public void saveUserStuffPai(@Param("userId") long userId, @Param("path") String path, @Param("creatTime") Date creatTime);

}
