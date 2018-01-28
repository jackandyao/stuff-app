package com.qbao.aisr.app.repository.mybatis.dao.shop;

import com.qbao.aisr.app.model.ShopPromotion;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-17 09:18:41 +0800 (Fri, 17 Mar 2017) $$
 * $$LastChangedRevision: 255 $$
 * $$LastChangedBy: allen $$
 */
@Component
public interface ShopPromotionDao {
    @Select("select * from shop_promotion where status=1 order by create_time desc limit #{shopSize}")
    @ResultMap("ShopPromotionMap")
    @DataSource("stuffSlaveDataSource")
    public List<ShopPromotion> findShopPromotion(@Param("shopSize") int shopSize);

    @Select("select * from shop_promotion where status=1 and id = #{shopId}")
    @ResultMap("ShopPromotionMap")
    @DataSource("stuffSlaveDataSource")
    public ShopPromotion findShopPromotionByShopId(@Param("shopId") long shopId);

}
