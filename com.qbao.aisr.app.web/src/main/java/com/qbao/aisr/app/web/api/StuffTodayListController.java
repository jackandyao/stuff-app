package com.qbao.aisr.app.web.api;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.service.facade.StuffRecommendFacade;
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
 * @author zhangjun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-30 16:51:33 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 595 $$
 * $$LastChangedBy: zhangjun $$
 */
@Controller
@RequestMapping("/stuff/")
public class StuffTodayListController extends BaseController {
    private Logger logger = Logger.getLogger(StuffTodayListController.class);
    @Autowired
    private StuffRecommendFacade stuffRecommendFacade;

    /**
     * 根据userId和设备类型返回今日清单
     * @param
     * @return
     */
    @RequestMapping("/todaylist")
    @ResponseBody
    public Map<String, Object> todaylist(HttpServletRequest request, @RequestParam("device") int device) {
    	long userId = getCurrentUserId(request);
    	logger.info("todaylist start >>> userId=" + userId );
        long startTime = System.currentTimeMillis();
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("userId", userId);
        try {
        	
        	List<Map<String, Object>> todaylist = stuffRecommendFacade.todaylist(userId, device);
            json.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            json.put("data", todaylist);
            json.put("success", true);
            json.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            json.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            json.put("message", msg);

            String urlInfo = "有好货今日清单接口异常:/stuff/todaylist?userId=" + userId +"&device=" + device;
            String subject = "有好货今日清单接口异常";
            NotifierUtil.notifyByPhone(urlInfo);
            NotifierUtil.notifyByEmail(subject, msg);
        }
        logger.info("queryGoods end >>> count time:" + (System.currentTimeMillis() - startTime));
        return json;
    }
}
