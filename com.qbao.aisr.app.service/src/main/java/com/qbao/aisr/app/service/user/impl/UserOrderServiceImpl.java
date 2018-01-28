package com.qbao.aisr.app.service.user.impl;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.enums.RebateTypeEnum;
import com.qbao.aisr.app.common.page.PageManager;
import com.qbao.aisr.app.dto.TaokeDetailDto;
import com.qbao.aisr.app.dto.UserOrderDto;
import com.qbao.aisr.app.dto.UserOrderInfoDto;
import com.qbao.aisr.app.model.TaokeDetail;
import com.qbao.aisr.app.model.UserStuffPromotion;
import com.qbao.aisr.app.repository.mybatis.dao.user.TaokeDetailDao;
import com.qbao.aisr.app.repository.mybatis.dao.user.UserAppealDao;
import com.qbao.aisr.app.repository.mybatis.dao.user.UserOrderDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.repository.redis.dao.RedisDao;
import com.qbao.aisr.app.service.user.IUserOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户订单(记录订单)
 * @author liaijun
 * @createTime 17/3/7 下午2:21
 * $$LastChangedDate: 2017-06-28 16:28:43 +0800 (Wed, 28 Jun 2017) $$
 * $$LastChangedRevision: 1369 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class UserOrderServiceImpl implements IUserOrderService{

    Logger logger = Logger.getLogger(UserOrderServiceImpl.class);
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private TaokeDetailDao taokeDetailDao;
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private UserAppealDao userAppealDao;
    /**
     * 订单记录
     * @param
     * @return
     */
    @RedisCache(expire = 60 * 5, clazz = UserOrderDto.class, cacheType = CacheType.OBJECT)
    public  UserOrderDto  findUserStuffPromotionById(Long id){

        UserStuffPromotion promotion = userOrderDao.findUserStuffPromotionById(id);

        UserOrderDto dto=new UserOrderDto();
        if (promotion == null) {
            return dto;
        }
        String orderId=promotion.getOrderId();
        String source=promotion.getSource();
        dto.setOrderNo(orderId);
        dto.setOrderTime(promotion.getOrderTime());
        dto.setSource(promotion.getSource());
        dto.setAmount(promotion.getPrice());
        dto.setUnit(RebateTypeEnum.asRebateTypeEnum(promotion.getRebateType()).getName());
        dto.setRebateStatus(promotion.getReturnCouponStatus());
        Long appealValue = userAppealDao.sumUserStuffPromotionRebateByUserIdStatus(promotion.getUserId(), orderId,source );
        appealValue=(appealValue==null?0L:appealValue);
        dto.setAppealValue(appealValue);

        Long rebate=promotion.getRebateValue();
        rebate=(rebate==null?0L:rebate);
        dto.setRebateValue(rebate);
        return dto;

    }

    @RedisCache(expire = 60 * 5, clazz = TaokeDetail.class, cacheType = CacheType.LIST)
    public List<TaokeDetail> queryTaokeDetailByOrderId(String orderId) {

        return taokeDetailDao.queryTaokeDetailByOrderId(orderId);
    }
    
    /**
     * 包括以返宝券和未返宝券
     * 缓存加在根据单个订单查询接口上:queryTaokeDetailByOrderId(promotion.getOrderId());
     * 
     * @param userId
     * @return
     */
    public List<UserOrderInfoDto> findUserStuffPromotionByUserId(Long userId, int page, int size, Integer status, int device) {
        PageManager pageManager = new PageManager(page, size);
        List<UserStuffPromotion> list=fetchUserStuffPromtions(userId,status);
        List<UserOrderInfoDto> returns=new ArrayList<UserOrderInfoDto>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i=page*size; i<list.size() ; i++){
                if(returns.size() >=size){
                    break;
                }
                UserStuffPromotion promotion = list.get(i);
                List<TaokeDetail> taokeDetails = queryTaokeDetailByOrderId(promotion.getOrderId());// 此接口加缓存
                List<TaokeDetailDto> taokeDetailDtos = new ArrayList<TaokeDetailDto>();
                int stuffNum = 0;
                if (CollectionUtils.isNotEmpty(taokeDetails)) {
                    for (TaokeDetail detail : taokeDetails) {
                        if(null ==detail.getStuffId() )
                            continue;
                        TaokeDetailDto taokeDetailDto = new TaokeDetailDto();
                        BeanUtils.copyProperties(detail, taokeDetailDto);
                        taokeDetailDto.setFinalPrice(detail.getPrice());
                        taokeDetailDto.setRebateValue(detail.getRebateValue());
                        taokeDetailDto.setUnit(RebateTypeEnum.asRebateTypeEnum(detail.getRebateType()).getName());
                        if (Constant.IOS == device) {
                            taokeDetailDto.setClickUrl(detail.getIosClickUrl());
                        } else {
                            taokeDetailDto.setClickUrl(detail.getAndroidClickUrl());
                        }
                        taokeDetailDtos.add(taokeDetailDto);
                        Integer num = detail.getStuffNum();
                        if (num != null) {
                            stuffNum += num;
                        }
                    }
                }
                if(taokeDetailDtos.size() >0 ) {
                    UserOrderInfoDto dto = new UserOrderInfoDto();
                    dto.setId(promotion.getId());
                    dto.setOrderNo(promotion.getOrderId());
                    dto.setAmount(promotion.getPrice());
                    dto.setRebateStatus(promotion.getReturnCouponStatus());
                    //获取返利类型RMB或宝券
                    dto.setUnit(RebateTypeEnum.asRebateTypeEnum(promotion.getRebateType()).getName());
                    String orderId=promotion.getOrderId();
                    String source=promotion.getSource();
                    Long appealValue = userAppealDao.sumUserStuffPromotionRebateByUserIdStatus(promotion.getUserId(), orderId,source );
                    appealValue=(appealValue==null?0L:appealValue);
                    Long rebate=promotion.getRebateValue();
                    rebate=(rebate==null?0L:rebate);

                    dto.setRebateValue(appealValue+rebate);
                    dto.setStuffNum(stuffNum);
                    dto.setItem(taokeDetailDtos);
                    returns.add(dto);
                }
            }
        }
        return returns;
    }

    @RedisCache(expire = 60 * 5, clazz = UserStuffPromotion.class, cacheType = CacheType.LIST)
    private List<UserStuffPromotion> fetchUserStuffPromtions(Long userId, Integer status){
        return (null != status) ? userOrderDao.findUserStuffPromotionByUserIdStatus(userId, status):  userOrderDao.findUserStuffPromotionByUserId(userId);
       // return Lists.newArrayList();
    }

    /**
     * 订单逻辑删除
     * @param id
     */
    public void modifyUserStuffPromo( long id){

        userOrderDao.modifyUserStuffPromo(id);

        UserStuffPromotion userStuffPromotion = userOrderDao.findUserStuffPromotionById(id);
        String key = generateCacheKey(userStuffPromotion.getOrderId());
        logger.info("try to delete the redis cache key = [" + key + "]");
        redisDao.del(key);
    }

    private String generateCacheKey(String orderId) {
        // // 用类名、方法名、参数名作为缓存的key
        String cacheKey = getClass().getName().concat("_").concat("queryTaokeDetailByOrderId").concat("_").concat(String.valueOf(orderId));
        return cacheKey;
    }
    /**
     *  我的好货接口宝券统计( 获取所有, 获取以返回宝券,获取未返宝券)
     * @param userId
     * @param
     * @return
     */
    public Map<String,Long> countUserStuffPromotionByUserIdStatus(Long userId){
        Long all = countUserStuffPromotionByUserIdStatus(userId, Constant.RETURN);
        Long unReturnCount = countUserStuffPromotionByUserIdStatus(userId, Constant.UNRETURN);
        Long allAppealSum=userAppealDao.sumUserStuffPromotionRebateByUserId(userId,Constant.ISPAYOK);
        allAppealSum=(allAppealSum==null?0L:allAppealSum);
        Long unAppealSum=userAppealDao.sumUserStuffPromotionRebateByUserId(userId,Constant.ISPAYNO);
        unAppealSum=(unAppealSum==null?0L:unAppealSum);

        //加上申诉的返券
        Long allSum = totalReturnRebate(userId);
        allSum=(allSum==null?0L:allSum);
        Long unReturnSum = sumUserStuffPromotionByUserIdStatus(userId, Constant.UNRETURN);
        unReturnSum=(unReturnSum==null?0L:unReturnSum);

        Map<String,Long> orderCount=new HashMap<String, Long>();
        orderCount.put("all",all);
        orderCount.put("UNRETURN",unReturnCount);
        orderCount.put("allSum", allSum+allAppealSum);
        orderCount.put("unReturnSum", unReturnSum+unAppealSum);
        return orderCount;
    }

    @RedisCache(expire = 60 * 5, clazz = Long.class, cacheType = CacheType.OBJECT)
    public Long countUserStuffPromotionByUserIdStatus(Long userId, Integer status) {
        return userOrderDao.countUserStuffPromotionByUserIdStatus(userId, status);
    }

    @RedisCache(expire = 60 * 5, clazz = Long.class, cacheType = CacheType.OBJECT)
    public Long totalReturnRebate(Long userId) {
        return userOrderDao.totalReturnRebate(userId);
    }

    @RedisCache(expire = 60 * 5, clazz = Long.class, cacheType = CacheType.OBJECT)
    public Long sumUserStuffPromotionByUserIdStatus(Long userId, Integer status) {
        return userOrderDao.sumUserStuffPromotionByUserIdStatus(userId, status);
    }

    @Override
    public void saveUserStuffPromo(Long userId, String orderId, String source, Integer device, Integer isPay, Integer isPromotion, String channel) {

        UserStuffPromotion promotion = new UserStuffPromotion();
        Date nowTime = new Date();
        promotion.setSource(source);
        promotion.setUpdateTime(nowTime);
        promotion.setCreateTime(nowTime);
       //promotion.setOrderTime(nowTime);对账ailimama更新订单时间
        promotion.setUserId(userId);
        promotion.setOrderId(orderId);
        promotion.setReturnCouponStatus(-1); // 返宝券状态-1,为订单录入 0: 未返券,1已返券,2返券被收回(以后可能被用到),3返券失败
        promotion.setStatus(-1);//逻辑是否删除：0:删除，1:正常 , -1 初始状态
        
        promotion.setDevice(device);
        promotion.setIsPay(isPay);
        promotion.setIsPromotion(isPromotion);
        promotion.setChannel(channel);
        
        //更新相同orderId的tmall与taobao的订单
        UserStuffPromotion userStuffPromotion = null;
        if("tmall".equals(source) || "taobao".equals(source)) {
        	userStuffPromotion = userOrderDao.findUserStuffPromotionByOrderId(orderId, "tmall");
//        	if (userStuffPromotion == null) userStuffPromotion = userOrderDao.findUserStuffPromotionByOrderId(orderId, "taobao");
        	if (userStuffPromotion != null) {
        		promotion.setSource("tmall");//重复数据来源优先设为tmall
        	} else {
        		userStuffPromotion = userOrderDao.findUserStuffPromotionByOrderId(orderId, "taobao");
        	}
        } else {
        	userStuffPromotion = userOrderDao.findUserStuffPromotionByOrderId(orderId, source);
        }
        if (userStuffPromotion == null) {
            userOrderDao.saveUserStuffPromo(promotion);
        } else {
        	promotion.setOldSource(userStuffPromotion.getSource());
        	userOrderDao.updateUserStuffPromo(promotion);
        }

    }
    @RedisCache(expire = 60 * 30, clazz = Long.class, cacheType = CacheType.OBJECT)
    public Long queryUserRebateValue(Long userId, String orderId, String source) {
        Long sum = 0L;
        Long rebateValue = userAppealDao.sumUserStuffPromotionRebateByUserIdStatus(userId, orderId, source);
        Long stuffRebate = userOrderDao.findUserStuffPromotionByOrderIdSource(userId, orderId, source);
        if (rebateValue != null) {
            sum = rebateValue;
        }
        if (stuffRebate != null) {
            sum += stuffRebate;
        }
        return sum;
    }
}
