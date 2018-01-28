package com.qbao.aisr.app.web.api;

import java.util.HashMap;
import java.util.List;
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
import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.dto.SysMessageDto;
import com.qbao.aisr.app.model.SysMessage;
import com.qbao.aisr.app.service.facade.SysMessageFacade;
import com.qbao.aisr.app.web.controller.base.BaseController;
@Controller
@RequestMapping("/stuff/message")
public class SysMessageController extends BaseController {
    private Logger logger = Logger.getLogger(SysMessageController.class);
    @Autowired
    private SysMessageFacade sysMessageFacade;
    
    @RequestMapping("/queryMess")
    @ResponseBody
    public Map<String, Object> querySysMessage(HttpServletRequest request, @RequestParam("userId") Long userId){
        logger.info("userId:"+userId);
        long start = System.currentTimeMillis();
        logger.info("finish to fetch ad sysmessage. and cost : " + (System.currentTimeMillis() - start) + ".ms");
        Map<String, Object> response = new HashMap<String, Object>();
        
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            List<SysMessageDto> data = sysMessageFacade.findSysMessage(userId);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            String notifyMsg = "search sysmessage is userid"+userId;
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        return response;
    }
    
    
    @RequestMapping("/updateMess")
    @ResponseBody
    public Map<String, Object> updateSysMessage(HttpServletRequest request, @RequestParam("userId") Long userId,
            @RequestParam("status") int status,@RequestParam("messageId") int messageId){
        logger.info("userId:"+userId+",status"+status+",messageId"+messageId);
        long start = System.currentTimeMillis();
        logger.info("finish to update sysmessage. and cost : " + (System.currentTimeMillis() - start) + ".ms");
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            
            SysMessage sysMessage = new SysMessage();
            sysMessage.setUser_id(userId);
            sysMessage.setId(messageId);
            sysMessage.setMessage_status(status);
            
            int result = sysMessageFacade.updateSysMessage(sysMessage);
            response.put("data", result);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("success", false);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            String notifyMsg = "update sysmessage is userid"+userId +"and messageid"+messageId;
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        return response;
        
    }
}
