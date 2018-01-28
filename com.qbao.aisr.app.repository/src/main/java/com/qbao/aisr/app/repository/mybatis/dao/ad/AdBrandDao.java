package com.qbao.aisr.app.repository.mybatis.dao.ad;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.qbao.aisr.app.model.AdBrand;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

/**
 * @author wangping
 * @createTime 17/3/7 上午10:36
 * $$LastChangedDate: 2017-03-10 21:09:54 +0800 (Fri, 10 Mar 2017) $$
 * $$LastChangedRevision: 127 $$
 * $$LastChangedBy: wangping $$
 */
@Component
public interface AdBrandDao {
	
	@Select("select * from ad_brand where status = 1 and on_time<now() and off_time>now() limit #{offset},#{limit}")
    @ResultMap("AdBrandMap")
    @DataSource("stuffSlaveDataSource")
    public List<AdBrand> findList(@Param("offset") int offset, @Param("limit") int limit);
	
	@Select("select * from ad_brand where id = #{id}")
    @ResultMap("AdBrandMap")
    @DataSource("stuffSlaveDataSource")
    public AdBrand findById(@Param("id") long id);

}
