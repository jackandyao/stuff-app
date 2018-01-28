package com.qbao.aisr.app.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.dto.QbaozyActivityDto;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.service.facade.QbzyActivityFacade;
import com.qbao.aisr.app.web.controller.base.BaseController;

/**
 * @author zhangjun
 * @createTime 17/5/24 上午10:22
 * $$LastChangedDate: 2017-03-30 16:51:33 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 595 $$
 * $$LastChangedBy: zhangjun $$
 */
@Controller
@RequestMapping("/stuff/special/")
public class StuffSpecialController extends BaseController {
    private Logger logger = Logger.getLogger(StuffSpecialController.class);
    @Autowired
    private QbzyActivityFacade qbzyActivityFacade;


    /**
     * 专场商品分类接口
     * @param request
     * @param specialId
     * @return
     */
    @RequestMapping("/goodsClass")
    @ResponseBody
    public Map<String, Object> goodsClass(HttpServletRequest request, @RequestParam("specialId") Long specialId){
    	logger.info("appeal goodsClass start >>> specialId " + specialId);
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        
        try {
        	QbaozyActivityDto activityDto = qbzyActivityFacade.findActivityById(specialId);
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("data", activityDto.getClassesList());
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货专场商品分类接口异常:/stuff/special/goodsClass.do?specialId=" + specialId;
            String subject = "有好货专场商品分类接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("goodsClass end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
    
    /**
     * 专场分类商品列表接口
     * @param request
     * @param cId
     * @param device
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/goodsList")
    @ResponseBody
    public Map<String, Object> goodsList(HttpServletRequest request, @RequestParam("cId")Integer cId, @RequestParam("device") Integer device
    		, @RequestParam(value="page",required=false,defaultValue="1") int page, @RequestParam(value="size",required=false,defaultValue="8") int size){
    	if(page<1)page=1;
    	logger.info("appeal upload start >>> ");
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        try {
            Page<StuffPromotionDto> data = qbzyActivityFacade.goodsList(userId, cId, device, page, size);
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("data", data.getItems());
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货专场分类商品列表接口异常:/stuff/special/goodsList.do?cId="+cId+"&device="+device+"&page="+page+"&size="+size;
            String subject = "有好货专场分类商品列表接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("goodsList end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
    
    
    
}
