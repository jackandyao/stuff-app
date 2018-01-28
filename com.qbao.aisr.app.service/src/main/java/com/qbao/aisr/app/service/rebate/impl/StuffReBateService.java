package com.qbao.aisr.app.service.rebate.impl;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.model.StuffPromotion;
import com.qbao.aisr.app.model.StuffRebate;
import com.qbao.aisr.app.repository.mybatis.dao.rebate.StuffRebateDao;
import com.qbao.aisr.app.repository.mybatis.dao.shop.StuffPromotionDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.rebate.IStuffReBateService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author wangping
 * @createTime 17/3/7 上午9:24
 * $$LastChangedDate: 2017-07-21 11:13:39 +0800 (Fri, 21 Jul 2017) $$
 * $$LastChangedRevision: 1465 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class StuffReBateService implements IStuffReBateService {
    Logger logger= Logger.getLogger(StuffReBateService.class);
    @Autowired
    private StuffRebateDao stuffRebateDao;

    @Autowired
    private StuffPromotionDao stuffPromotionDao;

    @RedisCache(expire = 60 * 30, clazz = StuffRebate.class, cacheType = CacheType.OBJECT)
    public StuffRebate findStuffRebate(long id) {
        return stuffRebateDao.findStuffReBate(id);
    }
    @RedisCache(expire = 60 * 30, clazz = StuffRebate.class, cacheType = CacheType.OBJECT)
    public StuffRebate findStuffRebateByStuffId(Long stuffId) {
        return stuffRebateDao.findStuffReBateByStuffId(stuffId);
    }

    @RedisCache(expire = 60 * 30, clazz = String.class, cacheType = CacheType.OBJECT)
    public String findStuffRebateValue(long stuffId){
        StuffPromotion stuffPromotion = stuffPromotionDao.findStuffPromotionById(stuffId);
        if(stuffPromotion==null){
            logger.error("stuffId=["+stuffId+"] 在商品表中不存在");
            return StringUtils.EMPTY;
        }
        StuffRebate stuffRebate = stuffRebateDao.findStuffReBate(stuffPromotion.getRebateId());
        if(stuffRebate!=null) {
            if (stuffRebate.getIsAbsolute() == 1) {
                //return Constant.FAN + stuffRebate.getValue().toBigInteger() + Constant.BAO;
                return Constant.FAN_JFB + stuffRebate.getValue().toBigInteger();
            } else {
                BigDecimal rebateValue = stuffRebate.getValue();
                Integer promotionRate = stuffPromotion.getPromotionRate();
                BigDecimal price =stuffPromotion.getFinalPrice();
                if (rebateValue != null && promotionRate != null && null!=price) {
                    Long value=(rebateValue.multiply(new BigDecimal(promotionRate)).multiply(price)).divide(new BigDecimal(10000)).longValue();
                    //Long value=(rebateValue.multiply(new BigDecimal(promotionRate)).multiply(price)).divide(new BigDecimal(1000000)).longValue();
                    if(0==value){
                        return StringUtils.EMPTY;
                    }else {
                       // return Constant.FAN +value+Constant.BAOQUAN;
                        return Constant.FAN_JFB +value;
                    }
                } else {
                    return StringUtils.EMPTY;
                }

            }
        }else{
            return StringUtils.EMPTY;
        }
    }
}
