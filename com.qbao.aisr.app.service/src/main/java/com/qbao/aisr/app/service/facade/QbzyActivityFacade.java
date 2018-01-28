package com.qbao.aisr.app.service.facade;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.qbao.aisr.app.common.enums.DeviceEnum;
import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.common.util.BeanUtils;
import com.qbao.aisr.app.dto.QbaozyActivityDto;
import com.qbao.aisr.app.dto.StuffCouponDto;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.model.search.ZwStuff;
import com.qbao.aisr.app.service.promotion.IPromotionUrlService;
import com.qbao.aisr.app.service.qbzy.IQbzyActivityService;
import com.qbao.aisr.app.service.qbzy.IQbzyClassesService;
import com.qbao.aisr.app.service.rebate.IStuffReBateService;

/**
 * @author zhangjun
 * @createTime 2017/6/11 16:38
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Component
public class QbzyActivityFacade {
    @Autowired
    IQbzyActivityService iQbaoActivityService;
    
    @Autowired
    IQbzyClassesService iQbaoClassesService;
    
    @Autowired
    IPromotionUrlService iPromotionUrlService;
    
    @Autowired
    IStuffReBateService iStuffReBateService;
    
    public Page<StuffPromotionDto> goodsList(long userId,long cid, int device,int page,int size) throws HttpProcessException {
        Page<ZwStuff> result = iQbaoActivityService.goodsList(cid,page,size);
        int total  = result.getTotalNumber();
        List<StuffPromotionDto> list = Lists.newArrayList();
        for (ZwStuff zwStuff : result.getItems()) {
        	StuffPromotionDto dto = new StuffPromotionDto();
        	BeanUtils.copy(zwStuff, dto);
        	String url = iPromotionUrlService.findPromtoionUrl(zwStuff, DeviceEnum.asDeviceEnum(device));
        	if(StringUtils.isNotEmpty(url)) dto.setUrl(url);
        	StuffCouponDto coupon = new StuffCouponDto();
        	BeanUtils.copy(zwStuff, coupon);
        	dto.setCoupon(coupon);
        	dto.setRebateValue(iStuffReBateService.findStuffRebateValue(zwStuff.getId()));
        	list.add(dto);
		}
        return new Page<StuffPromotionDto>(total,result.getCurrentIndex(),result.getPageSize(),list);
    }
    
    
    public QbaozyActivityDto findActivityById(Long id){
    	return iQbaoActivityService.findById(id);
    }

}
