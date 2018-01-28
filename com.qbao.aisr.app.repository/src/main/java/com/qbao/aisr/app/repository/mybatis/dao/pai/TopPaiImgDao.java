package com.qbao.aisr.app.repository.mybatis.dao.pai;

import com.qbao.aisr.app.model.TopPaiImg;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-10 18:15:26 +0800 (Fri, 10 Mar 2017) $$
 * $$LastChangedRevision: 124 $$
 * $$LastChangedBy: allen $$
 */
@Component
public interface TopPaiImgDao {
    @DataSource("stuffSlaveDataSource")
    @Select("select * from top_pai_img order by create_time desc limit  #{start},#{rows}")
    @ResultMap("TopPaiMap")
    public List<TopPaiImg> findTopPaiList(@Param("start") int start, @Param("rows") int rows);

    @DataSource("stuffSlaveDataSource")
    @Select("select count(1) from top_pai_img ")
    public Long countTopPaiImg();

}
