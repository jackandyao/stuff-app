package com.qbao.aisr.app.service.stuff.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.aisr.app.common.enums.DeviceEnum;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.common.page.PageManager;
import com.qbao.aisr.app.dto.StuffCouponDto;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.model.RecCloudStuff;
import com.qbao.aisr.app.model.StuffCoupon;
import com.qbao.aisr.app.model.StuffPromotion;
import com.qbao.aisr.app.repository.mybatis.dao.coupon.StuffCouponDao;
import com.qbao.aisr.app.repository.mybatis.dao.ju.RecCloudStuffDao;
import com.qbao.aisr.app.repository.mybatis.dao.shop.StuffPromotionDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.promotion.IPromotionUrlService;
import com.qbao.aisr.app.service.rebate.IStuffReBateService;
import com.qbao.aisr.app.service.stuff.IStuffPromotionService;

/**
 * @author wangping
 * @createTime 17/3/7 上午9:09
 * $$LastChangedDate: 2017-03-11 20:41:57 +0800 (周六, 11 三月 2017) $$
 * $$LastChangedRevision: 150 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class StuffPromotionServiceImpl implements IStuffPromotionService {
    Logger logger = Logger.getLogger(StuffPromotionServiceImpl.class);
    @Autowired
    private StuffPromotionDao stuffPromotionDao;
    @Autowired
    private RecCloudStuffDao recCloudStuffDao;
    @Autowired
    private IStuffReBateService stuffReBateService;
    @Autowired
    private IPromotionUrlService promotionService;
//    @Autowired
//    private StuffCouponDao stuffCouponDao;
    public boolean isAvailable(long id) {

        return (null != findStuffPromotion(id));
    }

    @RedisCache(expire = 60*2,clazz =StuffPromotion.class,cacheType = CacheType.OBJECT)
    public StuffPromotion findStuffPromotion(long id) {
        return stuffPromotionDao.findStuffPromotionById(id);
    }

    /**
     * 点击六个类目返回商品(口腔护理专场,母婴联合狂欢,健康好奶等类目)
     * http://wiki.qbao.com/pages/viewpage.action?pageId=18160745
     * @param catId
     * @param page
     * @param size
     * @return
     */
    @RedisCache(expire = 60 * 2, clazz = StuffPromotion.class, cacheType = CacheType.LIST)
    public List<StuffPromotion> findStuffPromotionByCatId(Long catId, int page, int size) {
        PageManager pageManager = new PageManager(page, size);
        return stuffPromotionDao.findStuffPromotionByCatId(catId, pageManager.getPage(), pageManager.getPageSize());
    }

    /**
     * 返回商品信息包括返利多少
     * @param stuffId
     * @return
     */
    @RedisCache(expire = 60 * 2, clazz = StuffPromotionDto.class, cacheType = CacheType.OBJECT)
    public StuffPromotionDto findStuffPromotionInfo(long stuffId,int device) {
        StuffPromotion promotion = stuffPromotionDao.findStuffPromotionInfo(stuffId);
        StuffPromotionDto dto = null;
        if (promotion != null) {
            dto =new StuffPromotionDto();
            BeanUtils.copyProperties(promotion, dto);
            dto.setRebateValue(stuffReBateService.findStuffRebateValue(stuffId));
            dto.setUrl(promotionService.findPromtoionUrl(promotion, DeviceEnum.asDeviceEnum(device)));
            
//            StuffCoupon stuffCoupon = stuffCouponDao.findStuffCouponsByStuffId(stuffId);
//            if(stuffCoupon != null){
//            	StuffCouponDto stuffCouponDto = new StuffCouponDto();
//    	        BeanUtils.copyProperties(stuffCoupon, stuffCouponDto);
//    	        dto.setCoupon(stuffCouponDto);
//            }
        }
        return dto;
    }

    /**
     * 获取所有的stuffId
     * 
     * @param userId
     * @param setId
     * @return
     */
    public Set<Long> queryRecCloudStuffByUserId(Long userId, Set<Long> setId) {
        logger.info("queryRecCloudStuffByUserId  start userId=" + userId);

        RecCloudStuff cloud = recCloudStuffDao.findRecCloudStuffByUserId(userId);

        if (cloud != null) {
            logger.info("queryRecCloudStuffByUserId  userId=" + userId + " cloud.stuffIds=" + cloud.getStuffIds());
            String ids = cloud.getStuffIds();
            String[] splitId = ids.replaceAll(",", ",").split(",");
            for (String arrayId : splitId) {
                if (StringUtils.isNumeric(arrayId)) {
                    setId.add(new Long(arrayId));
                }
            }
        }
        return setId;
    }
    
    /**
     * 聚好货中云好货商品信息 stuffId去重
     * 
     * @param userId
     * @return
     */
    @RedisCache(expire = 60 * 10, clazz = StuffPromotionDto.class, cacheType = CacheType.PAGE)
    public Page<StuffPromotionDto> findStuffPromotionInfoByUserId(Long userId,int device, int page, int size) {
        logger.info("findStuffPromotionInfoByUserId  userId=" + userId + " device=" + device + " size=" + size + " page=" + page);

        PageManager pageManager = new PageManager(page, size);
        List<StuffPromotionDto> dtos = new ArrayList<StuffPromotionDto>();
        Set<Long> setId = new LinkedHashSet<Long>();
        setId = queryRecCloudStuffByUserId(userId, setId);

        // 如果用户id不为0,则把大众用户也添加进来
        if (userId != 0l) {
            setId = queryRecCloudStuffByUserId(0l, setId);
        }
        if (setId.isEmpty()) {
            return new Page<StuffPromotionDto>(0, pageManager.getPage(), pageManager.getPageSize(), dtos);
        }

        List<Long> arrayList = new ArrayList<Long>();
        for (Long id : setId) {
            arrayList.add(id);
        }
        List<StuffPromotion> listIds = stuffPromotionDao.findStuffPromotionByIds(arrayList);
        arrayList = new ArrayList<Long>();
        for (StuffPromotion stuffPromotion : listIds) {
            arrayList.add(stuffPromotion.getId());
        }
        return queryStuffPromotionInfoByUserId(pageManager, arrayList, device);

    }

    /**
     * 聚好货中云好货商品信息 商品信息提取
     * 
     * @param pageManager
     * @param arrayList
     * @param device
     * @return
     */
    public Page<StuffPromotionDto> queryStuffPromotionInfoByUserId(PageManager pageManager, List<Long> arrayList, int device) {
        logger.info("queryStuffPromotionInfoByUserId start arrayList.size=" + arrayList.size());
        List<StuffPromotionDto> dtos = new ArrayList<StuffPromotionDto>();
        int start = pageManager.getPage();
        int end = pageManager.getPage() + pageManager.getPageSize();
        int length = arrayList.size();
        if (start > length) {
            start = length;
        }
        if (end > length) {
            end = length;
        }
        List<Long> sublistId = arrayList.subList(start, end);
        if (CollectionUtils.isEmpty(sublistId)) {
            return new Page<StuffPromotionDto>(0, pageManager.getPage(), pageManager.getPageSize(), dtos);
        }
        List<StuffPromotion> list = stuffPromotionDao.findStuffPromotionByIdsList(sublistId);
        for (StuffPromotion promotion : list) {
            StuffPromotionDto dto = new StuffPromotionDto();
            BeanUtils.copyProperties(promotion, dto);
            dto.setRebateValue(stuffReBateService.findStuffRebateValue(promotion.getId()));
            dto.setOrderNum(null);
            dto.setUrl(promotionService.findPromtoionUrl(promotion, DeviceEnum.asDeviceEnum(device)));
            
//            StuffCoupon stuffCoupon = stuffCouponDao.findStuffCouponsByStuffId(promotion.getStuffId());
//            if(stuffCoupon != null){
//            	StuffCouponDto stuffCouponDto = new StuffCouponDto();
//    	        BeanUtils.copyProperties(stuffCoupon, stuffCouponDto);
//    	        dto.setCoupon(stuffCouponDto);
//            }
            dtos.add(dto);
        }
        return new Page<StuffPromotionDto>(length, pageManager.getPage(), pageManager.getPageSize(), dtos);
    }

}
