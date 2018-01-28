package com.qbao.aisr.app.repository.mybatis.dao.location;

import com.qbao.aisr.app.model.LocationStyleConf;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/6 上午11:50
 * $$LastChangedDate: 2017-03-08 14:51:26 +0800 (Wed, 08 Mar 2017) $$
 * $$LastChangedRevision: 102 $$
 * $$LastChangedBy: wangping $$
 */
@Component
public interface LocationStyleConfDao {

    @Select("select * from ad_location_style_conf where style_id= #{styleId}")
    @ResultMap("LocationStyleConfMap")
    @DataSource("stuffSlaveDataSource")
    public List<LocationStyleConf> findLocationByStyleId(@Param("styleId") int styleId);

    @Select("select * from ad_location_style_conf where id= #{id}")
    @ResultMap("LocationStyleConfMap")
    @DataSource("stuffSlaveDataSource")
    public LocationStyleConf findConfById(@Param("id") int id);

}
