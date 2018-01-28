package com.qbao.aisr.app.service.facade;

import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.common.util.BeanUtils;
import com.qbao.aisr.app.dto.QbaoZyClassDto;
import com.qbao.aisr.app.dto.search.ZnStuffDto;
import com.qbao.aisr.app.model.QbaoZyClass;
import com.qbao.aisr.app.model.search.ZnStuff;
import com.qbao.aisr.app.service.qbzy.IQbzyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shuaizhihu
 * @createTime 2017/3/12 16:38
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Component
public class QbzyFacade {
    @Autowired
    IQbzyService iQbzyService;

    public Page<ZnStuffDto> goodsList(long userId,long cid,int page,int size) throws HttpProcessException {
        Page<ZnStuff> result = iQbzyService.goodsList(cid,page,size);
        int total  = result.getTotalNumber();
        List<ZnStuffDto> list = BeanUtils.mapList(result.getItems(),ZnStuffDto.class);
        return new Page<ZnStuffDto>(total,result.getCurrentIndex(),result.getPageSize(),list);
    }

    public List<QbaoZyClassDto> goodsClasses(){
        List<QbaoZyClass> list = iQbzyService.goodClasses();
        return BeanUtils.mapList(list,QbaoZyClassDto.class);
    }
}
