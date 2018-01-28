package com.qbao.aisr.app.service.facade;

import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.dto.StuffHotGoodsDto;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.service.hot.IStuffHotClassService;
import com.qbao.aisr.app.service.rebate.IStuffReBateService;
import com.qbao.aisr.app.service.stuff.IStuffPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liaijun
 * @createTime 17/3/8 上午11:59
 * $$LastChangedDate: 2017-03-30 16:51:33 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 595 $$
 * $$LastChangedBy: liaijun $$
 */
@Component
public class JuFacade {
    @Autowired
    private IStuffPromotionService stuffPromotionService;
    @Autowired
    private IStuffReBateService stuffReBateService;
    @Autowired
    private IStuffHotClassService stuffHotClassService;

    /**
     * 点击六个类目返回商品(口腔护理专场,母婴联合狂欢,健康好奶等类目)
     * http://wiki.qbao.com/pages/viewpage.action?pageId=18160745
     * @param catId
     * @param page
     * @param size
     * @return
     */
    public Page<StuffHotGoodsDto> findStuffPromotionByCatId(Long catId, int page, int size, int device) throws Exception {
        return stuffHotClassService.queryHotClassByCatId(String.valueOf(catId), page, size, device);
    }

    /**
     * 聚好货模块中的云好货
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public Page<StuffPromotionDto> findStuffPromotionByUserId(Long userId, int page, int size, int device) {


        return stuffPromotionService.findStuffPromotionInfoByUserId(userId, device, page, size);
    }


}
