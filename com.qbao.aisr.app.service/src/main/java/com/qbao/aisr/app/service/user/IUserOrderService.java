package com.qbao.aisr.app.service.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.qbao.aisr.app.dto.UserOrderDto;
import com.qbao.aisr.app.dto.UserOrderInfoDto;
import com.qbao.aisr.app.model.UserStuffPromotion;
import org.apache.ibatis.annotations.Param;

/**
 * @author liaijun
 * @createTime 17/3/7 下午2:19
 * $$LastChangedDate: 2017-06-09 11:16:41 +0800 (Fri, 09 Jun 2017) $$
 * $$LastChangedRevision: 1147 $$
 * $$LastChangedBy: zhangjun $$
 */
public interface IUserOrderService {
    /**
     * 订单记录
     * @param
     * @return
     */
    public UserOrderDto findUserStuffPromotionById(Long id );

    /**
     * 包括以返宝券和未返宝券
     * @param userId
     * @return
     */
    public List<UserOrderInfoDto> findUserStuffPromotionByUserId(Long userId, int page, int size, Integer status, int device);

    /**
     * 订单逻辑删除
     * @param id
     */
    public void modifyUserStuffPromo( long id);

    /**
     *  我的好货接口宝券统计( 获取所有, 获取以返回宝券,获取未返宝券)
     * @param userId
     *
     * @return
     */
    public Map<String,Long> countUserStuffPromotionByUserIdStatus(Long userId);

    /**
     * 新增订单
     * 
     * @param userId
     * @param orderId
     * 订单Id
     * @param source
     * 来源
     * @return
     */
    public void saveUserStuffPromo(Long userId, String orderId, String source, Integer device, Integer isPay, Integer isPromotion, String channel);

    /**
     * 用户返券数
     *
     * @param userId
     * @param orderId
     * @param source
     * @return
     */
    public Long queryUserRebateValue(Long userId, String orderId, String source);

}

