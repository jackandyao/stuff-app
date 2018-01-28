package com.qbao.aisr.app.repository.mybatis.dao.top;

import com.qbao.aisr.app.model.TopSearchKey;
import com.qbao.aisr.app.model.TopSearchStuff;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 热门搜索top关键字
 * 
 * @author liaijun
 * @createTime 17/3/6 下午3:17
 * $$LastChangedDate: 2017-03-06 17:28:26 +0800 (周一, 06 三月 2017) $$
 * $$LastChangedRevision: 78 $$
 * $$LastChangedBy: liaijun $$
 */
@Component
public interface TopSearchKeyDao {
    @Select("select * from top_search_key  limit #{size}")
    @ResultMap("TopSearchKeyMap")
    @DataSource("stuffSlaveDataSource")
    public List<TopSearchKey> topSearchKey(@Param("size") int size);

}
