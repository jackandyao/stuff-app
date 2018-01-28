package com.qbao.aisr.app.service.shop.impl;

import com.qbao.aisr.app.common.enums.DeviceEnum;
import com.qbao.aisr.app.dto.ShopPromotionDto;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.model.ShopPromotion;
import com.qbao.aisr.app.model.StuffPromotion;
import com.qbao.aisr.app.repository.mybatis.dao.shop.ShopPromotionDao;
import com.qbao.aisr.app.repository.mybatis.dao.shop.StuffPromotionDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.promotion.IPromotionUrlService;
import com.qbao.aisr.app.service.rebate.IStuffReBateService;
import com.qbao.aisr.app.service.shop.IShopPromotionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-04-10 11:47:22 +0800 (Mon, 10 Apr 2017) $$
 * $$LastChangedRevision: 662 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class ShopPromotionServiceImpl implements IShopPromotionService {
    @Autowired
    private ShopPromotionDao shopPromotionDao;
    @Autowired
    private StuffPromotionDao stuffPromotionDao;
    @Autowired
    private IStuffReBateService stuffReBateService;
    @Autowired
    private IPromotionUrlService promotionUrlService;

    @RedisCache(expire = 60 * 10, clazz = ShopPromotionDto.class, cacheType = CacheType.LIST)
    public List<ShopPromotionDto> findShopPromotion(int shopSize, int stuffSize, int device) {
        if(stuffSize<1){
            stuffSize=0;
        }
        if(shopSize<1){
            shopSize=0;
        }
        List<ShopPromotionDto> dtoList = new ArrayList<ShopPromotionDto>();
        List<ShopPromotion> list = shopPromotionDao.findShopPromotion(shopSize);
        if (list == null) {
            return dtoList;
        }
        for (ShopPromotion shop : list) {
            ShopPromotionDto dto = new ShopPromotionDto();
            BeanUtils.copyProperties(shop, dto);
            dto.setUrl(promotionUrlService.findPromtoionUrl(shop, DeviceEnum.asDeviceEnum(device)));
            List<StuffPromotion> stuffList = stuffPromotionDao.findStuffPromotionByShopId(shop.getRealShopId(), stuffSize, shop.getSource());
            List<StuffPromotionDto> dtoStuffList = new ArrayList<StuffPromotionDto>();
            if (stuffList != null) {
                for (StuffPromotion stuff : stuffList) {
                    StuffPromotionDto stuffDto = new StuffPromotionDto();
                    BeanUtils.copyProperties(stuff, stuffDto);
                    stuffDto.setRebateValue(stuffReBateService.findStuffRebateValue(stuff.getId()));
                    stuffDto.setUrl(promotionUrlService.findPromtoionUrl(stuff,DeviceEnum.asDeviceEnum(device)));
                    dtoStuffList.add(stuffDto);
                }
            }
            dto.setList(dtoStuffList);
            dtoList.add(dto);
        }

        return dtoList;
    }


    @RedisCache(expire = 60 * 2, clazz = Boolean.class, cacheType = CacheType.OBJECT)
    public boolean isAvailable(long shopId) {
        return (null != shopPromotionDao.findShopPromotionByShopId(shopId));
    }
   // @RedisCache(expire = 60 * 10, clazz = ShopPromotion.class, cacheType = CacheType.OBJECT)
    public ShopPromotion getShopInfo(long shopId) {
        return shopPromotionDao.findShopPromotionByShopId(shopId);
    }
}
