package com.qbao.aisr.app.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.dto.TopPaiImgDto;
import com.qbao.aisr.app.dto.UserStuffPaiDto;
import com.qbao.aisr.app.model.UserStuffPai;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.model.TopPaiImg;
import com.qbao.aisr.app.service.pai.ITopPaiImgService;
import com.qbao.aisr.app.web.controller.base.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-28 14:36:05 +0800 (Tue, 28 Mar 2017) $$
 * $$LastChangedRevision: 562 $$
 * $$LastChangedBy: liaijun $$
 */
@Controller
@RequestMapping("/stuff/topPai/")
public class TopPaiController extends BaseController {
    @Autowired
    private ITopPaiImgService paiService;

    Logger logger = Logger.getLogger(TopPaiController.class);

    /**
     * 大家都在拍的接口
     * 
     * @param
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> queryTopPai(HttpServletRequest request,@RequestParam(value = "page", required = true) int page, @RequestParam(value = "pageSize", required = true) int pageSize) {
        logger.info("queryTopPai start >>> page=" + page + " pageSize=" + pageSize);
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try{
            List<TopPaiImgDto> list = paiService.findTopPaiList(page, pageSize);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", list);
            response.put("total", paiService.countTopPaiImg());
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);

            String urlInfo = "有好货接口异常:/stuff/topPai/list?page=" + page + " pageSize=" + pageSize;
            final String subject = "有好货接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("queryTopPai end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }

    /**
     * 我的拍的接口
     *
     * @param
     * @return
     */
    @RequestMapping("/historyPai")
    @ResponseBody
    public Map<String, Object> queryTopUserPai(HttpServletRequest request, @RequestParam(value = "page", required = true) int page, @RequestParam(value = "pageSize", required = true) int pageSize) {
        logger.info("queryTopUserPai start >>> page=" + page + " pageSize=" + pageSize);
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            List<UserStuffPaiDto> list = paiService.findUserPaiList(userId, page, pageSize);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", list);
            response.put("total", paiService.countUserPai(userId));
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);

            String urlInfo = "有好货接口异常:/stuff/topPai/historyPai?page=" + page + " pageSize=" + pageSize;
            final String subject = "有好货接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("queryTopUserPai end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }
}
