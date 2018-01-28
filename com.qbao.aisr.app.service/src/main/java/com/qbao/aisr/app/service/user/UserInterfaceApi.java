package com.qbao.aisr.app.service.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.qbao.aisr.app.common.util.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author by zhangchanghong on 15/12/6.
 */

@Component
public class UserInterfaceApi {
    private final static Logger log = Logger.getLogger(UserInterfaceApi.class);
    public static final String default_nickname = "宝粉";
    public static final String USER_CAS_ID_KEY = "id";
    public static final String USER_NAME_KEY = "userName";
    public static final String NICK_NAME_KEY = "nickName";
    public static final String USER_HYID_ID_KEY = "hyipUserId";
    public static final String USER_ID = "userId";
    public static final String USER_ENABLE_KEY = "enabled";
    public static final String USER_MOBILEPHONE_KEY = "mobile";
    public static final String USER_EMAIL_KEY = "email";
    public static final String USER_CREATE_TIME = "createTime";
    public static final String MAN = "M";
    public static final String WOMEN = "F";
    public static final String APP_ID_KEY = "appId";
    public static final String USER_ID_KEY = "userId";
    private final String APP_ID = "goodstuff_service";

    public List<String> getUserRoleByUserName(final String username) {
        String userId = fetchUserId(username);
        if(StringUtils.isNotBlank(userId)) {
            final String userUrl = "http://api.user.qbao.com/api/get/userRole/%s/new";

            try {
                List<String> list = HttpUtils.get(String.format(userUrl, userId), String.class);
                if (null != list) {
                    // log.info("根据用户名:{}, 获取用户角色接口");
                    return list;
                }
            } catch (Exception e) {
                log.error("根据用户名:"+username+", 获取用户角色接口,错误:"+ e);
            }
        }
        return Collections.emptyList();
    }

    public Map<String, Object> getUserBaseInfo(final String username) {
        final String userUrl = "http://ucslaveapi.qbao.com/api/load/userDetail/userId";
        String userId = fetchUserId(username);
        Map<String, String> paras = Maps.newHashMap();
        paras.put(USER_ID_KEY, userId);
        paras.put(APP_ID_KEY, APP_ID);
        try {
            String json = HttpUtils.doPost(userUrl, paras, true);
            log.info("UserInterfaceApi-getUserBaseInfo():用户基本信息接口返回:"+ json);
            if (StringUtils.isNotEmpty(json)) {
                JSONObject userInfo = JSON.parseObject(json);
                String code = userInfo.get("code").toString();
                if ("1".equals(code)) {
                    json = userInfo.get("data").toString();
                    Map<String, Object> map2 = (Map<String, Object>) JSON.parse(json);
                    String jsonString = JSON.toJSONString(map2);
                    log.info("UserInterfaceApi-getUserBaseInfo():用户信息:"+ jsonString);
                    return map2;
                }
            }
        } catch (Exception e) {
            log.error("根据用户名:"+username+", 获取用户信息接口,错误:"+ e);
        }

        return null;
    }

    private String fetchUserId(String userName) {
        String userId = null;
        if (StringUtils.isNotBlank(userName)) {
            final String userUrl = "http://loginapi.qbao.com/api/query/userId/userName";
            Map<String, String> paras = Maps.newHashMap();
            paras.put(USER_NAME_KEY, userName);
            paras.put(APP_ID_KEY, APP_ID);
            try {
                String res = HttpUtils.doPost(userUrl, paras, true);
                JSONObject userInfo = JSON.parseObject(res);
                String code = userInfo.get("code").toString();
                if ("1".equals(code)) {
                    userId = userInfo.get("data").toString();
                }
            } catch (Exception ex) {
                log.error("根据用户名:"+userName+", 获取用户信息接口,错误:"+ ex);
            }

        }
        return userId;
    }
}
