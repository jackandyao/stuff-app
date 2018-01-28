package com.qbao.aisr.app.service.ad;

import com.qbao.aisr.app.model.AdQbao;

import java.util.List;

/**
 * @author wangping
 * @createTime 2017/3/17 下午4:37
 * $$LastChangedDate: 2017-03-17 18:01:36 +0800 (Fri, 17 Mar 2017) $$
 * $$LastChangedRevision: 268 $$
 * $$LastChangedBy: wangping $$
 */
public interface IAdQbaoService {

    public List<AdQbao> findByLocationId(int locationId, int limit);
}
