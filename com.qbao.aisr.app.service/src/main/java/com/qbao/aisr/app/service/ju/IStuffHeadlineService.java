package com.qbao.aisr.app.service.ju;

import com.qbao.aisr.app.model.StuffHeadline;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/6 上午11:56
 * $$LastChangedDate: 2017-03-07 10:25:05 +0800 (Tue, 07 Mar 2017) $$
 * $$LastChangedRevision: 81 $$
 * $$LastChangedBy: wangping $$
 */
public interface IStuffHeadlineService {
    public List<StuffHeadline> findStuffPromotionByCatId( int size);
}
