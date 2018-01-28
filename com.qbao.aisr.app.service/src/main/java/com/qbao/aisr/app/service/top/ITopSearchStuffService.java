package com.qbao.aisr.app.service.top;

import com.qbao.aisr.app.model.TopSearchStuff;
import com.qbao.aisr.app.model.TopSearchStuffComplex;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/6 下午3:29
 * $$LastChangedDate: 2017-03-30 17:17:11 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 596 $$
 * $$LastChangedBy: wangping $$
 */
public interface ITopSearchStuffService {
    public List<TopSearchStuff> topSearchStuff(int size);

    public List<TopSearchStuffComplex> topSearchStuffComplex(Integer page, Integer size,int device);
}
