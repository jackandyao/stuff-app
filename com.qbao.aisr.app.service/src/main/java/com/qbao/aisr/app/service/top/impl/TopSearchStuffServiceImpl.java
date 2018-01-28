package com.qbao.aisr.app.service.top.impl;

import com.qbao.aisr.app.common.enums.DeviceEnum;
import com.qbao.aisr.app.model.StuffPromotion;
import com.qbao.aisr.app.model.TopSearchStuff;
import com.qbao.aisr.app.model.TopSearchStuffComplex;
import com.qbao.aisr.app.repository.mybatis.dao.rebate.StuffRebateDao;
import com.qbao.aisr.app.repository.mybatis.dao.shop.StuffPromotionDao;
import com.qbao.aisr.app.repository.mybatis.dao.top.TopSearchStuffDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.promotion.IPromotionUrlService;
import com.qbao.aisr.app.service.top.ITopSearchStuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xueming
 * @createTime 17/3/6 下午3:33
 * $$LastChangedDate: 2017-05-11 21:08:39 +0800 (Thu, 11 May 2017) $$
 * $$LastChangedRevision: 817 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class TopSearchStuffServiceImpl implements ITopSearchStuffService {
    @Autowired
    private TopSearchStuffDao topSearchStuffDao;
    @Autowired
    private StuffRebateDao stuffRebateDao;

    @Autowired
    private StuffPromotionDao stuffPromotionDao;


    @Autowired
    private IPromotionUrlService promotionService;

    public List<TopSearchStuff> topSearchStuff(int size) {
        if (size < 1) {
            size = 0;
        }
        return topSearchStuffDao.topSearchStuff(size);
    }
    @RedisCache(expire = 60*2,clazz =TopSearchStuffComplex.class,cacheType = CacheType.LIST)
    public List<TopSearchStuffComplex> topSearchStuffComplex(Integer page, Integer size,int device) {
        List<TopSearchStuffComplex> result = new ArrayList<TopSearchStuffComplex>();
        List<TopSearchStuff> topSearchStuffList = findAllTopSearchStuff();
        for (int i=(page-1)*size; i<topSearchStuffList.size() ; i++){
            if(result.size() >=size){
                break;
            }
            TopSearchStuff topSearchStuff =topSearchStuffList.get(i);
            StuffPromotion stuffPromotion =findStuffPromotion(topSearchStuff.getStuffId());
            if(stuffPromotion != null){
                TopSearchStuffComplex topSearchStuffComplex = new TopSearchStuffComplex();
                topSearchStuffComplex.setId(stuffPromotion.getId());
                topSearchStuffComplex.setName(stuffPromotion.getName());
                topSearchStuffComplex.setImgUrl(stuffPromotion.getImgUrl());
                topSearchStuffComplex.setUrl(promotionService.findPromtoionUrl(stuffPromotion, DeviceEnum.asDeviceEnum(device)));
                topSearchStuffComplex.setFinalPrice(stuffPromotion.getFinalPrice());
                topSearchStuffComplex.setRebateValue(stuffPromotion.getRebateValue());
                topSearchStuffComplex.setSource(stuffPromotion.getSource());
                topSearchStuffComplex.setCount(topSearchStuff.getCount());
                result.add(topSearchStuffComplex);
            }

        }
        return result;
    }
    @RedisCache(expire = 60*10,clazz =TopSearchStuff.class,cacheType = CacheType.LIST)
    public List<TopSearchStuff> findAllTopSearchStuff(){
        List<TopSearchStuff> result =   topSearchStuffDao.findAllTopSearchStuff();
        return  null != result ? result : new ArrayList<>();
    }

    @RedisCache(expire = 60*2,clazz =StuffPromotion.class,cacheType = CacheType.OBJECT)
    public StuffPromotion findStuffPromotion(long stuffId){
        StuffPromotion stuffPromotion = null;
        stuffPromotion = stuffPromotionDao.findStuffPromotionById(stuffId);
        return  stuffPromotion;
    }
}
