package com.qbao.aisr.app.service.ju;

import com.qbao.aisr.app.model.StuffJu;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/6 上午11:56
 * $$LastChangedDate: 2017-03-07 11:35:17 +0800 (Tue, 07 Mar 2017) $$
 * $$LastChangedRevision: 83 $$
 * $$LastChangedBy: wangping $$
 */
public interface IStuffJuService {
    public List<StuffJu> findStuffJu(int size);

    public StuffJu findStuffJuById(long id);
}
