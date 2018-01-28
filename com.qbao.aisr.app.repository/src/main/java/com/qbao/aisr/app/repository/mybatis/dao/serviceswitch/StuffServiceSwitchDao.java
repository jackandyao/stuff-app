package com.qbao.aisr.app.repository.mybatis.dao.serviceswitch;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.qbao.aisr.app.model.StuffServiceSwitch;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

/**
 * @author sjzhangjun
 * @createTime 17/3/30 上午9:32
 * $$LastChangedDate: 2017-03-11 19:31:45 +0800 (周六, 2017-03-11) $$
 * $$LastChangedRevision: 148 $$
 * $$LastChangedBy: louxueming $$
 */
@Component
public interface StuffServiceSwitchDao {

    @DataSource("stuffSlaveDataSource")
    @Select("select * from stuff_service_switch where `key` = #{key} ")
    @ResultMap("StuffServiceSwitchMap")
    public StuffServiceSwitch findStuffServiceSwitchByKey(@Param("key") String key);

}
