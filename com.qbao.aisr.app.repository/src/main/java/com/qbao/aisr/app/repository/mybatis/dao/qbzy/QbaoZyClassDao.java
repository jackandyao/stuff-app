package com.qbao.aisr.app.repository.mybatis.dao.qbzy;

import com.qbao.aisr.app.model.QbaoZyClass;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shuaizhihu
 * @createTime 2017/3/12 15:53
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Repository
public interface QbaoZyClassDao {
    @DataSource("stuffSlaveDataSource")
    @Select("select * from qbaozy_class order by sort asc")
    @ResultMap("QbaoZyClassMap")
    public List<QbaoZyClass> findList();

    @DataSource("stuffSlaveDataSource")
    @Select("select * from qbaozy_class where id = #{id}")
    @ResultMap("QbaoZyClassMap")
    public QbaoZyClass  findById(long id);
}
