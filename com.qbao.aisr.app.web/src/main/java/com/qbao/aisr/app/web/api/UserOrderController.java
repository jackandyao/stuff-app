package com.qbao.aisr.app.web.api;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.dto.UserOrderDto;
import com.qbao.aisr.app.dto.UserOrderInfoDto;
import com.qbao.aisr.app.service.facade.UserOrderFacade;
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
 * @createTime 17/3/7 下午2:27
 * $$LastChangedDate: 2017-07-17 10:20:38 +0800 (Mon, 17 Jul 2017) $$
 * $$LastChangedRevision: 1435 $$
 * $$LastChangedBy: wangping $$
 */
@Controller
@RequestMapping("/stuff/order/")
public class UserOrderController extends BaseController {
    private Logger logger= Logger.getLogger(UserOrderController.class);

    @Autowired
    private UserOrderFacade userOrderFacade;

    /**
     * 我的订单(记录订单)
     *
     * @param
     * @return
     */
    @RequestMapping("/userOrder")
    @ResponseBody
    public Map<String, Object> userOrder(HttpServletRequest request,@RequestParam(value = "id", required = true) Long id) {
        logger.info("userOrder start >>>表user_stuff_promotion的 id=" + id);
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        long startTime = System.currentTimeMillis();
        try {

            UserOrderDto  dto = userOrderFacade.findUserStuffPromotionById(id);
            json.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            json.put("data", dto);
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货接口异常:/stuff/order/userOrder?id=" + id;
            final String subject = "有好货接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("userOrder end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }


    /**
     * 包括以返宝券和未返宝券
     * status状态:不填表示全部  0: 未返券,1已返券
     * @param
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request, @RequestParam(value = "page", required = true) int page, @RequestParam(value = "size", required = true) int size, @RequestParam(value = "status", required = false) Integer status, @RequestParam(value = "device", required = true) int device) {
        Long userId = getCurrentUserId(request);
        logger.info("list start >>> userId=" + userId);
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("userId", userId);
        long startTime = System.currentTimeMillis();
        try {

            List<UserOrderInfoDto> orderList = userOrderFacade.findUserStuffPromotionByUserId(userId, page, size, status, device);
            json.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            json.put("data", orderList);
            Map<String,Long> orderCount=userOrderFacade.countUserStuffPromotionByUserIdStatus(userId);
            Long allSum = orderCount.get("allSum");
            Long unReturnSum = orderCount.get("unReturnSum");
            json.put("totalRebateValue", allSum == null ? 0 : allSum);
            json.put("unRebateValue", unReturnSum == null ? 0 : unReturnSum);
            json.put("size", orderCount.get("all"));
            json.put("unSize", orderCount.get("UNRETURN"));
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货接口异常:/stuff/order/list?userId=" + userId + "&page=" + page + "&size=" + size + "&status=" + status;
            final String subject = "有好货接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("userOrder end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }


    /**
     * 订单逻辑删除
     * @param id
     */
    @RequestMapping("/delOrder")
    @ResponseBody
    public Map<String, Object>  modifyUserStuffPromo( HttpServletRequest request,@RequestParam(value = "id", required = true) Long id) {
        logger.info("list start >>> id=" + id);
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        long startTime = System.currentTimeMillis();
        try {
            userOrderFacade.modifyUserStuffPromo(id);
            json.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            json.put("success", true);
            json.put("message", "Ok");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货接口异常:/stuff/order/delOrde?id=" + id;
            final String subject = "有好货接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("userOrder end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }

    /**
     * 新增订单
     *
     */
    @RequestMapping("/addOrder")
    @ResponseBody
    public Map<String, Object> addOrder(HttpServletRequest request, @RequestParam(value = "orderId", required = true) String orderId, @RequestParam(value = "source", required = true) String source, @RequestParam(value = "device", required = false) Integer device
    		, @RequestParam(value = "isPay", required = false) Integer isPay, @RequestParam(value = "isPromotion", required = false) Integer isPromotion, @RequestParam(value = "channel", required = false) String channel) {
        Long userId = getCurrentUserId(request);
        logger.info("addOrder start >>> userId=" + userId + " orderId=" + orderId + " source=" + source + " device=" + device + " isPay=" + isPay + " isPromotion=" + isPromotion + " channel=" + channel);
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("userId", userId);
        long startTime = System.currentTimeMillis();
        try {
            userOrderFacade.saveUserStuffPromo(userId, orderId, source, device, isPay, isPromotion, channel);
            json.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            json.put("success", true);
            json.put("message", "Ok");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货接口异常:/stuff/order/addOrder?userId=" + userId + " orderId=" + orderId + " source=" + source;
            final String subject = "有好货接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("addOrder end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }

    /**
     * oneday 有好货 订单持久化
     *
     */
    @RequestMapping("/persistOrder")
    @ResponseBody
    public Map<String, Object> persistOrder(HttpServletRequest request,@RequestParam(value = "userId", required = true) Long userId, @RequestParam(value = "orderId", required = true) String orderId, @RequestParam(value = "source", required = true) String source, @RequestParam(value = "device", required = false) Integer device
            , @RequestParam(value = "isPay", required = false) Integer isPay, @RequestParam(value = "isPromotion", required = false) Integer isPromotion, @RequestParam(value = "channel", required = false) String channel) {
        logger.info("addOrder start >>> userId=" + userId + " orderId=" + orderId + " source=" + source + " device=" + device + " isPay=" + isPay + " isPromotion=" + isPromotion + " channel=" + channel);
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("userId", userId);
        long startTime = System.currentTimeMillis();
        try {
            userOrderFacade.saveUserStuffPromo(userId, orderId, source, device, isPay, isPromotion, channel);
            json.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            json.put("success", true);
            json.put("message", "Ok");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货接口异常:/stuff/order/persistOrder?userId=" + userId + " orderId=" + orderId + " source=" + source;
            final String subject = "有好货接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("addOrder end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }

    /**
     * 用户返券数
     *
     */
    @RequestMapping("/userRebate")
    @ResponseBody
    public Map<String, Object> userRebate(HttpServletRequest request, @RequestParam(value = "orderId", required = true) String orderId, @RequestParam(value = "source", required = true) String source) {
        Long userId = getCurrentUserId(request);
        logger.info("userRebate start >>> /stuff/order/userRebate.do?userId=" + userId + "&orderId=" + orderId + "&source=" + source);
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("userId", userId);
        long startTime = System.currentTimeMillis();
        try {
            Long rebateValue = userOrderFacade.queryUserRebateValue(userId, orderId, source);
            json.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            json.put("success", true);
            json.put("data", rebateValue);
            json.put("message", "Ok");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货接口异常:/stuff/order/userRebate.do?userId=" + userId + "&orderId=" + orderId + "&source=" + source;
            final String subject = "有好货接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("userRebate end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
}
