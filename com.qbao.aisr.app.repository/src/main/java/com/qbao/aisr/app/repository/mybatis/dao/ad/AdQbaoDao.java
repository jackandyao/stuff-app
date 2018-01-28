package com.qbao.aisr.app.repository.mybatis.dao.ad;

/**
 * @author wangping
 * @createTime 2017/3/17 下午4:55
 * $$LastChangedDate: 2017-03-17 18:01:36 +0800 (Fri, 17 Mar 2017) $$
 * $$LastChangedRevision: 268 $$
 * $$LastChangedBy: wangping $$
 */

import com.qbao.aisr.app.model.AdQbao;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AdQbaoDao {
    @Select("select * from ad_qbao where status=1 and ad_location_id= #{locationId} and on_time<=#{nowTime} and off_time>=#{nowTime} limit #{limit}")
    @ResultMap("AdQbaoMap")
    @DataSource("stuffSlaveDataSource")
    public List<AdQbao> findBannerByLocationId(@Param("locationId") long locationId, @Param("limit") int limit, @Param("nowTime") Date nowTime);

}
