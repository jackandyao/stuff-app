package com.qbao.aisr.app.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.dto.search.ZwStuffDetailDto;
import com.qbao.aisr.app.service.facade.SearchFacade;
import com.qbao.aisr.app.web.controller.base.BaseController;


/**
 * @author zhangjun
 * @createTime 2017/3/11 14:27
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@RestController
@RequestMapping("/stuff")
public class StuffDetailController extends BaseController {
    public Logger logger = Logger.getLogger(getClass());

    @Autowired
    SearchFacade searchFacade;
    
    /**
     * 有好货详细接口
     * @param request
     * @param stuffId
     * @param device
     * @return
     */
    @ResponseBody
    @RequestMapping("/detail")
    public  Map<String, Object> detail(HttpServletRequest request, @RequestParam("stuffId") long stuffId, @RequestParam("device") int device){
        logger.info("stuff detail start >>> stuffId=" + stuffId);
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        //long userId = getCurrentUserId(request);
        long userId = 0L;
        response.put("userId", userId);
        try {
            ZwStuffDetailDto data=searchFacade.searchStuffById(userId,stuffId,device);
            response.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");

        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);

        }
        logger.info("stuff detail end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }
    
}
