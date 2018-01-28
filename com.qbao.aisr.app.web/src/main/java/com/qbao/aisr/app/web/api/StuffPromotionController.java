package com.qbao.aisr.app.web.api;

import java.util.HashMap;
import java.util.Map;

import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.stuff.alarm.mail.impl.ErrorMailAlarmService;
import com.qbao.aisr.stuff.alarm.phone.impl.ErrorPhoneAlaramService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.service.facade.StuffFacade;
import com.qbao.aisr.app.web.controller.base.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-30 16:51:33 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 595 $$
 * $$LastChangedBy: liaijun $$
 */
@Controller
@RequestMapping("/stuff/goods/")
public class StuffPromotionController extends BaseController {
    private Logger logger = Logger.getLogger(StuffPromotionController.class);
    @Autowired
    private StuffFacade stuffFacade;


    /**
     * 根据stuffId商品id返回商品信息
     * 
     * @param
     * @return
     */
    @RequestMapping("/one")
    @ResponseBody
    public Map<String, Object> queryGoods(HttpServletRequest request, @RequestParam("stuffId") Long stuffId, @RequestParam("device") int device) {
        logger.info("queryGoods start >>> stuffId=" + stuffId );
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        try {
            StuffPromotionDto dto = stuffFacade.findStuffPromotionInfo(stuffId, device);
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("data", dto);
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货接口异常:/stuff/goods/ones?tuffId=" + stuffId;
            String subject = "有好货接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("queryGoods end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
}
