package com.qbao.aisr.app.repository.mybatis.dao.ad;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qbao.aisr.app.model.BannerStuffConf;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-06-22 17:25:22 +0800 (Thu, 22 Jun 2017) $$
 * $$LastChangedRevision: 1318 $$
 * $$LastChangedBy: zhangjun $$
 */
@Repository
public interface BannerStuffConfDao {

    @Select("select * from banner_stuff_conf where banner_id=#{bannerId} order by  level,display_order")
    @ResultMap("BannerStuffConfMap")
    @DataSource("stuffSlaveDataSource")
    public List<BannerStuffConf> findBannerStuffConfByBannerId(@Param("bannerId") Integer bannerId);

}
