package com.qbao.aisr.app.service.search.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qbao.aisr.app.common.enums.DeviceEnum;
import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.dto.StuffCouponDto;
import com.qbao.aisr.app.dto.search.ZwStuffDetailDto;
import com.qbao.aisr.app.model.StuffCoupon;
import com.qbao.aisr.app.model.StuffPromotion;
import com.qbao.aisr.app.model.search.Suggest;
import com.qbao.aisr.app.model.search.ZnStuff;
import com.qbao.aisr.app.model.search.ZwStuff;
import com.qbao.aisr.app.repository.mybatis.dao.coupon.StuffCouponDao;
import com.qbao.aisr.app.repository.mybatis.dao.shop.StuffPromotionDao;
import com.qbao.aisr.app.repository.rest.search.KwSuggestBiz;
import com.qbao.aisr.app.repository.rest.search.StuffSearchBiz;
import com.qbao.aisr.app.service.promotion.IPromotionUrlService;
import com.qbao.aisr.app.service.rebate.impl.StuffReBateService;
import com.qbao.aisr.app.service.search.ISearchStuffService;

/**
 * @author shuaizhihu
 * @createTime 2017/3/11 16:43
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Service
public class SearchStuffServiceImpl implements ISearchStuffService {

    Logger logger = LoggerFactory.getLogger(SearchStuffServiceImpl.class);

    @Autowired
    StuffSearchBiz stuffSearchBiz;

    @Autowired
    KwSuggestBiz kwSuggestBiz;
    
    @Autowired
    private StuffPromotionDao stuffPromotionDao;
    
    @Autowired
    private IPromotionUrlService promotionService;
    
    @Autowired
    private StuffCouponDao stuffCouponDao;

    @Autowired
    private StuffReBateService stuffRebateService;
    @Override
    public Page<ZnStuff> searchZn(long userId, String kw, String sort, int page, int size) throws UnsupportedEncodingException, HttpProcessException {
        String result = stuffSearchBiz.searchZn(userId,kw,sort,page,size);
        int total = 0;
        List<ZnStuff> list = new ArrayList<ZnStuff>();
//        System.out.println(result);
        JSONObject resultObject = JSON.parseObject(result);
        String returnCode = resultObject.getString("returnCode");
        if("1000".equals(returnCode)){
            JSONObject dataObject = resultObject.getJSONObject("data");
            total = dataObject.getInteger("total");
            JSONArray dataArray = dataObject.getJSONArray("list");
            list.addAll(JSON.parseArray(dataArray.toJSONString(),ZnStuff.class));
        }else{
            logger.error("查询出错！ result:"+result);
            throw new RuntimeException("查询出错！");
        }
        return new Page<ZnStuff>(total,page,size,list);
    }

    @Override
    public Page<ZwStuff> searchZw(long userId, String kw, String source, String sort, int page, int size,String activities,int coupon)
            throws UnsupportedEncodingException, HttpProcessException {
        String result = stuffSearchBiz.searchZw(userId,kw,source,sort,page,size,  activities,  coupon);
        int total = 0;
        List<ZwStuff> list = new ArrayList<ZwStuff>();
        //        System.out.println(result);
        JSONObject resultObject = JSON.parseObject(result);
        String code = resultObject.getString("errorCode");
        if("0".equals(code)){
            JSONObject dataObject = resultObject.getJSONObject("data");
            total = dataObject.getInteger("totalCount");
            JSONArray dataArray = dataObject.getJSONArray("dataList");
            list.addAll(JSON.parseArray(dataArray.toJSONString(),ZwStuff.class));
        }else{
            logger.error("查询出错！ result:"+result);
            throw new RuntimeException("查询出错！");
        }
        return new Page<ZwStuff>(total,page,size,list);
    }

    @Override
    public Page<ZwStuff> searchZwSim(long userId, String kw, String cId, String source, String sort, int page, int size)
            throws UnsupportedEncodingException, HttpProcessException {
        String result = stuffSearchBiz.searchZwSim(userId,kw,cId,source,sort,page,size);
        int total = 0;
        List<ZwStuff> list = new ArrayList<ZwStuff>();
        //        System.out.println(result);
        JSONObject resultObject = JSON.parseObject(result);
        String code = resultObject.getString("errorCode");
        if("0".equals(code)){
            JSONObject dataObject = resultObject.getJSONObject("data");
            total = dataObject.getInteger("totalCount");
            JSONArray dataArray = dataObject.getJSONArray("dataList");
            list.addAll(JSON.parseArray(dataArray.toJSONString(),ZwStuff.class));
        }else{
            logger.error("查询出错！ result:"+result);
            throw new RuntimeException("查询出错！");
        }
        return new Page<ZwStuff>(total,page,size,list);
    }

    @Override
    public Page<Suggest> suggest(String kw, int page, int size) throws UnsupportedEncodingException, HttpProcessException {
        String result = kwSuggestBiz.suggest(kw,page,size);
        int total = 0;
        List<Suggest> list = new ArrayList<Suggest>();
        //        System.out.println(result);
        JSONObject resultObject = JSON.parseObject(result);
        String code = resultObject.getString("errorCode");
        if("0".equals(code)){
            JSONObject dataObject = resultObject.getJSONObject("data");
            total = dataObject.getInteger("totalCount");
            JSONArray dataArray = dataObject.getJSONArray("dataList");
            list.addAll(JSON.parseArray(dataArray.toJSONString(),Suggest.class));
        }else{
            logger.error("查询出错！ result:"+result);
            throw new RuntimeException("查询出错！");
        }
        return new Page<Suggest>(total,page,size,list);
    }

    
  //@RedisCache(expire = 60 * 60, clazz = ZwStuffDto.class, cacheType = CacheType.OBJECT)
    public ZwStuffDetailDto searchStuffById(long userId,long stuffId,int device) {
        StuffPromotion stuffPromotion=stuffPromotionDao.findStuffPromotionById(stuffId);
        if(stuffPromotion==null){
            logger.error("stuffId=["+stuffId+"] 在商品表中不存在");
            return new ZwStuffDetailDto();
        }
        ZwStuffDetailDto dto=new ZwStuffDetailDto();
        StuffCouponDto cou=new StuffCouponDto();
        
        dto.setImgUrl(stuffPromotion.getImgUrl());
        dto.setName(stuffPromotion.getName());
        dto.setSaleCount(stuffPromotion.getOrderNum());
        dto.setSource(stuffPromotion.getSource());
        dto.setId(stuffPromotion.getId());
        dto.setCopyWriter(stuffPromotion.getCopyWriter());
        BigDecimal reservePrice=stuffPromotion.getReservePrice();
        BigDecimal finalPrice=stuffPromotion.getReservePrice();
        if(reservePrice!=null) {
            dto.setPrice(reservePrice.doubleValue());
        }
        if(finalPrice!=null) {
            dto.setFinalPrice(finalPrice.doubleValue());
        }
        dto.setLinkUrl(promotionService.findPromtoionUrl(stuffPromotion, DeviceEnum.asDeviceEnum(device)));
        StuffCoupon stuffCoupon = stuffCouponDao.findStuffCouponsByStuffId(stuffId);
        if(stuffCoupon!=null) {
        	cou.setLink(stuffCoupon.getLink());
        	cou.setValue(stuffCoupon.getValue());
        	cou.setType(stuffCoupon.getType());
        	dto.setCoupon(cou);
        }
        dto.setId(stuffPromotion.getId());
        dto.setRebateValue(stuffRebateService.findStuffRebateValue(stuffPromotion.getId()));
        return dto;
    }
}
