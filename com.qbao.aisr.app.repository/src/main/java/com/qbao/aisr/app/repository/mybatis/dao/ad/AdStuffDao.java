package com.qbao.aisr.app.repository.mybatis.dao.ad;

import com.qbao.aisr.app.model.AdStuff;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/6 下午12:16
 * $$LastChangedDate: 2017-03-16 11:59:10 +0800 (Thu, 16 Mar 2017) $$
 * $$LastChangedRevision: 235 $$
 * $$LastChangedBy: wangping $$
 */
@Component
public interface AdStuffDao {

    //    @Select("select * from ad_stuff where ad_location_id= #{locationId} and module_id = #{moduleId} and status = 1 and on_time<=#{nowTime} and off_time>=#{nowTime} limit #{limit}")
    //    @ResultMap("AdStuffMap")
    //    @DataSource("stuffSlaveDataSource")
    //    public List<AdStuff> findByModuleLocationId(@Param("moduleId") int moduleId, @Param("locationId") long locationId, @Param("limit") int limit, @Param("nowTime") Date nowTime);

    @Select("select * from ad_stuff where ad_location_id= #{locationId} and status = 1 and on_time<=#{nowTime} and off_time>=#{nowTime} limit #{limit}")
    @ResultMap("AdStuffMap")
    @DataSource("stuffSlaveDataSource")
    public List<AdStuff> findByLocationId(@Param("locationId") long locationId, @Param("limit") int limit, @Param("nowTime") Date nowTime);

    @Select("select * from ad_stuff where source = #{source} and status = 1 and on_time<=#{nowTime} and off_time>=#{nowTime} limit #{limit}")
    @ResultMap("AdStuffMap")
    @DataSource("stuffSlaveDataSource")
    public List<AdStuff> findBySource(@Param("source") String source, @Param("limit") int limit, @Param("nowTime") Date nowTime);

    @Select("select * from ad_stuff where source != #{source} and status = 1 and on_time<=#{nowTime} and off_time>=#{nowTime} limit #{limit}")
    @ResultMap("AdStuffMap")
    @DataSource("stuffSlaveDataSource")
    List<AdStuff> findByNotFrom(@Param("source") String source, @Param("limit") int limit, @Param("nowTime") Date nowTime);

    @Select("select * from ad_stuff where ad_location_id= #{locationId} and status = 1 and on_time<=#{nowTime} and off_time>=#{nowTime}")
    @ResultMap("AdStuffMap")
    @DataSource("stuffSlaveDataSource")
    List<AdStuff> findAllByLocationId(@Param("locationId") long locationId, @Param("nowTime") Date nowTime);
}
