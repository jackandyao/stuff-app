package com.qbao.aisr.app.service.hot;

import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.dto.StuffHotClassDto;
import com.qbao.aisr.app.dto.StuffHotGoodsDto;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/10 下午5:07
 * $$LastChangedDate: 2017-03-30 16:11:14 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 588 $$
 * $$LastChangedBy: liaijun $$
 */
public interface IStuffHotClassService {
    // 获取热卖好货所有类目
    public List<StuffHotClassDto> queryHotClassCat();

    //根据id获取热卖好货
    public Page<StuffHotGoodsDto> queryHotClassById(Long id, int page, int size, int device) throws Exception;

    public Page<StuffHotGoodsDto> queryHotClassByCatId(String catId, int page, int size, int device) throws Exception;
}
