package com.qbao.aisr.app.repository.mybatis.dao.user;

import java.util.List;

import com.qbao.aisr.app.model.TaokeDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

/**
 * 
 * 
 * @author liaijun
 * @email liaijun@qbao.com
 * @date 2017-03-13 23:20:06
 */
@Component
public interface TaokeDetailDao {

    @Select("select * from taoke_detail where order_id = #{orderId}")
    @ResultMap("taokeDetailMap")
    @DataSource("stuffSlaveDataSource")
    public List<TaokeDetail> queryTaokeDetailByOrderId(@Param("orderId") String orderId);
}
