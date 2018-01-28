package com.qbao.aisr.app.web.api;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.dto.BannerDto;
import com.qbao.aisr.app.dto.BannerSaleStuffConfDto;
import com.qbao.aisr.app.dto.BannerStuffConfDto;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.model.AdJu;
import com.qbao.aisr.app.service.facade.AdFacade;
import com.qbao.aisr.app.web.controller.base.BaseController;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangping
 * @createTime 17/3/8 上午11:29
 * $$LastChangedDate: 2017-08-07 10:16:47 +0800 (Mon, 07 Aug 2017) $$
 * $$LastChangedRevision: 1482 $$
 * $$LastChangedBy: liaijun $$
 */
@Controller
@RequestMapping("/stuff/ad/")
public class AdController extends BaseController {

    private Logger logger = Logger.getLogger(AdController.class);
    @Autowired
    private AdFacade adFacade;
//
//    @RequestMapping("/banner")
//    @ResponseBody
//    public Map<String, Object> fetchAdBanner(HttpServletRequest request,@RequestParam(value = "locationId", required = true) int locationId, @RequestParam(value = "device", required = true) int device) {
//        logger.info("fetching ad banner locationId=["+locationId+"], device=["+device+"]  ...");
//        long start = System.currentTimeMillis();
//
//        Map<String, Object> response = new HashMap<String, Object>();
//        long userId = getCurrentUserId(request);
//        response.put("userId", userId);
//        try {
//            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
//            List<BannerDto> data = adFacade.fetchAdBanner(locationId,device);
//            response.put("data", data);
//            response.put("success", true);
//            response.put("message", "Ok");
//        } catch (Exception ex) {
//            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
//            response.put("success", false);
//            String msg = ExceptionUtils.getFullStackTrace(ex);
//            response.put("message", msg);
//            String notifyMsg = "fetching Ad banner meet error: locationId=["+locationId+"], device=["+device+"]";
//            NotifierUtil.notifyByPhone(notifyMsg);
//            NotifierUtil.notifyByEmail(notifyMsg, msg);
//            logger.error(notifyMsg,ex);
//        }
//        logger.info("finish to fetch ad banner. and cost : " + (System.currentTimeMillis() - start) + ".ms");
//        return response;
//    }

    @RequestMapping("/banner")
    @ResponseBody
    public Object fetchAdBanner(HttpServletRequest request,@RequestParam(value="callback",required = false) String callback,@RequestParam(value = "locationId", required = true) int locationId, @RequestParam(value = "device", required = false) Integer device) {
        Object result = null;
        logger.info("fetching ad banner locationId=["+locationId+"], device=["+device+"]  ...");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            List<BannerDto> data = adFacade.fetchAdBanner(locationId,device);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            String notifyMsg = "fetching Ad banner meet error: locationId=["+locationId+"], device=["+device+"]";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        logger.info("finish to fetch ad banner. and cost : " + (System.currentTimeMillis() - start) + ".ms");
        if (StringUtils.isBlank(callback)) {
            result = response;
        } else {
            result = bindJsonp(callback, response);
        }
        return result;
    }

    private MappingJacksonValue bindJsonp(String callback, Object data) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(data);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }


    @RequestMapping("/stuff")
    @ResponseBody
    public Map<String, Object> fetchAdStuff(HttpServletRequest request,@RequestParam(value = "locationId", required = true) int locationId, @RequestParam(value = "device", required = true) int device) {
        logger.info("fetching ad Stuff locationId=["+locationId+"], device=["+device+"]  ...");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            List<StuffPromotionDto> data = adFacade.fetchAdStuff(locationId,device);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            String notifyMsg = "fetching Ad Stuff meet error: locationId=["+locationId+"], device=["+device+"] ";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        logger.info("finish to fetch ad Stuff. and cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }

    @RequestMapping("/shop")
    @ResponseBody
    public Map<String, Object> fetchAdShop(HttpServletRequest request,@RequestParam(value = "locationId", required = true) int locationId, @RequestParam(value = "device", required = true) int device) {
        logger.info("fetching ad shop locationId=["+locationId+"], device=["+device+"]  ...");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            //            List<AdBanner> data = adFacade.fetchAdShop(moduleId,locationId);
            //            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            logger.error("fetching ad shop ", ex);
            String notifyMsg = "fetching Ad Shop meet error: locationId=["+locationId+"], device=["+device+"] ";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        logger.info("finish to fetch ad shop. and cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }

    @RequestMapping("/ju")
    @ResponseBody
    public Map<String, Object> fetchAdJu(HttpServletRequest request,@RequestParam(value = "locationId", required = true) int locationId, @RequestParam(value = "device", required = true) int device) {
        logger.info("fetching ad ju locationId=["+locationId+"], device=["+device+"]  ...");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            List<AdJu> data = adFacade.fetchAdJu(locationId,device);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            String notifyMsg = "fetching Ad Ju meet error: locationId=["+locationId+"], device=["+device+"] ";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg, ex);
        }
        logger.info("finish to fetch ad Ju. and cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }

    /**
     * Banner详细页面接口描述
     * 
     * @param request
     * @param bannerId
     * @param device
     * @return
     */
    @RequestMapping("/banner/detail")
    @ResponseBody
    public Map<String, Object> detail(HttpServletRequest request, @RequestParam(value = "bannerId", required = true) Integer bannerId, @RequestParam(value = "device", required = true) int device) {
        logger.info("/banner/detail   bannerId=[" + bannerId + "], device=[" + device + "]  ...");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            BannerStuffConfDto data = adFacade.queryBannerStuffConf(bannerId, device);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            String notifyMsg = "/banner/detail   bannerId=[" + bannerId + "], device=[" + device + "]  ...";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        logger.info("finish to fetch ad Ju. and cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }

    /**
     * Banner限时购详细页面接口描述
     *  根据下架时间选择商品  banner_id=#{bannerId} and  off_time>=#{nowTime} order by on_time asc
     * @param request
     * @param bannerId
     * @param device
     * @return
     */
    @RequestMapping("/banner/flashSaleDetail")
    @ResponseBody
    public Map<String, Object> flashSaleDetail(HttpServletRequest request, @RequestParam(value = "bannerId", required = true) Integer bannerId, @RequestParam(value = "device", required = true) int device) {
        logger.info("/banner/flashSaleDetail   bannerId=[" + bannerId + "], device=[" + device + "]  ...");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            BannerSaleStuffConfDto data = adFacade.queryBannerSaleStuffConf(bannerId, device);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            String notifyMsg = "/banner/flashSaleDetail   bannerId=[" + bannerId + "], device=[" + device + "]  ...";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        logger.info("finish to /banner/flashSaleDetail. and cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }
}
