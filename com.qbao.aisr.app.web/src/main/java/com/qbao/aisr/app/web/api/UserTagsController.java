package com.qbao.aisr.app.web.api;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.util.NotifierUtil;
import com.qbao.aisr.app.dto.UserTagsDto;
import com.qbao.aisr.app.service.facade.UserTagsFacade;
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
import java.util.Map;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-27 17:45:36 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 557 $$
 * $$LastChangedBy: wangping $$
 */
@Controller
@RequestMapping("/stuff/custom/")
public class UserTagsController extends BaseController {

    public Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserTagsFacade userTagsFacde;

    /**
     * 获取用户标签
     * 
     * @return
     */
    @RequestMapping("/getUserTags")
    @ResponseBody
    public Map<String, Object> fetchUserTags(HttpServletRequest request, @RequestParam(value = "typeId", required = true) int typeId) {

        long userId = getCurrentUserId(request);
        logger.info("fetching user tags :  user id =[" + userId + "] and tag type id  =[" + typeId + "]  ... ");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("userId", userId);
        try {
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            UserTagsDto data = userTagsFacde.fetchUserTags(userId, typeId);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            response.put("success", false);
            String notifyMsg = "fetching  fetchUserTags meet error:  user id =[" + userId + "] and tag type id  =[" + typeId + "] ";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        logger.info("finish to fetch user tag : user id [" + userId + "] and tag type id  =[" + typeId + "]! and cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }

    /**
     * 修改用户标签
     * 
     * @param
     * @return
     */
    @RequestMapping("/updateUserTags")
    @ResponseBody
    public Map<String, Object> updateUserTags(HttpServletRequest request, @RequestParam(value = "typeId", required = true) int typeId, @RequestParam(value = "tagDetailIds", required = true) String tagDetailIds) {
        Long userId = getCurrentUserId(request);
        logger.info("updating user tags : user id =[" + userId + "], tag type id =[" + typeId + "], and tagDetailIds=[" + tagDetailIds + "]   ... ");
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("userId", userId);
        long start = System.currentTimeMillis();
        try {
            userTagsFacde.modifyUserTags(userId, typeId, tagDetailIds);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception ex) {
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);
            response.put("success", false);
            String notifyMsg ="updating user tags values :    user id =[" + userId + "], tag type id =[" + typeId + "], and tagDetailIds=[" + tagDetailIds + "]";
            NotifierUtil.notifyByPhone(notifyMsg);
            NotifierUtil.notifyByEmail(notifyMsg, msg);
            logger.error(notifyMsg,ex);
        }
        logger.info("finish to update user tags :  user id =[" + userId + "], tag type id =[" + typeId + "], and tagDetailIds=[" + tagDetailIds + "] and cost : " + (System.currentTimeMillis() - start) + ".ms");
        return response;
    }
}
