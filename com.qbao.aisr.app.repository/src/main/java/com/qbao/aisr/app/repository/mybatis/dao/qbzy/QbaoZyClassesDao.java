package com.qbao.aisr.app.repository.mybatis.dao.qbzy;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qbao.aisr.app.model.QbaoZyClasses;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

/**
 * @author zhangjun
 * @createTime 2017/6/11 15:53
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Repository
public interface QbaoZyClassesDao {
    @DataSource("stuffSlaveDataSource")
    @Select("select * from qbaozy_classes where activity_id = #{activityId} order by sort")
    @ResultMap("QbaoZyClassesMap")
    public List<QbaoZyClasses> findQbaoZyClassesListByActivityId(@Param("activityId") Long activityId);
    
    @DataSource("stuffSlaveDataSource")
    @Select("select * from qbaozy_classes where id = #{id}")
    @ResultMap("QbaoZyClassesMap")
    public QbaoZyClasses findById(@Param("id") Long id);
    
    @DataSource("stuffMasterDataSource")
    public void saveQbaoZyClasses(@Param("classes") QbaoZyClasses classes);
    
    @DataSource("stuffMasterDataSource")
    @Delete("delete from qbaozy_classes where id = #{id}")
    public void deleteQbaoZyClassesById(@Param("id") Long id);
    
    @DataSource("stuffMasterDataSource")
    public void updateQbaoZyClasses(@Param("classes") QbaoZyClasses classes);
    
}
