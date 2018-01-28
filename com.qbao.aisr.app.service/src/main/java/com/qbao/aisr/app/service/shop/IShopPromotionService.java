package com.qbao.aisr.app.service.shop;

import com.qbao.aisr.app.dto.ShopPromotionDto;
import com.qbao.aisr.app.model.ShopPromotion;
import com.qbao.aisr.app.model.StuffPromotion;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-30 17:27:59 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 598 $$
 * $$LastChangedBy: liaijun $$
 */
public interface IShopPromotionService {
    // 发现好店首页店铺接口
    public List<ShopPromotionDto> findShopPromotion(int shopSize, int stuffSize, int device);



    /**
     * 判断该店铺是否可用
     * @param shopId
     * @return
     */
    public boolean isAvailable(long shopId);

    public ShopPromotion getShopInfo(long shopId);
}
