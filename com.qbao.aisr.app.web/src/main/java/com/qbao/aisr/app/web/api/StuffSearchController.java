package com.qbao.aisr.app.web.api;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.exception.NoValidDataException;
import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.common.util.ExceptionAlarmUtils;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.dto.StuffRecommendDto;
import com.qbao.aisr.app.dto.TopSearchKeyDto;
import com.qbao.aisr.app.dto.search.ZnStuffDto;
import com.qbao.aisr.app.dto.search.ZwStuffDto;
import com.qbao.aisr.app.model.search.Suggest;
import com.qbao.aisr.app.service.facade.SearchFacade;
import com.qbao.aisr.app.service.facade.StuffRecommendFacade;
import com.qbao.aisr.app.service.facade.TopSearchFacade;
import com.qbao.aisr.app.service.facade.UserDislikeStuffFacade;
import com.qbao.aisr.app.web.controller.base.BaseController;

/**
 * @author shuaizhihu
 * @createTime 2017/3/11 14:27
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@RestController
@RequestMapping("/stuff/search")
public class StuffSearchController extends BaseController {
    public Logger logger = Logger.getLogger(getClass());

    @Autowired
    SearchFacade searchFacade;
    @Autowired
    StuffRecommendFacade stuffRecommendFacade;
    @Autowired
    UserDislikeStuffFacade userDislikeStuffFacade;
    @Autowired
    private TopSearchFacade topSearchFacade;

    @RequestMapping("search_zn")
    public Map<String, Object> searchZn(HttpServletRequest request,
            @RequestParam(value = "kw", required = false, defaultValue = "") String kw,
            @RequestParam(value = "sort", required = false, defaultValue = "") String sort,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int pageSize) {

        long userId = this.getCurrentUserId(request);
        logger.info("searchZn start >>> kw=" + kw + " userId=" + userId + " sort=" + sort + " page=" + page + " size=" + pageSize);
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("userId", userId);
        try {
            Page<ZnStuffDto> pageResult = searchFacade.searchZn(userId, kw, sort, page, pageSize);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", pageResult.getItems());
            response.put("total", pageResult.getTotalNumber());
            response.put("success", true);
            response.put("message", "Ok");
        } catch (UnsupportedEncodingException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "url不合法");
        } catch (HttpProcessException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "请求搜索服务出错！");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "系统错误！");
        }
        logger.info("searchZn end >>> count time:" + (System.currentTimeMillis() - start));
        return response;
    }

    @RequestMapping("search_zw")
    public Map<String, Object> searchZw(
            HttpServletRequest request,
            @RequestParam(value = "kw", required = false, defaultValue = "") String kw,
            @RequestParam(value = "sort", required = false, defaultValue = "sort:promotion_rate:desc") String sort, // 默认按佣金高低排序
            @RequestParam(value = "source", required = false, defaultValue = "") String source,
            @RequestParam(value = "activities", required = false, defaultValue = "") String activities,
            @RequestParam(value = "coupon", required = false, defaultValue = "0") int coupon,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "device", required = true) int device) {
        long userId = this.getCurrentUserId(request);
        logger.info("searchZw start >>> kw=" + kw + " userId=" + userId + " sort=" + sort + "&source=" + source + "&page=" + page + " size=" + pageSize+"&device="+device+"&activities="+activities+"&coupon="+coupon);
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("userId", userId);
        try {
            Page<ZwStuffDto> pageResult = searchFacade.searchZw(userId, kw, source, sort, page, pageSize,device, activities, coupon);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", pageResult.getItems());
            response.put("total", pageResult.getTotalNumber());
            response.put("success", true);
            response.put("message", "Ok");
        } catch (UnsupportedEncodingException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "url不合法");
        } catch (HttpProcessException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "请求搜索服务出错！");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "系统错误！");
        }
        logger.info("searchZw end >>> count time:" + (System.currentTimeMillis() - start));
        return response;
    }

    @RequestMapping("similar")
    public Map<String, Object> similar(
            HttpServletRequest request,
            @RequestParam(value = "stuffId", required = false, defaultValue = "") long stuffId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "device", required = true) int device) {
        long userId = this.getCurrentUserId(request);
        logger.info("similar start >>> stuffId=" + stuffId + " userId=" + userId + "&page=" + page + " size=" + pageSize);
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("userId", userId);
        try {
            int newSize = pageSize + 1;
            Page<ZwStuffDto> pageResult = searchFacade.searchZwSim(userId, stuffId, page, newSize,device);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", pageResult.getItems());
            response.put("total", pageResult.getTotalNumber());
            response.put("success", true);
            response.put("message", "Ok");
        } catch (UnsupportedEncodingException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "url不合法");
        } catch (HttpProcessException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "请求搜索服务出错！");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "系统错误！");
        }
        logger.info("similar end >>> count time:" + (System.currentTimeMillis() - start));
        return response;
    }

    @RequestMapping("findStuffByStuffId")
    public Map<String, Object> findStuffByStuffId(HttpServletRequest request,
            @RequestParam(value = "stuffId", required = true, defaultValue = "") long stuffId) {
        logger.info("findStuffByStuffId start >>> stuffId=" + stuffId);
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            StuffPromotionDto stuffPromotionDto = searchFacade.findStuffByStuffId(stuffId);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", stuffPromotionDto);
            response.put("total", 1);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (NoValidDataException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", e.getMessage());
            //异常短消息报警
            ExceptionAlarmUtils.sendAlarm("/stuff/search/findStuffByStuffId", "stuffId:" + stuffId, "NoValidDataException", e.getMessage());
        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "系统错误！");
            //异常短消息报警
            ExceptionAlarmUtils.sendAlarm("/stuff/search/findStuffByStuffId", "stuffId:" + stuffId, "Exception", e.getMessage());
        }
        logger.info("findStuffByStuffId end >>> count time:" + (System.currentTimeMillis() - start));
        return response;
    }

    @RequestMapping("suggest")
    public Map<String, Object> suggest(HttpServletRequest request,
            @RequestParam(value = "kw", required = false, defaultValue = "") String kw,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int pageSize) {
        logger.info("suggest start >>> kw=" + kw + "&page=" + page + " size=" + pageSize);
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            Page<Suggest> pageResult = searchFacade.suggest(kw, page, pageSize);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", pageResult.getItems());
            response.put("total", pageResult.getTotalNumber());
            response.put("success", true);
            response.put("message", "Ok");
        } catch (UnsupportedEncodingException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "url不合法");
        } catch (HttpProcessException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "请求搜索服务出错！");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data", null);
            response.put("total", 0);
            response.put("success", true);
            response.put("message", "系统错误！");
        }
        logger.info("suggest end >>> count time:" + (System.currentTimeMillis() - start));
        return response;

    }

    /**
     * 首页商品推荐
     *
     * @param
     * @return
     */
    @RequestMapping("/recommendhp")
    @ResponseBody
    public Map<String, Object> stuffRecommendHomePage( HttpServletRequest request, @RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size, @RequestParam(value = "device", required = true) int device) {
        long userId = getCurrentUserId(request);
        logger.info("stuffRecommendHomePage start >>> userId=" + userId + ", page=" + page + ", size=" + size + ", device=" + device);
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            List<StuffRecommendDto> data = stuffRecommendFacade.stuffRecommendHomePage(userId, page, size, device);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            logger.error("stuffRecommend ", ex);
            //异常短消息报警
            ExceptionAlarmUtils.sendAlarm("/stuff/search/recommendhp", "page:" + page + ",size:" + size, "Exception", ex.getMessage());
        }
        logger.info("stuffRecommendHomePage end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }

    /**
     * 商品推荐
     *
     * @param
     * @return
     */
    @RequestMapping("/recommend")
    @ResponseBody
    public Map<String, Object> stuffRecommend(HttpServletRequest request, @RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size,@RequestParam(value = "device", required = true) int device) {
        long userId = getCurrentUserId(request);
        //long userId = 5434613l;
                logger.info("stuffRecommend start >>> userId="  + userId + ", page=" + page + ", size=" + size + ", device=" + device);
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            List<StuffRecommendDto> data = stuffRecommendFacade.stuffRecommend(userId, page, size,device);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            logger.error("stuffRecommend ", ex);
            //异常短消息报警
            ExceptionAlarmUtils.sendAlarm("/stuff/search/recommend", "page:" + page + ",size:" + size, "Exception", ex.getMessage());
        }
        logger.info("stuffRecommend end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }

    /**
     * 热门搜索top关键字
     *
     * @param
     * @return
     */
    @RequestMapping("/keyList")
    @ResponseBody
    public Map<String, Object> topSearchKey(HttpServletRequest request, @RequestParam(value = "limit", required = true) int limit) {
        logger.info("topSearchKey start >>> limit=" + limit);
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            List<TopSearchKeyDto> data = topSearchFacade.topSearchKey(limit);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            logger.error("topSearchKey ", ex);
            //异常短消息报警
            ExceptionAlarmUtils.sendAlarm("/stuff/search/keyList", "limit:" + limit, "Exception", ex.getMessage());
        }
        logger.info("topSearchKey end >>> count time: : " + (System.currentTimeMillis() - startTime) + ".ms");
        return response;
    }

    /**
     * 用户不喜欢商品
     *
     * @param
     * @return
     */
    @RequestMapping("/dislike")
    @ResponseBody
    public Map<String, Object> updateUserDislikeStuff(HttpServletRequest request, @RequestParam(value = "stuffId", required = true) Long stuffId) {
        logger.info("updateUserDislikeStuff start >>> stuffId=" + stuffId);
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            Boolean rs = userDislikeStuffFacade.updateUserDislikeStuff(userId, stuffId);
            if (rs) {
                response.put("success", true);
                response.put("message", "Ok");
            } else {
                response.put("success", false);
                response.put("message", "系统异常");
            }

        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            logger.error("updateUserDislikeStuff ", ex);
            //异常短消息报警
            ExceptionAlarmUtils.sendAlarm("/stuff/search/updateUserDislikeStuff", "stuffId:" + stuffId, "Exception", ex.getMessage());
        }
        logger.info("updateUserDislikeStuff end >>> count time: : " + (System.currentTimeMillis() - startTime) + ".ms");
        return response;
    }
    
}
