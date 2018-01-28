package com.qbao.aisr.app.repository.mybatis.dao.ju;

import com.qbao.aisr.app.model.StuffJu;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 有好货头条接口
 * 
 * @author liaijun
 * @createTime 17/3/6 上午11:35
 * $$LastChangedDate: 2017-03-07 17:49:13 +0800 (Tue, 07 Mar 2017) $$
 * $$LastChangedRevision: 91 $$
 * $$LastChangedBy: wangping $$
 */
@Component
public interface StuffJuDao {

    @Select("select id,cat_id,name,img_url,link_url,status from stuff_ju where status=1 and on_time<=#{nowTime} and off_time>=#{nowTime} limit #{size}")
    @ResultMap("StuffJuMap")
    @DataSource("stuffSlaveDataSource")
    public List<StuffJu> findStuffJu(@Param("size") int size, @Param("nowTime") Date nowTime);

    @Select("select id,cat_id,name,img_url,link_url,status from stuff_ju where id=#{id} and status=1 and on_time<=#{nowTime} and off_time>=#{nowTime} ")
    @ResultMap("StuffJuMap")
    @DataSource("stuffSlaveDataSource")
    public StuffJu findStuffJuById(@Param("id") long id, @Param("nowTime") Date nowTime);

}
