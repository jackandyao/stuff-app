package com.qbao.aisr.app.service.ad;

import com.qbao.aisr.app.model.AdJu;

import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/7 上午10:33
 * $$LastChangedDate: 2017-03-10 21:09:54 +0800 (Fri, 10 Mar 2017) $$
 * $$LastChangedRevision: 127 $$
 * $$LastChangedBy: wangping $$
 */
public interface IAdJuService {

    public List<AdJu> findByLocationId(int locationId, int limit);

}
