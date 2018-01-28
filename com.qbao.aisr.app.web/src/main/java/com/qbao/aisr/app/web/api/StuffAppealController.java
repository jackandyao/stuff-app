package com.qbao.aisr.app.web.api;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.dto.StuffAppealDetailDto;
import com.qbao.aisr.app.dto.StuffAppealDto;
import com.qbao.aisr.app.service.facade.StuffAppealFacade;
import com.qbao.aisr.app.web.controller.base.BaseController;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjun
 * @createTime 17/5/24 上午10:22
 * $$LastChangedDate: 2017-03-30 16:51:33 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 595 $$
 * $$LastChangedBy: zhangjun $$
 */
@Controller
@RequestMapping("/stuff/appeal/")
public class StuffAppealController extends BaseController {
    private Logger logger = Logger.getLogger(StuffAppealController.class);
    @Autowired
    private StuffAppealFacade stuffAppealFacade;


    /**
     * 是否可以提交申诉接口
     * @param request
     * @param appealId
     * @param userId
     * @return
     */
    @RequestMapping("/right")
    @ResponseBody
    public Map<String, Object> right(HttpServletRequest request){
    	logger.info("appeal right start >>> ");
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        
        try {
            Boolean data = stuffAppealFacade.right(userId);
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("data", data);
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货申诉提交权限接口异常:/stuff/appeal/right.do?userId=" + userId;
            String subject = "有好货申诉提交权限接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("appeal right end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
    
    /**
     * 
     * @param request
     * @param userId
     * @param image
     * @param imageType
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(HttpServletRequest request, @RequestParam("image")MultipartFile image, @RequestParam("imageType") String imageType){
    	logger.info("appeal upload start >>> ");
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        try {
            Map<String,String> data = stuffAppealFacade.upload(userId, image.getBytes(), imageType);
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("data", data);
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货图片上传接口异常:/stuff/appeal/upload.do?userId="+userId+"&file=";
            String subject = "有好货图片上传接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("appeal upload end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
    
    @RequestMapping("/submit")
    @ResponseBody
    public Map<String, Object> submit(HttpServletRequest request, @RequestParam("orderId") String orderId
    		, @RequestParam("source") String source, @RequestParam("device") int device, @RequestParam("phoneBrand") String phoneBrand, @RequestParam("phoneType") String phoneType
    		, @RequestParam("content") String content, @RequestParam("reason") String reason, @RequestParam("imgUrl") String imgUrl
    		, @RequestParam("qq") String qq, @RequestParam("phone") String phone){
    	logger.info("appeal submit start >>> orderId=" + orderId+", source=" + source
    			+", device=" + device+", phoneBrand=" + phoneBrand+", phoneType=" + phoneType+", content=" + content+", reason=" + reason
    			+", imgUrl="+ imgUrl+", qq=" + qq+", phone=" + phone);
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        
        try {
            stuffAppealFacade.submit(userId, orderId,source,device,phoneBrand,phoneType,content,reason,imgUrl,qq,phone);
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货申诉提交接口异常:/stuff/appeal/upload.do?userId="+userId+"&file=";
            String subject = "有好货申诉提交接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("appeal submit end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
   
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request){
    	logger.info("appeal list start >>> ");
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        
        try {
            List<StuffAppealDto> data = stuffAppealFacade.list(userId);
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("data", data);
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货申诉/售后接口异常:/stuff/appeal/list.do?userId=" + userId;
            String subject = "有好货申诉/售后接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("appeal list end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
    
    @RequestMapping("/cancel")
    @ResponseBody
    public Map<String, Object> cancel(HttpServletRequest request, @RequestParam("appealId") long appealId){
    	logger.info("appeal cancel start >>> appealId=" + appealId);
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        
        try {
            stuffAppealFacade.cancel(appealId, userId);
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货取消申诉接口异常:/stuff/appeal/cancel.do?appealId="+appealId+"&userId=" + userId;
            String subject = "有好货取消申诉接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("appeal cancel end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
    
    @RequestMapping("/detail")
    @ResponseBody
    public Map<String, Object> detail(HttpServletRequest request, @RequestParam("appealId") long appealId){
    	logger.info("appeal detail start >>> appealId=" + appealId);
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        json.put("userId", userId);
        
        try {
        	StuffAppealDetailDto data = stuffAppealFacade.detail(appealId, userId);
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("data", data);
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货申诉详细接口异常:/stuff/appeal/detail.do?appealId="+appealId+"&userId=" + userId;
            String subject = "有好货申诉详细接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("appeal detail end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
    
}
