package com.qbao.aisr.app.web.security;

import org.jasig.cas.client.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author by zhangchanghong on 15/12/6.
 */
public class QbaoCasAuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(QbaoCasAuthenticationEntryPoint.class);

    private static final String RESPONSE_TYPE_APPLICATION_JSON = "application/json";
    private static final String HEADER_RESPONSE_CONTENT_TYPE = "Response-Content-Type";
    private static final String X_REQUESTED_WITH = "x-requested-with";
    private static final String XMLHttpRequest = "XMLHttpRequest";
    private static final String REQUEST_TYPE = "requestType";
    private static final String SOURCE_TYPE = "sourceType";
    private static final String WAP = "wap";
    private static final String CLIENT = "client";

    private static final String JSON_MSG_INVALID_SESSION = "{\"success\":false,\"message\":\"session timeout,please login!\",\"returnCode\":100001,\"data\":null}";
    private static final String SESSION_TIME_OUT_MSG = "{\"success\":false,\"message\":\"session timeout,please login!\",\"returnCode\":100001,\"data\":null}";
    private static final String JSON_MSG_INVALID_SESSION_NEW = "{\"message\":\"session timeout,please login!\",\"responseCode\":100001,\"data\":null}";

    private ServiceProperties serviceProperties;
    private String loginUrl;
    private String checkUserUrl;

    @Deprecated
    private boolean encodeServiceUrlWithSessionId = true;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String requestURI = request.getServletPath();
        final String urlEncodedService = createServiceUrl(request, response);
        final String redirectUrl = createRedirectUrl(requestURI, urlEncodedService);

        String type = request.getHeader(HEADER_RESPONSE_CONTENT_TYPE);
        log.info("invalid session for content type <{}> and url:{}", type, requestURI);

        //        String requestType = request.getHeader(REQUEST_TYPE);
        //        if (requestType != null && requestType.equalsIgnoreCase(WAP)) {
        //            response.sendRedirect(redirectUrl);
        //        } else {
        //            log.info("client request, invalid session, need login");
        //            PrintWriter writer = response.getWriter();
        //            writer.write(JSON_MSG_INVALID_SESSION_NEW);
        //            writer.close();
        //            return;
        //        }

        // new client
        //        String client = request.getHeader(SOURCE_TYPE);
        //        log.info("client_sourceType: {}", client);
        //        if (client != null && client.equals(CLIENT) && isNotCheckUserUrl(requestURI)) {
        //            PrintWriter writer = response.getWriter();
        //            writer.write(JSON_MSG_INVALID_SESSION_NEW);
        //            writer.close();
        //            return;
        //        }

        // web ajax request session invalid, jump login page
        //if (request.getHeader(X_REQUESTED_WITH) != null && request.getHeader(X_REQUESTED_WITH).equalsIgnoreCase(XMLHttpRequest)) {
        String url = request.getRequestURI();
        log.info("[QbaoCasAuthenticationEntryPoint.commence()]-请求URL:{}，跳转URL:{}" ,url, redirectUrl);
        if(url.contains("/login.do") && !url.contains("api/v34/account4Client/login.do")){
            response.sendRedirect(redirectUrl);
            return;
        }else{   // 表示全站目前返回 json格式 ， 项目特殊
            log.info("web端ajax异步调用session失效，需要登录");
            PrintWriter writer = response.getWriter();
            writer.write(SESSION_TIME_OUT_MSG);
            writer.close();
            return;
        }

       /* String client = request.getHeader(SOURCE_TYPE);
        String sender = request.getHeader("sender");
        if (client != null && client.equals(CLIENT) && isNotCheckUserUrl(requestURI)) {
            if (sender != null || sender.equals("webview")) {
                response.sendRedirect("http://banyan.qbao.com/qbao_helper/notlogin.do");
                return;
            } else {
                PrintWriter writer = response.getWriter();
                writer.write(JSON_MSG_INVALID_SESSION_NEW);
                writer.close();
                return;
            }
        }

        if (RESPONSE_TYPE_APPLICATION_JSON.equalsIgnoreCase(type) && isNotCheckUserUrl(requestURI)) {
            log.info("return json format data of session timeout.");
            PrintWriter writer = response.getWriter();
            writer.write(JSON_MSG_INVALID_SESSION);
            writer.close();
        } else {
            response.sendRedirect(redirectUrl);
        }*/
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasLength(this.loginUrl, "loginUrl must be specified");
        Assert.notNull(this.serviceProperties, "serviceProperties must be specified");
    }

    private boolean isNotCheckUserUrl(String url) {
        return checkUserUrl == null || !checkUserUrl.equalsIgnoreCase(url);
    }

    private String createServiceUrl(final HttpServletRequest request, final HttpServletResponse response) {
        return CommonUtils.constructServiceUrl(null, response, this.serviceProperties.getService(), null, this.serviceProperties.getArtifactParameter(), this.encodeServiceUrlWithSessionId);
    }

    protected String createRedirectUrl(final String requestURI, final String serviceUrl) {
        //当用户停留在商家平台下的页面超时，需要跳转到商家登录页面，而不是会员登录页面
        return CommonUtils.constructRedirectUrl(this.loginUrl, this.serviceProperties.getServiceParameter(), serviceUrl, this.serviceProperties.isSendRenew(), false);
    }

    public void setServiceProperties(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public void setCheckUserUrl(String checkUserUrl) {
        this.checkUserUrl = checkUserUrl;
    }

    public void setEncodeServiceUrlWithSessionId(boolean encodeServiceUrlWithSessionId) {
        this.encodeServiceUrlWithSessionId = encodeServiceUrlWithSessionId;
    }
}
