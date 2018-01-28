package com.qbao.aisr.app.repository.mybatis.dao.ad;

import com.qbao.aisr.app.model.BannerFlashSaleStuffConf;
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
 * $$LastChangedDate: 2017-06-07 18:34:44 +0800 (Wed, 07 Jun 2017) $$
 * $$LastChangedRevision: 1117 $$
 * $$LastChangedBy: wangping $$
 */
@Repository
public interface BannerSaleStuffConfDao {

    @Select("select * from banner_flash_sale_stuff_conf where banner_id=#{bannerId} and  off_time>=#{nowTime} order by on_time asc")
    @ResultMap("BannerSaleStuffConfMap")
    @DataSource("stuffSlaveDataSource")
    public List<BannerFlashSaleStuffConf> findBannerSaleStuffConfByBannerId(@Param("bannerId") Integer bannerId, @Param("nowTime") Date nowTime);

}
