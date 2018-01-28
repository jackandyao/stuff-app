package com.qbao.aisr.app.web.api;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.model.CommMsg;
import com.qbao.aisr.app.service.facade.CommMsgFacade;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangjun
 * @createTime 17/5/24 上午10:22
 * $$LastChangedDate: 2017-03-30 16:51:33 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 595 $$
 * $$LastChangedBy: zhangjun $$
 */
@Controller
@RequestMapping("/stuff/msg/")
public class CommMsgController  {
    private Logger logger = Logger.getLogger(CommMsgController.class);
    @Autowired
    private CommMsgFacade commMsgFacade;


    /**
     * 活动接口
     * @param request
     * @param typeId
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> right(HttpServletRequest request, @RequestParam("typeId") int typeId){
    	logger.info("msg list start >>> typeId=" + typeId);
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
//        long userId = getCurrentUserId(request);
//        json.put("userId", userId);
        
        try {
            CommMsg data = commMsgFacade.findByTypeId(typeId);
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("data", data);
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货活动接口异常:/stuff/msg/list.do?typeId=" + typeId;
            String subject = "有好货活动接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("msg list end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
    
}
