package com.qbao.aisr.app.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.service.stuff.IStuffPromotionService;

/**
 * @author liaijun
 * @createTime 17/3/8 上午11:59
 * $$LastChangedDate: 2017-03-30 16:51:33 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 595 $$
 * $$LastChangedBy: liaijun $$
 */
@Component
public class StuffFacade {
    @Autowired
    private IStuffPromotionService stuffPromotionService;

    /**
     * 返回商品信息包括返利多少,多表查询
     * @param stuffId
     * @return
     */
    public StuffPromotionDto findStuffPromotionInfo(Long stuffId, int device) {

        return stuffPromotionService.findStuffPromotionInfo(stuffId, device);

    }


}
