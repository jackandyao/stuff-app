package com.qbao.aisr.app.repository.mybatis.dao.ju;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.qbao.aisr.app.model.StuffHeadline;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

/**
 * 有好货头条接口
 * @author liaijun
 * @createTime 17/3/6 上午11:35
 * $$LastChangedDate: 2017-03-06 14:50:17 +0800 (Mon, 06 Mar 2017) $$
 * $$LastChangedRevision: 73 $$
 * $$LastChangedBy: liaijun $$
 */
@Component
public interface StuffHeadlineDao {

    @Select("select * from stuff_headline where status=1 and on_time<=#{nowTime} and off_time>=#{nowTime} limit #{size}")
    @ResultMap("StuffHeadlineMap")
    @DataSource("stuffSlaveDataSource")
    public List<StuffHeadline> findStuffPromotionByCatId( @Param("size") int size,@Param("nowTime") Date nowTime);

}
