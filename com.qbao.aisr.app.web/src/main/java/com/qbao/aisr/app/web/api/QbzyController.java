package com.qbao.aisr.app.web.api;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.dto.QbaoZyClassDto;
import com.qbao.aisr.app.dto.search.ZnStuffDto;
import com.qbao.aisr.app.service.facade.QbzyFacade;
import com.qbao.aisr.app.web.controller.base.BaseController;
import org.codehaus.plexus.util.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shuaizhihu
 * @createTime 2017/3/12 16:47
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@RestController
@RequestMapping("/stuff/qbzy/")
public class QbzyController extends BaseController {
    Logger logger = org.slf4j.LoggerFactory.getLogger(QbzyController.class);

    @Autowired
    QbzyFacade qbzyFacade;

    @RequestMapping("goodsClass")
    public Map<String,Object> goodsClasses(HttpServletRequest request){
        logger.info("goodsClasses start");
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            List<QbaoZyClassDto> list= qbzyFacade.goodsClasses();
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", list);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data",null);
            response.put("success", true);
            response.put("message", "系统错误！");
        }
        logger.info("goodsClasses end >>> count time:" + (System.currentTimeMillis() - start));
        return response;
    }

    @RequestMapping("goodsList")
    public Map<String,Object> goodsList(
            @RequestParam(value = "userId",required = false, defaultValue = "0") long userId,
            @RequestParam(value = "cId", required = false, defaultValue = "") long cid,
            @RequestParam(value = "page",required = false, defaultValue = "1") int page,
            @RequestParam(value = "size",required = false, defaultValue = "10") int pageSize
    ){
        logger.info("goodsList start >>>  userId="+userId+" cid="+cid+" page="+page+" size="+pageSize);
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("userId", userId);
        try {
            Page<ZnStuffDto> pageResult = qbzyFacade.goodsList(userId,cid,page,pageSize);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", pageResult.getItems());
            response.put("total",pageResult.getTotalNumber());
            response.put("success", true);
            response.put("message", "Ok");
        } catch (HttpProcessException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data",null);
            response.put("total",0);
            response.put("success", true);
            response.put("message", "请求搜索服务出错！");
        }catch(Exception e){
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data",null);
            response.put("total",0);
            response.put("success", true);
            response.put("message", "系统错误！");
        }
        logger.info("goodsList end >>> count time:" + (System.currentTimeMillis() - start));
        return response;
    }


}
