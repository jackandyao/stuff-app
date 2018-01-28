package com.qbao.aisr.app.repository.mybatis.dao.rebate;

import com.qbao.aisr.app.model.StuffRebate;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author wangping
 * @createTime 17/3/7 上午9:32
 * $$LastChangedDate: 2017-03-23 10:21:11 +0800 (Thu, 23 Mar 2017) $$
 * $$LastChangedRevision: 469 $$
 * $$LastChangedBy: wangping $$
 */
@Component
public interface StuffRebateDao {

    @DataSource("stuffSlaveDataSource")
    @Select("select * from stuff_rebate where id = #{id} ")
    @ResultMap("StuffRebateMap")
    public StuffRebate findStuffReBate(@Param("id") long id);

    @DataSource("stuffSlaveDataSource")
    @Select("select distinct sr.*\n" +
            "from stuff_promotion sp inner join stuff_rebate sr\n" +
            "on sp.rebate_id = sr.id\n" +
            "where sr.id =  #{stuffId}")
    @ResultMap("StuffRebateMap")
    public StuffRebate findStuffReBateByStuffId(@Param("stuffId") Long stuffId);

}
