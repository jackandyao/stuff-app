package com.qbao.aisr.app.service.stuff;

import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.common.page.PageManager;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.model.StuffPromotion;

import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/7 上午9:08
 * $$LastChangedDate: 2017-03-11 20:41:57 +0800 (周六, 11 三月 2017) $$
 * $$LastChangedRevision: 150 $$
 * $$LastChangedBy: wangping $$
 */
public interface IStuffPromotionService {

    public boolean isAvailable(long shopId);

    public StuffPromotion findStuffPromotion(long stuffId);

    /**
     * 点击六个类目返回商品(口腔护理专场,母婴联合狂欢,健康好奶等类目)
     * http://wiki.qbao.com/pages/viewpage.action?pageId=18160745
     * @param catId
     * @param page
     * @param size
     * @return
     */
    public List<StuffPromotion> findStuffPromotionByCatId(Long catId, int page, int size);

    /**
     * 返回商品信息包括返利多少
     * @param stuffId
     * @return
     */
    public StuffPromotionDto findStuffPromotionInfo(long stuffId,int device);


    /**
     * 聚好货中云好货商品信息
     * @param userId
     * @return
     */
    public Page<StuffPromotionDto> findStuffPromotionInfoByUserId(Long userId, int device, int page, int size);

}
