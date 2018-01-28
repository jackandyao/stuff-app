package com.qbao.aisr.app.service.stuffrecommend;

import java.util.List;

import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.dto.search.ZwStuffDetailDto;
import com.qbao.aisr.app.dto.search.ZwStuffOnedayDto;
import com.qbao.aisr.app.model.StuffPromotion;

/**
 * @author xueming
 * @createTime 17/3/6 下午3:29
 * $$LastChangedDate: 2017-07-12 11:37:08 +0800 (Wed, 12 Jul 2017) $$
 * $$LastChangedRevision: 1427 $$
 * $$LastChangedBy: zhangjun $$
 */
public interface IStuffRecommendService {
    public List<StuffPromotion> stuffRecommendHomePage(Long userId,Integer page, Integer size);

    public List<StuffPromotion> stuffRecommend(Long userId,Integer page, Integer size);
    
    List<Long> fetchBuyRecommendIds(Long userId);
    List<Long> fetchViewRecommendIds(Long userId);
    List<Long> fetchRecommendIds(Long userId);
    
    
    Page<ZwStuffDetailDto> zwSimilar(long stuffId, int page, int size, int device);
	Page<ZwStuffDetailDto> zwRelated(long stuffId, int page, int size, int device);
	Page<ZwStuffDetailDto> zwHot(int page, int size, int device);
	Page<ZwStuffDetailDto> zwGuess(long user_id, int page, int size, int device);
	
	
	Page<ZwStuffOnedayDto> oneday(long userid, String userProfile, int page, int size, int device);
}
