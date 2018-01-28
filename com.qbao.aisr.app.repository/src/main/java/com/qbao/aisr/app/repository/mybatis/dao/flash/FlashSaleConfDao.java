package com.qbao.aisr.app.repository.mybatis.dao.flash;

import java.util.Date;
import java.util.List;

import com.qbao.aisr.app.model.FlashSaleConf;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.qbao.aisr.app.model.QbaoZyActivity;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

/**
 * @author liaijun
 * @createTime 2017/7/10 15:53
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Repository
public interface FlashSaleConfDao {

    @DataSource("stuffSlaveDataSource")
    @Select("select * from flash_sale_conf where status=1 and on_time<=#{nowTime} and off_time>=#{nowTime}")
    @ResultMap("FlashSaleConfMap")
    public List<FlashSaleConf> findFlashSaleConf( @Param("nowTime") Date nowTime);


    @DataSource("stuffSlaveDataSource")
    @Select("select count(id) from flash_sale_conf  where id = #{id} and  status=1 and on_time<=#{nowTime} and off_time>=#{nowTime}")
    public Long findFlashSaleConfById(@Param("id")Long id,@Param("nowTime") Date nowTime);
}
