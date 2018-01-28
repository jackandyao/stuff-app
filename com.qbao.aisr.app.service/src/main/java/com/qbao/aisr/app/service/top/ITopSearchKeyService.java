package com.qbao.aisr.app.service.top;

import com.qbao.aisr.app.model.TopSearchKey;
import com.qbao.aisr.app.model.TopSearchStuff;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/6 下午3:29
 * $$LastChangedDate: 2017-03-07 10:25:05 +0800 (周二, 07 三月 2017) $$
 * $$LastChangedRevision: 81 $$
 * $$LastChangedBy: wangping $$
 */
public interface ITopSearchKeyService {
    public List<TopSearchKey> topSearchKey(int size);
}
