package com.qbao.aisr.app.repository.mybatis.dao.top;

import com.qbao.aisr.app.model.TopSearchStuff;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 热门搜索top商品
 *
 * @author liaijun
 * @createTime 17/3/6 下午3:17
 * $$LastChangedDate: 2017-05-11 21:08:39 +0800 (Thu, 11 May 2017) $$
 * $$LastChangedRevision: 817 $$
 * $$LastChangedBy: wangping $$
 */
@Component
public interface TopSearchStuffDao {
    @Select("select * from top_search_stuff  limit #{size}")
    @ResultMap("TopSearchStuffMap")
    @DataSource("stuffSlaveDataSource")
    public List<TopSearchStuff> topSearchStuff(@Param("size") int size);

    @Select("select distinct * from top_search_stuff order by id  limit #{page},#{size}")
    @ResultMap("TopSearchStuffMap")
    @DataSource("stuffSlaveDataSource")
    public List<TopSearchStuff> topSearchStuffByPage(@Param("page") Integer page,@Param("size") int size);

    @Select("select distinct * from top_search_stuff")
    @ResultMap("TopSearchStuffMap")
    @DataSource("stuffSlaveDataSource")
    public List<TopSearchStuff> findAllTopSearchStuff();

    /*@Select("select sp.id,sp.`name`,sp.img_url,sp.url,sp.final_price,sp.source \n" +
            "from stuff_promotion sp\n" +
            "where sp.status=1 and sp.id in(select distinct stuff_id from top_search_stuff)\n" +
            "limit #{page},#{size}")
    @ResultMap("TopSearchStuffComplexMap")
    @DataSource("stuffSlaveDataSource")
    public List<TopSearchStuffComplex> topSearchStuffComplex(@Param("page") Integer page,@Param("size") Integer size);*/

}
