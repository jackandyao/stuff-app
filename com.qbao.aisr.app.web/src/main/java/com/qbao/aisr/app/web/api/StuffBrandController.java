package com.qbao.aisr.app.web.api;

import java.util.HashMap;
import java.util.List;
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
import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.dto.AdBrandDto;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.model.AdBrand;
import com.qbao.aisr.app.service.facade.AdBrandFacade;
import com.qbao.aisr.app.web.controller.base.BaseController;

/**
 * @author zhangjun
 * @createTime 17/5/24 上午10:22
 * $$LastChangedDate: 2017-03-30 16:51:33 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 595 $$
 * $$LastChangedBy: zhangjun $$
 */
@Controller
@RequestMapping("/stuff/brand/")
public class StuffBrandController extends BaseController {
    private Logger logger = Logger.getLogger(StuffBrandController.class);
    @Autowired
    private AdBrandFacade adBrandFacade;


    /**
     * 品牌团首页接口
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request, @RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "size", required = false, defaultValue = "8") int size){
    	if(page<1) page=1;
    	logger.info("brand list start >>> page= " + page + ", size=" + size);
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        
        try {
        	List<AdBrandDto> data = adBrandFacade.list(page,size);
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("data", data);
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货品牌团首页接口异常:/stuff/brand/list.do?page=" + page + "&size=" + size;
            String subject = "有好货品牌团首页接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("brand list end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
    
    /**
     * 品牌团商品详细接口
     * @param request
     * @param cId
     * @param device
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Map<String, Object> detail(HttpServletRequest request, @RequestParam("brandId") long brandId, @RequestParam("device") int device, @RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "size", required = false, defaultValue = "8") int size){
    	if(page<1) page=1;
    	logger.info("brand detail start >>> brandId="+brandId+", device = "+device+" ,page= " + page + ", size=" + size);
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        try {
            List<StuffPromotionDto> data = adBrandFacade.detail(brandId, device, page, size);
            AdBrand adbrand = adBrandFacade.findByBrandId(brandId);
            json.put("name", adbrand.getName());
            json.put("imgUrl", adbrand.getImgUrl());
            json.put("offTime", adbrand.getOffTime());
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("data", data);
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货品牌团商品详细接口异常:/stuff/brand/detail.do?brandId="+brandId+", device = "+device+" ,page= " + page + ", size=" + size;
            String subject = "有好货品牌团商品详细接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("brand detail end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
    
    
    
}
