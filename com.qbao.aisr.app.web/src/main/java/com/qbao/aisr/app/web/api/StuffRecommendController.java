package com.qbao.aisr.app.web.api;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.dto.search.ZwStuffDetailDto;
import com.qbao.aisr.app.dto.search.ZwStuffOnedayDto;
import com.qbao.aisr.app.service.stuffrecommend.IStuffRecommendService;
import com.qbao.aisr.app.web.controller.base.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @author zhangjun
 * @createTime 2017/3/11 14:27
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@RestController
@RequestMapping("/stuff/rec")
public class StuffRecommendController extends BaseController {
    public Logger logger = Logger.getLogger(getClass());

    @Autowired
	private IStuffRecommendService iStuffRecommendService;
    
	/**
	 * 相似推荐接口
	 * @param
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/similar", method = {RequestMethod.POST, RequestMethod.GET},
            produces = "application/json; charset=utf-8")
	public Map<String, Object> similar(HttpServletRequest request, @RequestParam("stuffId") long stuffId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "8") int size, @RequestParam("device") int device) {
        logger.info("stuff similar start >>> stuffId=" + stuffId);
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        //long userId = getCurrentUserId(request);
        long userId = 0L;
        response.put("userId", userId);

        try {
            Page<ZwStuffDetailDto> data=iStuffRecommendService.zwSimilar(stuffId, page, size, device);
            response.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            response.put("data", data.getItems());
            response.put("total", data.getTotalNumber());
            response.put("success", true);
            response.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);

            String urlInfo = "APP有好货相似接口异常:/stuff/rec/similar.do?stuffId="+stuffId;
            String subject = "有好货详细接口异常";
        }
        logger.info("stuff similar end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }

    /**
     * 关联推荐接口
     * @param
     * @return
     */
	@ResponseBody
    @RequestMapping(value = "/related", method = {RequestMethod.POST, RequestMethod.GET},
            produces = "application/json; charset=utf-8")
	public Map<String, Object> related(HttpServletRequest request, @RequestParam("stuffId") long stuffId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "8") int size, @RequestParam("device") int device) {
        logger.info("stuff related start >>> stuffId=" + stuffId);
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        //long userId = getCurrentUserId(request);
        long userId = 0L;
        response.put("userId", userId);

        try {
            Page<ZwStuffDetailDto> data=iStuffRecommendService.zwRelated(stuffId, page, size, device);
            response.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            response.put("data", data.getItems());
            response.put("total", data.getTotalNumber());
            response.put("success", true);
            response.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);

            String urlInfo = "APP有好货关联接口异常:/stuff/rec/related.do?stuffId="+stuffId;
            String subject = "有好货详细接口异常";
        }
        logger.info("stuff related end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }


    /**
     * 热卖推荐接口
     * @param
     * @return
     */
	@ResponseBody
    @RequestMapping(value = "/hot", method = {RequestMethod.POST, RequestMethod.GET},
            produces = "application/json; charset=utf-8")
	public Map<String, Object> hot(HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "8") int size, @RequestParam("device") int device) {
        logger.info("stuff hot start >>> " );
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        //long userId = getCurrentUserId(request);
        long userId = 0L;
        response.put("userId", userId);

        try {
            Page<ZwStuffDetailDto> data=iStuffRecommendService.zwHot(page, size, device);
            response.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            response.put("data", data.getItems());
            response.put("total", data.getTotalNumber());
            response.put("success", true);
            response.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);

            String urlInfo = "APP有好货热卖接口异常:/stuff/rec/hot.do?";
            String subject = "有好货热卖接口异常";
        }
        logger.info("stuff hot end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }
	
	
	/**
     * 热卖推荐接口
     * @param
     * @return
     */
	@ResponseBody
    @RequestMapping(value = "/guess", method = {RequestMethod.POST, RequestMethod.GET},
            produces = "application/json; charset=utf-8")
	public Map<String, Object> guess(HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "8") int size, @RequestParam("device") int device) {
        logger.info("stuff guess start >>> " );
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);

        try {
            Page<ZwStuffDetailDto> data=iStuffRecommendService.zwGuess(userId, page, size, device);
            response.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            response.put("data", data.getItems());
            response.put("total", data.getTotalNumber());
            response.put("success", true);
            response.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);

            String urlInfo = "APP有好货猜你喜欢接口异常:/stuff/rec/hot.do?";
            String subject = "有好货猜你喜欢接口异常";
        }
        logger.info("stuff guess end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }
	
	
	/**
     * oneday接口
     * @param
     * @return
     */
	@ResponseBody
    @RequestMapping(value = "/oneday", method = {RequestMethod.POST, RequestMethod.GET},
            produces = "application/json; charset=utf-8")
	public Object oneday(HttpServletRequest request,@RequestParam(value="callback",required = false) String callback,@RequestParam(value="userId",required = false) Long userId, @RequestParam(value = "userProfile", required = false) String userProfile,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "30") int size, @RequestParam("device") int device) {
        logger.info("stuff oneday start >>> " );
        Object result = null;
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        userId = (0==userId )? getCurrentUserId(request) : userId;
        response.put("userId", userId);

        try {
            Page<ZwStuffOnedayDto> data=iStuffRecommendService.oneday(userId, userProfile, page, size, device);
            response.put("responseCode",Constant.RESPONSE_CODE_SCUESS);
            response.put("data", data.getItems());
            response.put("total", data.getTotalNumber());
            response.put("success", true);
            response.put("message", "Ok");
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            String msg = ExceptionUtils.getFullStackTrace(ex);
            response.put("message", msg);

            String urlInfo = "APPoneday接口异常:/stuff/rec/hot.do?";
            String subject = "oneday接口异常";
        }
        logger.info("stuff oneday end >>> count time:" + (System.currentTimeMillis() - startTime));
        if (StringUtils.isBlank(callback)) {
            result = response;
        } else {
            result = bindJsonp(callback, response);
        }
        return result;
    }


    private MappingJacksonValue bindJsonp(String callback, Object data) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(data);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }

}
