package com.qbao.aisr.app.repository.mybatis.dao.comm;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.qbao.aisr.app.model.CommMsg;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

/**
 * @author zhangjun
 * @createTime 17/5/30 上午10:36
 * $$LastChangedDate: 2017-03-10 21:09:54 +0800 (Fri, 10 Mar 2017) $$
 * $$LastChangedRevision: 127 $$
 * $$LastChangedBy: zhangjun $$
 */
@Component
public interface CommMsgDao {
    @Select("select * from comm_msg where type= #{typeId} and status=1 and on_time<=now() and off_time>=now() limit 0,1")
    @ResultMap("CommMsgMap")
    @DataSource("stuffSlaveDataSource")
    public CommMsg findByTypeId(@Param("typeId") long typeId);

}
