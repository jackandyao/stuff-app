package com.qbao.aisr.app.service.search;

import java.io.UnsupportedEncodingException;

import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.dto.search.ZwStuffDetailDto;
import com.qbao.aisr.app.model.search.Suggest;
import com.qbao.aisr.app.model.search.ZnStuff;
import com.qbao.aisr.app.model.search.ZwStuff;

/**
 * @author shuaizhihu
 * @createTime 2017/3/11 14:24
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
public interface ISearchStuffService {

    Page<ZnStuff> searchZn(long userId,String kw,String sort,int page,int size) throws UnsupportedEncodingException, HttpProcessException;

    Page<ZwStuff> searchZw(long userId,String kw,String source,String sort,int page,int size,String activities,int coupon) throws UnsupportedEncodingException, HttpProcessException;

    Page<ZwStuff> searchZwSim(long userId,String kw,String cId,String source,String sort,int page,int size) throws UnsupportedEncodingException, HttpProcessException;

    Page<Suggest> suggest(String kw,int page,int size) throws UnsupportedEncodingException, HttpProcessException;

    ZwStuffDetailDto searchStuffById(long userId,long stuffId,int device);
}
