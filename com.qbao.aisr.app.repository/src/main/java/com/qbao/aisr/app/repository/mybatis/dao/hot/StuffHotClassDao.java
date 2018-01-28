package com.qbao.aisr.app.repository.mybatis.dao.hot;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.qbao.aisr.app.model.StuffHotClass;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/10 下午4:58
 * $$LastChangedDate: 2017-03-12 19:03:08 +0800 (Sun, 12 Mar 2017) $$
 * $$LastChangedRevision: 165 $$
 * $$LastChangedBy: allen $$
 */
@Component
public interface StuffHotClassDao {

    @Select("select * from stuff_hot_class order by sort asc")
    @ResultMap("StuffHotClassMap")
    @DataSource("stuffSlaveDataSource")
    public List<StuffHotClass> queryHotClassCat();


    @Select("select * from stuff_hot_class where id=#{id}")
    @ResultMap("StuffHotClassMap")
    @DataSource("stuffSlaveDataSource")
    public  StuffHotClass  queryHotClassCatById(@Param("id") Long id);
}
