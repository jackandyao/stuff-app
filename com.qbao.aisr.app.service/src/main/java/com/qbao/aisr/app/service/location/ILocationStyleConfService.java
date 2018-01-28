package com.qbao.aisr.app.service.location;

import com.qbao.aisr.app.model.LocationStyleConf;

import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/6 下午1:57
 * $$LastChangedDate: 2017-03-08 14:51:26 +0800 (Wed, 08 Mar 2017) $$
 * $$LastChangedRevision: 102 $$
 * $$LastChangedBy: wangping $$
 */
public interface ILocationStyleConfService {

    // public List<LocationStyleConf> findConfByStyleId(int styleId, int type);

    public List<LocationStyleConf> findConfByStyleId(int styleId);

    public LocationStyleConf findConfById(int id);
}
