package com.qbao.aisr.app.repository.mybatis.dao.shop;

import com.qbao.aisr.app.model.StuffPromotion;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-07-10 18:30:55 +0800 (Mon, 10 Jul 2017) $$
 * $$LastChangedRevision: 1413 $$
 * $$LastChangedBy: liaijun $$
 */
@Component
public interface StuffPromotionDao {
    @Select("select * from stuff_promotion where status=1 and shop_id=#{shopId} and source=#{source} order by create_time desc limit #{stuffSize}")
    @ResultMap("StuffPromotionMap")
    @DataSource("stuffSlaveDataSource")
    public List<StuffPromotion> findStuffPromotionByShopId(@Param("shopId") long shopId, @Param("stuffSize") int stuffSize, @Param("source") String source);


    @Select("select * from stuff_promotion where status=1 and cat_id=#{catId} limit #{page},#{size}")
    @ResultMap("StuffPromotionMap")
    @DataSource("stuffSlaveDataSource")
    public List<StuffPromotion> findStuffPromotionByCatId(@Param("catId") Long catId, @Param("page") int page, @Param("size") int size);

    @Select("select * from stuff_promotion where status=1 and id = #{id}")
    @ResultMap("StuffPromotionMap")
    @DataSource("stuffSlaveDataSource")
    public StuffPromotion findStuffPromotionById(@Param("id") long id);

    // 此接口只返回id 如果返回整张表字段请调用findStuffPromotionByIdsList
    @DataSource("stuffSlaveDataSource")
    public List<StuffPromotion> findStuffPromotionByIds(@Param("ids") List<Long> ids);

    // @Select("select * from stuff_promotion where status=1 and id in limit #{page},#{size}")
    // @ResultMap("StuffPromotionMap")
    @DataSource("stuffSlaveDataSource")
    public List<StuffPromotion> findStuffPromotionByIdsAndPage(@Param("ids") List<Long> ids, @Param("page") Integer page, @Param("size") Integer size);

    @DataSource("stuffSlaveDataSource")
    public List<StuffPromotion> findStuffPromotionByIdsList(@Param("ids") List<Long> ids);

    @Select("select  p.*,r.is_absolute,r.`value` as rebate_value,p.id as stuff_id from stuff_promotion p,stuff_rebate r  where p.rebate_id=r.id and p.status=1 and p.id = #{stuffId}")
    @ResultMap("StuffPromotionMap")
    @DataSource("stuffSlaveDataSource")
    public StuffPromotion findStuffPromotionInfo(@Param("stuffId") long stuffId);




    @Select("select  p.* from stuff_promotion p,flash_sale_stuff_conf r  where p.id=r.stuff_id and p.status=1  and r.flash_sale_conf_id= #{saleConfId} order by r.display_order asc limit #{page},#{size}")
    @ResultMap("StuffPromotionMap")
    @DataSource("stuffSlaveDataSource")
    public List<StuffPromotion> findStuffPromotionBySaleConf(@Param("saleConfId") long saleConfId, @Param("page") Integer page, @Param("size") Integer size);

}
