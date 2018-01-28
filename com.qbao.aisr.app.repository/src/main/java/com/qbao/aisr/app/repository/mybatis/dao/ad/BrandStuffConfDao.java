package com.qbao.aisr.app.repository.mybatis.dao.ad;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qbao.aisr.app.model.BrandStuffConf;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

/**
 * @author zhangjun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-04-03 17:43:08 +0800 (Mon, 03 Apr 2017) $$
 * $$LastChangedRevision: 630 $$
 * $$LastChangedBy: zhangjun $$
 */
@Repository
public interface BrandStuffConfDao {

    @Select("select * from brand_stuff_conf where brand_id=#{brandId} order by id desc limit #{offset},#{limit}")
    @ResultMap("BrandStuffConfMap")
    @DataSource("stuffSlaveDataSource")
    public List<BrandStuffConf> findBrandStuffConfByBrandId(@Param("brandId") long brandId, @Param("offset") int offset, @Param("limit") int limit);

}
