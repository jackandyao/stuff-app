package com.qbao.aisr.app.repository.mybatis.dao.user;

import com.qbao.aisr.app.model.TagDetail;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-03 17:59:07 +0800 (Fri, 03 Mar 2017) $$
 * $$LastChangedRevision: 53 $$
 * $$LastChangedBy: wangping $$
 */
@Component
public interface TagDetailDao {
    @Select("select * from tag_detail ")
    @ResultMap("TagDetailMap")
    @DataSource("stuffSlaveDataSource")
    public List<TagDetail> findAll();

    @Select("select * from tag_detail where tag_type_id = #{typeId}  ")
    @ResultMap("TagDetailMap")
    @DataSource("stuffSlaveDataSource")
    public List<TagDetail> findByTagTypeId(@Param("typeId") int typeId);
}
