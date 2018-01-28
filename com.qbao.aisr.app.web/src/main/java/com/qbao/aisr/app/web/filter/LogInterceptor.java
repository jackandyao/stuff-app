package com.qbao.aisr.app.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbao.aisr.app.common.constant.ExceptionEnum;
import com.qbao.aisr.app.web.controller.base.BaseController;
import com.qbao.aisr.app.web.security.CasUser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by liuwang on 2016/7/4.
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    private final static Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);
    private final static ObjectMapper jsonMapper = new ObjectMapper();
    /*  @Autowired
      private QbaoHelperService qbaoHelperService;*/
    @Autowired
    private BaseController baseController;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        LOG.info("prehandle Url" + url);
        if (url != null && (url.contains(".do") || url.contains("login"))) {
            if (url.contains("api/account4Client/login")
                    || url.contains("stuff/service/switch.do")
                    || url.contains("stuff/order/persistOrder.do")
                    || url.contains("qbao_helper/qbao_helper_page.do")
                    || url.contains("/ass_pay")
                    || url.contains("/goods_stat")
                    || url.contains("firstPage/isLogin.do")
                    || url.contains("firstPage/isSubscribe.do")
                    || url.contains("firstPage/subscribe_info.do")
                    || url.contains("/qbao_helper/get_home_page_promotion.do")
                    || url.contains("/ass_achievement_wall/wall.do")
                    || url.contains("/ass_achievement_wall/compass.do")
                    || url.contains("/merchant_dw/")
                    || url.contains("/ass_compass_task/getCompassTask.do")
                    || url.contains("/login.do")
                    || url.contains("index/index_page.do")
                    || url.contains("image/getUserUploadImageScore.do")
                    || url.contains("stuff/service/switch.do")
                    || url.contains("stuff/msg/list.do")
                    || url.contains("stuff/rec/oneday.do")
                    || url.contains("stuff/ad/banner.do")
                    ) {

            }
            else {
                String t = request.getSession().getAttribute("HAS_NOT_ORDER") == null ? "" : request.getSession().getAttribute("HAS_NOT_ORDER").toString();
                LOG.info("LogInterceptor-preHandle():{}:HAS_NOT_ORDER:{}", t);
                if (t.equals("true")) {
                    SecurityContext context = SecurityContextHolder.getContext();
                    LOG.info("LogInterceptor-preHandle():context:{}", context);
                    PrintWriter writer = response.getWriter();
                    if (context != null) {
                        Authentication authentication = context.getAuthentication();
                        if (authentication != null) {
                            Object principal = authentication.getPrincipal();
                            if (principal instanceof CasUser) {
                                CasUser cu = (CasUser) principal;
                                writer.write("{\"success\":false,\"message\":\"please login first" + "\",\"returnCode\":" + ExceptionEnum.ASS_NOT_SUBSCRIBED_EXCEPTION.key + ",\"data\":{\"payMoney\":" + request.getSession().getAttribute("PAY_MONEY").toString() + ",\"userId\":\"" + cu.getUserId() + "\",\"userName\":\"" + cu.getUserName() + "\",\"nikeName\":\"" + cu.getNickName() + "\"}}");
                            } else {
                                writer.write("{\"success\":false,\"message\":\"please login first" + "\",\"returnCode\":" + ExceptionEnum.ASS_NOT_SUBSCRIBED_EXCEPTION.key + ",\"data\":{\"payMoney\":" + request.getSession().getAttribute("PAY_MONEY").toString() + "}}");
                            }
                        }
                    } else {
                        writer.write("{\"success\":false,\"message\":\"please login first" + "\",\"returnCode\":" + ExceptionEnum.ASS_NOT_SUBSCRIBED_EXCEPTION.key + ",\"data\":{\"payMoney\":" + request.getSession().getAttribute("PAY_MONEY").toString() + ",\"userId\":\"-1\",\"userName\":\"宝粉\",\"nikeName\":\"宝粉\"}}");
                    }
                    writer.flush();
                    writer.close();
                    return false;
                } else if (StringUtils.isEmpty(t)) {
                    long userId = baseController.getCurrentUserId(request);
                    if (0 == userId) {
                        PrintWriter writer = response.getWriter();
                        writer.write("{\"success\":false,\"message\":\"please login first" + "\",\"returnCode\":" + ExceptionEnum.ASS_GO_LOGIN_EXCEPTION.key + "}");
                        writer.flush();
                        writer.close();
                        return false;
                    }
           /*         int forwardCode=qbaoHelperService.getForwardCode(userId);
                    LOG.info("LogInterceptor-preHandle():拦截返回信息:userId:{}:forwardCode:{}",userId,forwardCode);
                    if(Constants.CONSTANTS_INT_0==forwardCode){
                        Object isTrue=request.getSession().getAttribute("ASS_NOT_SUBSCRIBED_EXCEPTION");
                        if(null==isTrue){
                            request.getSession().setAttribute("ASS_NOT_SUBSCRIBED_EXCEPTION","1");
                            PrintWriter writer = response.getWriter();
                            writer.write("{\"success\":false,\"message\":\"please login first"+"\",\"returnCode\":"+ ExceptionEnum.ASS_NOT_SUBSCRIBED_EXCEPTION.key+"}");
                            writer.flush();
                            writer.close();
                            return false;
                        }
                    }else if(Constants.CONSTANTS_INT_2==forwardCode){
                        Object isTrue=request.getSession().getAttribute("ASS_NOT_SUBSCRIBED_NEW_USER_EXCEPTION");
                        if(null==isTrue){
                            request.getSession().setAttribute("ASS_NOT_SUBSCRIBED_NEW_USER_EXCEPTION","1");
                            PrintWriter writer = response.getWriter();
                            writer.write("{\"success\":false,\"message\":\"please login first"+"\",\"returnCode\":"+ ExceptionEnum.ASS_NOT_SUBSCRIBED_NEW_USER_EXCEPTION.key+"}");
                            writer.flush();
                            writer.close();
                            return false;
                        }
                    }*/
                }
            }
        }

        long startTime = System.currentTimeMillis();
        request.setAttribute("Interceptor_StartTime", startTime);
        String clazzName = ((HandlerMethod) handler).getMethod().getDeclaringClass().getName();
        String indexInfo = clazzName.substring(clazzName.lastIndexOf(".") + 1) + "." + ((HandlerMethod) handler).getMethod().getName();
        //HttpSession session = request.getSession();

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("Interceptor_StartTime");
        request.removeAttribute("Interceptor_StartTime");
        long endTime = System.currentTimeMillis();
        HandlerMethod handler2 = (HandlerMethod) handler;
        String clazzName = handler2.getMethod().getDeclaringClass().getName();
        String indexInfo = clazzName.substring(clazzName.lastIndexOf(".") + 1) + "." + handler2.getMethod().getName();
        if (modelAndView != null) {
            SecurityContext context = SecurityContextHolder.getContext();
            LOG.info("LogInterceptor-postHandle():context:{}", context);
            if (context != null) {
                Authentication authentication = context.getAuthentication();
                if (authentication != null) {
                    Object principal = authentication.getPrincipal();
                    if (principal instanceof CasUser) {
                        CasUser cu = (CasUser) principal;
                        LOG.info("[{}]- userId: {}, userName: {}, mobile: {}, ResultSet: {}, ViewName:{}", indexInfo, cu.getUserId(), cu.getUserName(), cu.getMobile(), jsonMapper.writeValueAsString(modelAndView.getModel()), modelAndView.getViewName());
                    }
                }
            } else {
                LOG.info("[{}]- userId: {}, userName: {}, mobile: {}, ResultSet: {}, ViewName:{}", indexInfo, 0, "", "", jsonMapper.writeValueAsString(modelAndView.getModel()), modelAndView.getViewName());
            }
        }
        LOG.info("[{}]- Time: {}(ms)", indexInfo, endTime - startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
