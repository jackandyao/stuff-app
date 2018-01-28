package com.qbao.aisr.app.repository.mybatis.dao.ad;

import com.qbao.aisr.app.model.AdJu;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/7 上午10:36
 * $$LastChangedDate: 2017-03-10 21:09:54 +0800 (Fri, 10 Mar 2017) $$
 * $$LastChangedRevision: 127 $$
 * $$LastChangedBy: wangping $$
 */
@Component
public interface AdJuDao {
    @Select("select * from ad_ju where ad_location_id= #{locationId} and status = 1 and on_time<=#{nowTime} and off_time>=#{nowTime} limit #{limit}")
    @ResultMap("AdJuMap")
    @DataSource("stuffSlaveDataSource")
    public List<AdJu> findByLocationId(@Param("locationId") long locationId, @Param("limit") int limit, @Param("nowTime") Date nowTime);

}
