package com.qbao.aisr.app.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.dto.QbaozyActivityDto;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.service.facade.QbzyActivityFacade;
import com.qbao.aisr.app.web.controller.base.BaseController;

/**
 * @author zhangjun
 * @createTime 17/5/24 上午10:22
 * $$LastChangedDate: 2017-03-30 16:51:33 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 595 $$
 * $$LastChangedBy: zhangjun $$
 */
@Controller
@RequestMapping("/stuff/coupon/")
public class StuffCouponController extends BaseController {
    private Logger logger = Logger.getLogger(StuffCouponController.class);
    @Autowired
    private QbzyActivityFacade qbzyActivityFacade;


    /**
     * 优惠券商品分类接口
     * @param request
     * @param specialId
     * @return
     */
    @RequestMapping("/goodsClass")
    @ResponseBody
    public Map<String, Object> goodsClass(HttpServletRequest request, @RequestParam("specialId") Long specialId){
    	logger.info("coupon goodsClass start >>> specialId " + specialId);
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        
        try {
        	QbaozyActivityDto activityDto = qbzyActivityFacade.findActivityById(specialId);
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("data", activityDto.getClassesList());
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货优惠券商品分类接口异常:/stuff/special/goodsClass.do?specialId=" + specialId;
            String subject = "有好货优惠券商品分类接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("coupon goodsClass end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
    
    /**
     * 优惠券分类商品列表接口
     * @param request
     * @param cId
     * @param device
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/goodsList")
    @ResponseBody
    public Map<String, Object> goodsList(HttpServletRequest request, @RequestParam("cId")Integer cId, @RequestParam("device") Integer device, @RequestParam("page") Integer page, @RequestParam("size") Integer size){
    	logger.info("coupon goodsList start >>> ");
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        try {
            Page<StuffPromotionDto> data = qbzyActivityFacade.goodsList(userId, cId, device, page, size);
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("data", data.getItems());
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货优惠券分类商品列表接口异常:/stuff/special/goodsList.do?cId="+cId+"&device="+device+"&page="+page+"&size="+size;
            String subject = "有好货优惠券分类商品列表接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("coupon goodsList end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
    
    
    
}
