package com.qbao.aisr.app.repository.mybatis.dao.module;

import com.qbao.aisr.app.model.StuffModule;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/5 下午9:43
 * $$LastChangedDate: 2017-03-07 17:49:13 +0800 (Tue, 07 Mar 2017) $$
 * $$LastChangedRevision: 91 $$
 * $$LastChangedBy: wangping $$
 */
@Component
public interface StuffModuleDao {
    @DataSource("stuffSlaveDataSource")
    @Select("select * from stuff_module where status = 1 order by display_order asc ")
    @ResultMap("StuffModuleMap")
    public List<StuffModule> findAvailableModules();
}
