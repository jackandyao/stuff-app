package com.qbao.aisr.app.service.flash.impl;

import com.qbao.aisr.app.common.enums.DeviceEnum;
import com.qbao.aisr.app.common.page.PageManager;
import com.qbao.aisr.app.dto.FlashSaleConfDto;
import com.qbao.aisr.app.dto.StuffCouponDto;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.model.FlashSaleConf;
import com.qbao.aisr.app.model.StuffCoupon;
import com.qbao.aisr.app.model.StuffHeadline;
import com.qbao.aisr.app.model.StuffPromotion;
import com.qbao.aisr.app.repository.mybatis.dao.coupon.StuffCouponDao;
import com.qbao.aisr.app.repository.mybatis.dao.flash.FlashSaleConfDao;
import com.qbao.aisr.app.repository.mybatis.dao.shop.StuffPromotionDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.flash.IFlashSaleConfService;
import com.qbao.aisr.app.service.promotion.IPromotionUrlService;
import com.qbao.aisr.app.service.rebate.IStuffReBateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liaijun
 * @createTime 17/7/10 下午2:47
 * $$LastChangedDate: 2017-07-10 18:30:55 +0800 (Mon, 10 Jul 2017) $$
 * $$LastChangedRevision: 1413 $$
 * $$LastChangedBy: liaijun $$
 */
@Service
public class FlashSaleConfServiceImpl implements IFlashSaleConfService {
    @Autowired
    private FlashSaleConfDao flashSaleConfDao;
    @Autowired
    private StuffPromotionDao stuffPromotionDao;
    @Autowired
    private IStuffReBateService stuffReBateService;
    @Autowired
    private IPromotionUrlService promotionService;
    @Autowired
    private StuffCouponDao stuffCouponDao;


    @RedisCache(expire = 60 * 30, clazz = FlashSaleConfDto.class, cacheType = CacheType.LIST)
    public List<FlashSaleConfDto> findFlashSaleConf() {
        List<FlashSaleConf> flashSaleConfList= flashSaleConfDao.findFlashSaleConf(new Date());
        List<FlashSaleConfDto> list=new ArrayList<FlashSaleConfDto>();
        for(FlashSaleConf conf:flashSaleConfList){
            FlashSaleConfDto dto=new FlashSaleConfDto();
            BeanUtils.copyProperties(conf,dto);
            dto.setStart(true);
            list.add(dto);
        }
        return list;
    }

    @RedisCache(expire = 60 * 30, clazz = Boolean.class, cacheType = CacheType.OBJECT)
    public Boolean findFlashSaleConfById(Long id,Date nowTime) {
       Long number= flashSaleConfDao.findFlashSaleConfById( id,nowTime);
        if(number!=null&&number>0) {
            return true;
        }else{
            return false;
        }
    }
    @RedisCache(expire = 60 * 30, clazz = StuffPromotionDto.class, cacheType = CacheType.LIST)
    public List<StuffPromotionDto> findFlashSaleStuff(Long id, int page, int size, int device){
        PageManager pageManager = new PageManager(page, size);
        List<StuffPromotion> stuffPromotionList=stuffPromotionDao.findStuffPromotionBySaleConf(id,pageManager.getPage(),pageManager.getPageSize());
        List<StuffPromotionDto> list=new ArrayList<StuffPromotionDto>();
        for(StuffPromotion promotion:stuffPromotionList){
            StuffPromotionDto dto=new StuffPromotionDto();
            BeanUtils.copyProperties(promotion,dto);
            dto.setRebateValue(stuffReBateService.findStuffRebateValue(promotion.getId()));
            dto.setUrl(promotionService.findPromtoionUrl(promotion, DeviceEnum.asDeviceEnum(device)));
            StuffCoupon coupon=stuffCouponDao.findStuffCouponsByStuffId(promotion.getId());
            StuffCouponDto couponDto=new StuffCouponDto();
            if(coupon!=null) {
                BeanUtils.copyProperties(coupon, couponDto);
                dto.setCoupon(couponDto);
            }
            list.add(dto);
        }
       return list;
    }
}
