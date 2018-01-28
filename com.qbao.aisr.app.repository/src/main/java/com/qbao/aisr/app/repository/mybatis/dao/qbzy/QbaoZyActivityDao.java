package com.qbao.aisr.app.repository.mybatis.dao.qbzy;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.qbao.aisr.app.model.QbaoZyActivity;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

/**
 * @author shuaizhihu
 * @createTime 2017/3/12 15:53
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Repository
public interface QbaoZyActivityDao {

    @DataSource("stuffSlaveDataSource")
    @Select("select * from qbaozy_activity where id = #{id} and status = 1 and on_time<now() and off_time>now()")
    @ResultMap("QbaoZyActivityMap")
    public QbaoZyActivity findById(Long id);
    
    @DataSource("stuffSlaveDataSource")
    @Select("select * from qbaozy_activity where status = 1 and on_time<now() and off_time>now()")
    @ResultMap("QbaoZyActivityMap")
    public List<QbaoZyActivity> findList();
    
    @DataSource("stuffMasterDataSource")
    public void saveQbaoZyActivity(@Param("activity") QbaoZyActivity activity);
    
    @DataSource("stuffMasterDataSource")
    @Update("update qbaozy_activity set status = 0 where id = #{id}")
    public void deleteQbaoZyActivityById(@Param("id") Long id);
    
    @DataSource("stuffMasterDataSource")
    public void updateQbaoZyActivity(@Param("activity") QbaoZyActivity activity);
}
