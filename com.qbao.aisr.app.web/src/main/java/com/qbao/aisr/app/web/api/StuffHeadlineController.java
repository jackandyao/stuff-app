package com.qbao.aisr.app.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qbao.aisr.app.common.util.NotifierUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.model.StuffHeadline;
import com.qbao.aisr.app.service.ju.IStuffHeadlineService;
import com.qbao.aisr.app.web.controller.base.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liaijun
 * @createTime 17/3/6 上午11:51
 * $$LastChangedDate: 2017-03-27 17:53:38 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 558 $$
 * $$LastChangedBy: liaijun $$
 */
@Controller
@RequestMapping("/stuff/headline/")
public class StuffHeadlineController  extends BaseController {
    private Logger logger= Logger.getLogger(StuffHeadlineController.class);
    @Autowired
    private IStuffHeadlineService stuffHeadlineService;

    /**
     *有好货头条接口
     *
     * @param
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> queryStuffHeadline(HttpServletRequest request,@RequestParam(value = "size", defaultValue = "5") int size) {
        logger.info("queryStuffHeadline start >>> size="+size);
        long startTime=System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try{
            List<StuffHeadline> list = stuffHeadlineService.findStuffPromotionByCatId(size);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", list);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);

            String urlInfo = "有好货接口异常:/stuff/headline/list?size=" + size;
            final String subject = "有好货接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("queryStuffHeadline end >>>count time="+(System.currentTimeMillis()-startTime));
        return response;
    }
}
