package com.qbao.aisr.app.web.api;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.dto.StuffHotGoodsDto;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.dto.TopSearchStuffComplexDto;
import com.qbao.aisr.app.service.facade.JuFacade;
import com.qbao.aisr.app.service.facade.TopSearchFacade;
import com.qbao.aisr.app.web.controller.base.BaseController;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-30 17:17:11 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 596 $$
 * $$LastChangedBy: wangping $$
 */
@Controller
@RequestMapping("/stuff/ju/")
public class JuPromotionController extends BaseController {
    Logger logger = Logger.getLogger(JuPromotionController.class);

    @Autowired
    private JuFacade juFacade;
    @Autowired
    private TopSearchFacade topSearchFacade;

    /**
     * * 点击六个类目返回商品(口腔护理专场,母婴联合狂欢,健康好奶等类目)
     * 对应页面:http://wiki.qbao.com/pages/viewpage.action?pageId=18160745
     * @param
     * @return
     */
    @RequestMapping("/catPromotion")
    @ResponseBody
    public Map<String, Object> catPromotion(HttpServletRequest request, @RequestParam(value = "catId", required = true) Long catId, @RequestParam(value = "page", required = true) int page, @RequestParam(value = "size", required = true) int size, @RequestParam(value = "device", required = true) int device) {
        logger.info("catPromotion  start>>>> catId=[" + catId + "], page=[" + page + "], size=[" + size + "]  device=[" + device+"]");
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try{
            Page<StuffHotGoodsDto> list = juFacade.findStuffPromotionByCatId(catId, page, size, device);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", list.getItems());
            response.put("total", list.getTotalNumber());
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            String notifyMsg = "fetching  cat promotions meet error:  catId =[" + catId + "],userId =[" + userId + "],page=[" + page + "], size=[" +size+"],device=[" + device+"]";
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            NotifierUtil.notifyByPhone(notifyMsg);
            logger.error(notifyMsg,ex);
        }
        logger.info("catPromotion end>>>>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }
    /**
     * 热门搜索top商品
     *
     * @param
     * @return
     */
    @RequestMapping("/stuffList")
    @ResponseBody
    public Map<String, Object> topSearchStuffComplex(HttpServletRequest request,@RequestParam(value = "page", required = true) Integer page, @RequestParam(value = "size", required = true) Integer size, @RequestParam(value = "device", required = true) int device) {
        logger.info("topSearchStuffComplex start >>> size=" + size+ "], page=[" + page + "], size=[" + size + "]  device=[" + device+"]");
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            List<TopSearchStuffComplexDto> data = topSearchFacade.topSearchStuffComplex(page,size,device);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            String notifyMsg = "fetching topSearchStuffComplex meet error: userId =[" + userId + "],page=[" + page + "], size=[" +size+"],device=[" + device+"]";
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            NotifierUtil.notifyByPhone(notifyMsg);
            logger.error(notifyMsg,ex);
        }
        logger.info("topSearchStuffComplex end >>> count time: : " + (System.currentTimeMillis() - startTime) + ".ms");
        return response;
    }

    /**
     * 云好货接口
     * 
     * @param
     * @return
     */
    @RequestMapping("/cloud")
    @ResponseBody
    public Map<String, Object> cloudStuffPromotion(HttpServletRequest request, @RequestParam(value = "page", required = true) int page, @RequestParam(value = "size", required = true) int size, @RequestParam(value = "device", required = true) int device) {
        Long userId = getCurrentUserId(request);
        logger.info("cloudStuffPromotion  start>>>> userId=[" + userId + "], page=[" + page + "], size=[" + size + "]  device=[" + device+"]");
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("userId", userId);
        try {
            Page<StuffPromotionDto> list = juFacade.findStuffPromotionByUserId(userId, page, size,device);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", list.getItems());
            response.put("total", list.getTotalNumber());
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            String notifyMsg = "fetching  cloudStuffPromotion meet error: userId =[" + userId + "],page=[" + page + "], size=[" +size+"],device=[" + device+"]";
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            NotifierUtil.notifyByPhone(notifyMsg);
            logger.error(notifyMsg,ex);
        }
        logger.info("cloudStuffPromotion end>>>>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }
}
