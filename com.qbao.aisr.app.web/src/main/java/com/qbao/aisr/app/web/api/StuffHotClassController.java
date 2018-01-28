package com.qbao.aisr.app.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.dto.StuffHotGoodsDto;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.dto.StuffHotClassDto;
import com.qbao.aisr.app.service.facade.HotFacade;
import com.qbao.aisr.app.web.controller.base.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liaijun
 * @createTime 17/3/10 下午5:37
 * $$LastChangedDate: 2017-03-30 16:11:14 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 588 $$
 * $$LastChangedBy: liaijun $$
 */
@Controller
@RequestMapping("/stuff/hot/")
public class StuffHotClassController extends BaseController {
    @Autowired
    private HotFacade hotFacade;

    private Logger logger = Logger.getLogger(StuffHotClassController.class);

    /**
     * 获取热卖好货所有类目
     *
     * @param
     * @return
     */
    @RequestMapping("/goodsClass")
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request) {
        logger.info("list start >>> ");
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            List<StuffHotClassDto> list = hotFacade.queryHotClassCat();
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", list);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);

            String urlInfo = "有好货接口异常:/stuff/hot/goodsClass";
            final String subject = "有好货接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("list end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }

    /**
     * 获取热卖好货类目下的商品
     *
     * @param
     * @return
     */
    @RequestMapping("/goodsList")
    @ResponseBody
    public Map<String, Object> goodsList(HttpServletRequest request, @RequestParam(value = "cId", required = true) Long cId, @RequestParam(value = "page", required = true) int page, @RequestParam(value = "size", required = true) int size, @RequestParam(value = "device", required = true) int device) {
        Long userId = getCurrentUserId(request);
        logger.info("goodsList start >>>/stuff/hot/goodsList?cId=" + cId + "&page=" + page + "&size=" + size + " 哪种设备device=" + device);
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("userId", userId);
        try {
            Page<StuffHotGoodsDto> list = hotFacade.queryHotClassById(cId, page, size, device);
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

            String urlInfo = "有好货接口异常:/stuff/hot/goodsList?cId=" + cId + "&page=" + page + "&size=" + size + " 哪种设备device=" + device;
            final String subject = "有好货接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("goodsList end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }
}
