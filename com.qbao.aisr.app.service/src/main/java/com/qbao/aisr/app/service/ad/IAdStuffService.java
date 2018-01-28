package com.qbao.aisr.app.service.ad;

import com.qbao.aisr.app.model.AdStuff;

import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/6 下午1:27
 * $$LastChangedDate: 2017-03-16 11:59:10 +0800 (Thu, 16 Mar 2017) $$
 * $$LastChangedRevision: 235 $$
 * $$LastChangedBy: wangping $$
 */
public interface IAdStuffService {

    public List<AdStuff> findByLocationId(int locationId, int limit);


    public List<AdStuff> findByLocationId(int locationId);

    /**
     *
     * @param source jd, taobao,tmall,qbao
     * @return
     */
    public List<AdStuff> findBySource(String source, int limit);

    /**
     *
     * @param source jd, taobao,tmall,qbao
     * @return
     */
    public List<AdStuff> findByNotFrom(String source, int limit);

}
