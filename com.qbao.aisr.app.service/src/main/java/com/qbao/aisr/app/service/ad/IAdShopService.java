package com.qbao.aisr.app.service.ad;

import com.qbao.aisr.app.model.AdShop;

import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/6 下午1:27
 * $$LastChangedDate: 2017-03-10 21:09:54 +0800 (Fri, 10 Mar 2017) $$
 * $$LastChangedRevision: 127 $$
 * $$LastChangedBy: wangping $$
 */
public interface IAdShopService {
    public List<AdShop> findByLocationId(int locationId, int limit);
}
