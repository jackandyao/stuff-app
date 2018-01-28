package com.qbao.aisr.app.repository.mybatis.dao.ad;

import com.qbao.aisr.app.model.AdBanner;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-05-17 11:58:17 +0800 (Wed, 17 May 2017) $$
 * $$LastChangedRevision: 852 $$
 * $$LastChangedBy: wangping $$
 */
@Repository
public interface AdBannerDao {

    @Select("select * from ad_banner where status=1 and ad_location_id= #{locationId} and on_time<=#{nowTime} and off_time>=#{nowTime} order by create_time desc limit #{limit}")
    @ResultMap("AdBannerMap")
    @DataSource("stuffSlaveDataSource")
    public List<AdBanner> findBannerByLocationId(@Param("locationId") long locationId, @Param("limit") int limit, @Param("nowTime") Date nowTime);

    @Select("select * from ad_banner where status=1 and level = 1 and module_id = #{moduleId} and ad_location_id= #{locationId} and on_time<=#{nowTime} and off_time>=#{nowTime} limit #{limit}")
    @ResultMap("AdBannerMap")
    @DataSource("stuffSlaveDataSource")
    public List<AdBanner> findIndexPageBannerByLocationId(@Param("moduleId") int moduleId, @Param("locationId") long locationId, @Param("limit") int limit, @Param("nowTime") Date nowTime);

    @Select("select * from ad_banner where status=1 and module_id = #{moduleId} and ad_location_id= #{locationId} and on_time<=#{nowTime} and off_time>=#{nowTime} limit #{limit}")
    @ResultMap("AdBannerMap")
    @DataSource("stuffSlaveDataSource")
    public List<AdBanner> findBannerByModuleAndLocationId(@Param("moduleId") int moduleId, @Param("locationId") long locationId, @Param("limit") int limit, @Param("nowTime") Date nowTime);

    @Select("select * from ad_banner where status=1 and id= #{id}")
    @ResultMap("AdBannerMap")
    @DataSource("stuffSlaveDataSource")
    public AdBanner findBannerById(@Param("id") Integer id);

}
