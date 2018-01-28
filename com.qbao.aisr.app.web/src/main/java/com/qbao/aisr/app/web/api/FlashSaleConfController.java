package com.qbao.aisr.app.web.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.qbao.aisr.app.dto.StuffPromotionDto;
import org.codehaus.plexus.util.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.dto.FlashSaleConfDto;
import com.qbao.aisr.app.service.flash.IFlashSaleConfService;
import com.qbao.aisr.app.web.controller.base.BaseController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liaijun
 * @createTime 17/7/10 下午2:53
 * $$LastChangedDate: 2017-07-10 18:30:55 +0800 (Mon, 10 Jul 2017) $$
 * $$LastChangedRevision: 1413 $$
 * $$LastChangedBy: liaijun $$
 */
@Controller
@RequestMapping("/stuff/flash/")
public class FlashSaleConfController  extends BaseController {
    Logger logger = org.slf4j.LoggerFactory.getLogger(FlashSaleConfController.class);
    @Autowired
    private IFlashSaleConfService flashSaleConfService;

    @ResponseBody
    @RequestMapping("/item")
    public Map<String,Object> goodsClasses(HttpServletRequest request){
        logger.info("item start >>>>>>>>>>>>>>>>>>");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            List<FlashSaleConfDto> list= flashSaleConfService.findFlashSaleConf();
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", list);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data",null);
            response.put("success", true);
            response.put("message", "系统错误！");
        }
        logger.info("item end >>> count time:" + (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping("/isStart")
    public Map<String,Object> isStart(HttpServletRequest request,@RequestParam(value = "id") Long id){
        logger.info("isStart start>>>>>>>>>>>>>>>>>>>>");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            Date nowTime=new Date();
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data",  flashSaleConfService.findFlashSaleConfById(id,nowTime));
            response.put("success", true);
            response.put("sysTime",nowTime);
            response.put("message", "Ok");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data",null);
            response.put("success", true);
            response.put("message", "系统错误！");
        }
        logger.info("isStart end >>> count time:" + (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping("/detail")
    public Map<String,Object> detail(HttpServletRequest request,@RequestParam(value = "id") Long id,
            @RequestParam("device") Integer device,
            @RequestParam(value = "page",required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        logger.info("detail start>>>>>>>>>>>>>>>>>>>>");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            List<StuffPromotionDto> list=flashSaleConfService.findFlashSaleStuff(id,page,size,device);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data",list);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data",null);
            response.put("success", true);
            response.put("message", "系统错误！");
        }
        logger.info("detail end >>> count time:" + (System.currentTimeMillis() - start));
        return response;
    }
}
