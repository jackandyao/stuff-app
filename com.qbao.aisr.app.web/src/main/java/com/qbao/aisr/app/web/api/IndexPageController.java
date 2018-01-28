package com.qbao.aisr.app.web.api;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.dto.MenuDto;
import com.qbao.aisr.app.dto.StuffModuleDto;
import com.qbao.aisr.app.service.facade.IndexPageFacade;
import com.qbao.aisr.app.web.controller.base.BaseController;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangping
 * @createTime 17/3/3 下午6:58
 * $$LastChangedDate: 2017-03-31 09:39:13 +0800 (Fri, 31 Mar 2017) $$
 * $$LastChangedRevision: 602 $$
 * $$LastChangedBy: wangping $$
 */
@Controller
@RequestMapping("/stuff/indexPage/")
public class IndexPageController extends BaseController {
    public Logger logger = Logger.getLogger(getClass());

    @Autowired
    private IndexPageFacade facade;

    @RequestMapping("/menus")
    @ResponseBody
    public Map<String, Object> fetchMenus(HttpServletRequest request) {
        logger.info("fetching index page menus  ... ");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            response.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            List<MenuDto> data = facade.fetchMenus();
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            String notifyMsg = "fetching  menu meet error:  user id =[" + userId + "] ";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        logger.info("finish to fetch index page menus. and cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }

    @RequestMapping("/modules")
    @ResponseBody
    public Map<String, Object> fetchModules(HttpServletRequest request, @RequestParam(value = "device", required = true) int device ) {
        long userId = getCurrentUserId(request);
        logger.info("fetching index page modules :  user id =[" + userId + "], device=["+device+"]  ...");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            List<StuffModuleDto> data = facade.fetchModules(userId,device);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            response.put("success", false);
            String notifyMsg = "fetching user Modules meet error:   user id =[" + userId + "], device=["+device+"] . " + new Date();
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        logger.info("finish to fetch index page modules  : user id [" + userId + "]. and cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }
}
