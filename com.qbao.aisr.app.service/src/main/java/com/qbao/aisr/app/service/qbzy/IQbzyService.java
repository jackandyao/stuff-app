package com.qbao.aisr.app.service.qbzy;

import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.model.QbaoZyClass;
import com.qbao.aisr.app.model.search.ZnStuff;

import java.util.List;

/**
 * @author shuaizhihu
 * @createTime 2017/3/12 16:00
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
public interface IQbzyService {

    public Page<ZnStuff> goodsList(long id,int page,int size) throws HttpProcessException;

    public List<QbaoZyClass> goodClasses();
}
