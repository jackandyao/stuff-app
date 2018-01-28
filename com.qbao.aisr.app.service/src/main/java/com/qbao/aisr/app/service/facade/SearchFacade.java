package com.qbao.aisr.app.service.facade;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qbao.aisr.app.common.enums.DeviceEnum;
import com.qbao.aisr.app.common.exception.NoValidDataException;
import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.common.util.BeanUtils;
import com.qbao.aisr.app.dto.StuffCouponDto;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.dto.search.ZnStuffDto;
import com.qbao.aisr.app.dto.search.ZwStuffDetailDto;
import com.qbao.aisr.app.dto.search.ZwStuffDto;
import com.qbao.aisr.app.model.StuffPromotion;
import com.qbao.aisr.app.model.search.Suggest;
import com.qbao.aisr.app.model.search.ZnStuff;
import com.qbao.aisr.app.model.search.ZwStuff;
import com.qbao.aisr.app.service.promotion.IPromotionUrlService;
import com.qbao.aisr.app.service.rebate.IStuffReBateService;
import com.qbao.aisr.app.service.search.ISearchStuffService;
import com.qbao.aisr.app.service.stuff.IStuffPromotionService;

/**
 * @author shuaizhihu
 * @createTime 2017/3/11 15:00
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Component
public class SearchFacade {
    public Logger logger = Logger.getLogger(getClass());
    @Autowired
    ISearchStuffService searchStuffService;

    @Autowired
    IStuffReBateService stuffReBateService;

    @Autowired
    IStuffPromotionService iStuffPromotionService;

    @Autowired
    private IPromotionUrlService promotionUrlService;

//    @Autowired
//    private StuffCouponDao stuffCouponDao;
    
    public Page<ZnStuffDto> searchZn(long userId,String kw,String sort,int page,int size) throws UnsupportedEncodingException, HttpProcessException {
        Page<ZnStuff> result = searchStuffService.searchZn(userId,kw,sort,page,size);
        int total = result.getTotalNumber();
        logger.info("SearchZn total result : "+total );
        List<ZnStuffDto> list = BeanUtils.mapList(result.getItems(),ZnStuffDto.class);
        logger.info("SearchZn convert to dto size : "+list.size() );
        return new Page<ZnStuffDto>(total,result.getCurrentIndex(),result.getPageSize(),list);
    }

    public Page<ZwStuffDto> searchZw(long userId,String kw,String source,String sort,int page,int size,int device,String activities,int coupon) throws UnsupportedEncodingException, HttpProcessException {
        Page<ZwStuff> result = searchStuffService.searchZw(userId,kw,source,sort,page,size,  activities,  coupon);
        return this.convertToZwStuffDto(result,device);
    }

    public Page<ZwStuffDto> searchZwSim(long userId,long stuffId,int page,int size,int device) throws UnsupportedEncodingException, HttpProcessException {
        StuffPromotion stuffPromotion = iStuffPromotionService.findStuffPromotion(stuffId);
        if(null != stuffPromotion){
        Page<ZwStuff> result = searchStuffService.searchZwSim(userId,stuffPromotion.getName(),stuffPromotion.getCatId()+"","","",page,size);
        List<ZwStuff> newList = new ArrayList<ZwStuff>();
        for(ZwStuff zwStuff:result.getItems()){
            if(stuffId!=zwStuff.getId()){
                newList.add(zwStuff);
            }
            if(newList.size()==(size-1)){
                break;
            }
        }
        Page<ZwStuff> newResult = new Page<ZwStuff> (result.getTotalNumber(),result.getCurrentIndex(),result.getPageSize()-1,newList);
        return this.convertToZwStuffDto(newResult,device);
        }
        return new Page<ZwStuffDto>();
    }
    public StuffPromotionDto findStuffByStuffId(long stuffId) throws NoValidDataException{
        StuffPromotion stuffPromotion = iStuffPromotionService.findStuffPromotion(stuffId);
        stuffPromotion.setRebateValue(stuffReBateService.findStuffRebateValue(stuffPromotion.getId()));

        if(stuffPromotion==null){
               throw new NoValidDataException("此Id不存在对应的商品记录");
        }
        StuffPromotionDto stuffPromotionDto = new StuffPromotionDto();
        BeanUtils.copy(stuffPromotion, stuffPromotionDto);
        
//        StuffCoupon stuffCoupon = stuffCouponDao.findStuffCouponsByStuffId(stuffId);
//        if(stuffCoupon != null){
//        	StuffCouponDto stuffCouponDto = new StuffCouponDto();
//	        BeanUtils.copy(stuffCoupon, stuffCouponDto);
//	        stuffPromotionDto.setCoupon(stuffCouponDto);
//        }
        return stuffPromotionDto;
    }

    public Page<Suggest> suggest(String kw,int page,int size) throws UnsupportedEncodingException, HttpProcessException {
        return searchStuffService.suggest(kw,page,size);
    }



    private Page<ZwStuffDto> convertToZwStuffDto(Page<ZwStuff> zwStuffs,int device){
        List<ZwStuffDto> list = new ArrayList<>();
        int total = zwStuffs.getTotalNumber();
        for(ZwStuff zwStuff : zwStuffs.getItems()){
            ZwStuffDto dto = new ZwStuffDto();
            BeanUtils.copy(zwStuff,dto);
            dto.setRebateValue(stuffReBateService.findStuffRebateValue(dto.getStuffId()));
            dto.setLinkUrl(promotionUrlService.findPromtoionUrl(zwStuff, DeviceEnum.asDeviceEnum(device)));

            StuffCouponDto coupon = new StuffCouponDto();
            coupon.setLink(dto.getLink());
            coupon.setValue(new BigDecimal(dto.getValue()));
            dto.setCoupon(coupon);
            list.add(dto);
        }
        return new Page<ZwStuffDto>(total,zwStuffs.getCurrentIndex(),zwStuffs.getPageSize(),list);
    }
    
    public ZwStuffDetailDto searchStuffById(long userId,long stuffId, int device) {
        return searchStuffService.searchStuffById(userId, stuffId, device);
    }

}
