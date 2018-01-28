package com.qbao.aisr.app.web.api;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.dto.pai.PaiUploadDto;
import com.qbao.aisr.app.dto.search.ZwStuffDto;
import com.qbao.aisr.app.service.facade.PaiFacade;
import com.qbao.aisr.app.web.controller.base.BaseController;
import org.codehaus.plexus.util.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shuaizhihu
 * @createTime 2017/3/12 17:37
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@RestController
@RequestMapping("/stuff/pai/")
public class PaiController extends BaseController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(PaiController.class);

    @Autowired
    PaiFacade paiFacade;

    @RequestMapping("upload")
    public Map<String,Object> upload(HttpServletRequest request,MultipartFile file){
        logger.info("upload start >>> file:" + file.getOriginalFilename());
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            PaiUploadDto dto = paiFacade.upload(file, userId);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", dto);
            response.put("success", true);
            response.put("message", "Ok");
        }catch(Exception e){
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data",null);
            response.put("success", true);
            response.put("message", "系统错误！");
        }

        logger.info("upload end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }

    /**
     * 根据图片获取分类信息
     * 
     * @param request
     * @param imgUrl
     * @return
     */
    @RequestMapping("facets_img")
    public Map<String,Object> facetsImg(HttpServletRequest request,@RequestParam(value = "imgUrl") String imgUrl){
        logger.info("facetsImg start >>> imgUrl:"+imgUrl);
        long startTime = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        long userId = getCurrentUserId(request);
        response.put("userId", userId);
        try {
            List list = paiFacade.facetsImg(imgUrl);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", list);
            response.put("success", true);
            response.put("message", "Ok");
        } catch (HttpProcessException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data",null);
            response.put("success", true);
            response.put("message", "查询出错！");
        }catch(Exception e){
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data",null);
            response.put("success", true);
            response.put("message", "系统错误！");
        }
        logger.info("facetsImg end >>> count time:" + (System.currentTimeMillis() - startTime));
        return response;
    }

    @RequestMapping("search")
    public Map<String,Object> search(
            HttpServletRequest request,
            @RequestParam(value = "imgUrl") String imgUrl,
            @RequestParam(value = "cId",required = false, defaultValue = "0") String cId,
            @RequestParam(value = "sort",required = false, defaultValue = "") String sort,
            @RequestParam(value = "source",required = false, defaultValue = "") String source,
            @RequestParam(value = "page",required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int pageSize, @RequestParam(value = "device", required = true) int device
            ){
        long userId = this.getCurrentUserId(request);
        logger.info("search start >>> imgUrl="+imgUrl+" userId="+userId+" sort="+sort+"&source="+source+"&page="+page+" size="+pageSize);
        long start = System.currentTimeMillis();
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("userId", userId);
        try {
            Page<ZwStuffDto> pageResult = paiFacade.search(imgUrl, userId, cId, source, sort, page, pageSize, device);
            response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
            response.put("data", pageResult.getItems());
            response.put("total",pageResult.getTotalNumber());
            response.put("success", true);
            response.put("message", "Ok");
        } catch (UnsupportedEncodingException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            response.put("responseCode", Constant.RESPONSE_CODE_HAS_ERROR);
            response.put("data",null);
            response.put("total",0);
            response.put("success", true);
            response.put("message", "url不合法");
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
        logger.info("search end >>> count time:" + (System.currentTimeMillis() - start));
        return response;
    }


}
