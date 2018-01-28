package com.qbao.aisr.app.service.facade;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.dto.UserOrderDto;
import com.qbao.aisr.app.dto.UserOrderInfoDto;
import com.qbao.aisr.app.service.user.IUserOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 用户订单(记录订单)
 * @author liaijun
 * @createTime 17/3/7 下午2:21
 * $$LastChangedDate: 2017-06-09 11:16:41 +0800 (Fri, 09 Jun 2017) $$
 * $$LastChangedRevision: 1147 $$
 * $$LastChangedBy: zhangjun $$
 */
@Component
public class UserOrderFacade{
    @Autowired
    private IUserOrderService userOrderService;

    /**
     * 订单记录
     * @param
     * @return
     */
    public  UserOrderDto  findUserStuffPromotionById(Long id){

        return userOrderService.findUserStuffPromotionById(id);
    }

    /**
     * 包括以返宝券和未返宝券
     * @param userId
     * @return
     */
    public List<UserOrderInfoDto> findUserStuffPromotionByUserId(Long userId, int page, int size, Integer status, int device) {
        return userOrderService.findUserStuffPromotionByUserId(userId, page, size, status, device);

    }


    /**
     * 订单逻辑删除
     * @param id
     */
    public void modifyUserStuffPromo( long id){
         userOrderService.modifyUserStuffPromo(id);
    }

    /**
     *  我的好货接口宝券统计( 获取所有, 获取以返回宝券,获取未返宝券)
     * @param userId
     * @param
     * @return
     */
    public Map<String,Long> countUserStuffPromotionByUserIdStatus(Long userId){
        return userOrderService.countUserStuffPromotionByUserIdStatus(userId);
    }

    /**
     * 新增订单
     * 
     * @param userId
     * @param orderId
     * @param source
     * @return
     */
    public void saveUserStuffPromo(Long userId, String orderId, String source, Integer device, Integer isPay, Integer isPromotion, String channel) {
        String[] orderIds =  StringUtils.split(orderId, Constant.ORDER_ID_DELIMITER);
        if(null !=orderIds) {
            for (String id : orderIds) {
                userOrderService.saveUserStuffPromo(userId, id, source, device, isPay, isPromotion, channel);
            }
        }
    }

    /**
     * 用户返券数
     *
     * @param userId
     * @param orderId
     * @param source
     * @return
     */
    public Long queryUserRebateValue(Long userId, String orderId, String source) {
        return userOrderService.queryUserRebateValue(userId, orderId, source);
    }
}
