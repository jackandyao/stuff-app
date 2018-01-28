package com.qbao.aisr.app.service.qbzy;

import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.dto.QbaozyActivityDto;
import com.qbao.aisr.app.model.search.ZwStuff;

/**
 * @author zhangjun
 * @createTime 2017/6/11 16:00
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
public interface IQbzyActivityService {
    
    QbaozyActivityDto findById(Long id);

	Page<ZwStuff> goodsList(long cid, int page, int size) throws HttpProcessException;
}
