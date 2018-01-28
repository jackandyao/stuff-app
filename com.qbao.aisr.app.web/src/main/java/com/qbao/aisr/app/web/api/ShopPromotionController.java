package com.qbao.aisr.app.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.dto.ShopPromotionDto;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.model.ShopPromotion;
import com.qbao.aisr.app.service.shop.IShopPromotionService;
import com.qbao.aisr.app.web.controller.base.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-30 17:27:59 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 598 $$
 * $$LastChangedBy: liaijun $$
 */
@Controller
@RequestMapping("/stuff/shop/")
public class ShopPromotionController extends BaseController {
    private Logger logger = Logger.getLogger(ShopPromotionController.class);
    @Autowired
    private IShopPromotionService shopPromotionService;

    /**
     * 发现好店首页店铺接口
     * 
     * @param
     * @return
     */
    @RequestMapping("/getShop")
    @ResponseBody
    public Map<String, Object> queryShop(HttpServletRequest request, @RequestParam(value = "shopSize", required = true) int shopSize, @RequestParam(value = "stuffSize", required = true) int stuffSize, @RequestParam(value = "device", required = true) int device) {
        logger.info("queryShop start >>> shopSize=" + shopSize + "stuffSize=" + stuffSize);
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try{
            List<ShopPromotionDto> list = shopPromotionService.findShopPromotion(shopSize, stuffSize, device);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", list);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);

            String urlInfo = "有好货接口异常:/stuff/shop/getShop?shopSize=" + shopSize + "stuffSize=" + stuffSize;
            final String subject = "有好货接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("queryShop end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }
}
