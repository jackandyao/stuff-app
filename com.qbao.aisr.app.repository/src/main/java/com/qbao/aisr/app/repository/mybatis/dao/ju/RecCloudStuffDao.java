package com.qbao.aisr.app.repository.mybatis.dao.ju;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.qbao.aisr.app.model.RecCloudStuff;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

/**
 * @author liaijun
 * @createTime 17/3/13 下午2:48
 * $$LastChangedDate: 2017-03-13 15:50:53 +0800 (Mon, 13 Mar 2017) $$
 * $$LastChangedRevision: 181 $$
 * $$LastChangedBy: allen $$
 */
@Component
public interface RecCloudStuffDao {

    @Select("select * from rec_cloud_stuff s where s.user_id=#{userId}")
    @ResultMap("RecCloudStuffMap")
    @DataSource("stuffSlaveDataSource")
    public RecCloudStuff findRecCloudStuffByUserId( @Param("userId") Long userId);

}
