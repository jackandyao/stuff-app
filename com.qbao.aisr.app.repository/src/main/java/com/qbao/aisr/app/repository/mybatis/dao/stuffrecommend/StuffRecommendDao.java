package com.qbao.aisr.app.repository.mybatis.dao.stuffrecommend;

import com.qbao.aisr.app.model.StuffRecommend;
import com.qbao.aisr.app.model.TopSearchStuff;
import com.qbao.aisr.app.model.TopSearchStuffComplex;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品推荐
 * 
 * @author xueming
 * @createTime 17/3/6 下午3:17
 * $$LastChangedDate: 2017-03-30 13:51:29 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 585 $$
 * $$LastChangedBy: wangping $$
 */
@Component
public interface StuffRecommendDao {
    @Select("select * from rec_search_stuff where user_id=#{userId}")
    @ResultMap("StuffRecommendMap")
    @DataSource("stuffSlaveDataSource")
    public StuffRecommend stuffRecommend(@Param("userId") Long userId);

    @Select("update rec_search_stuff set stuff_ids=#{stuffIds} where user_id=#{userId}")
    @ResultMap("StuffRecommendMap")
    @DataSource("stuffMasterDataSource")
    public void updateStuffRecommend(@Param("userId") Long userId,@Param("stuffIds") String stuffIds);

    /**
     * 首页商品推荐
     * @param userId
     * @return
     */
    @Select("select * from rec_user_stuff where user_id=#{userId}")
    @ResultMap("StuffRecommendMap")
    @DataSource("stuffSlaveDataSource")
    public StuffRecommend stuffRecommendHomePage(@Param("userId") Long userId);


}
