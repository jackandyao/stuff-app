package com.qbao.aisr.app.service.dislikestuff;

import com.qbao.aisr.app.model.StuffPromotion;

import java.util.List;

/**
 * @author xueming
 * @createTime 17/3/6 下午3:29
 * $$LastChangedDate: 2017-04-10 11:07:45 +0800 (Mon, 10 Apr 2017) $$
 * $$LastChangedRevision: 661 $$
 * $$LastChangedBy: louxueming $$
 */
public interface IUserDislikeStuffService {
    public Boolean updateUserDislikeStuff(Long userId, Long stuffId);
    public List<Long>  fetchDisLikeStuffIds(Long userId);
}
