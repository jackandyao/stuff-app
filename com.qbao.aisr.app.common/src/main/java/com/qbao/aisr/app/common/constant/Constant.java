package com.qbao.aisr.app.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liaijun
 * @createTime 17/3/7 下午7:36
 * $$LastChangedDate: 2017-07-19 09:21:14 +0800 (Wed, 19 Jul 2017) $$
 * $$LastChangedRevision: 1455 $$
 * $$LastChangedBy: wangping $$
 */
public class Constant {
    //1已返券
    public static Integer RETURN=1;
    //0: 未返券
    public static Integer UNRETURN=0;

    //1已返券
    public static Integer ISPAYOK=1;
    //0: 未返券
    public static Integer ISPAYNO=0;

    // 服务器返回成功
    public  static final int   RESPONSE_CODE_SCUESS = 1000;

    // 有错误
    public  static final int   RESPONSE_CODE_HAS_ERROR = 1005;
    //调用接口成功返回0
    public static final String SUCCESSCODE="0";

    public static String FAN = "预返";
    public static String RMB = "¥";
    public static String FAN_RMB = "预返¥";
    public static String FAN_JFB = "返积分";


    public static String BAOQUAN = "%宝券";

    public static Map<String, String> map = new HashMap<String, String>();

    public static int IOS = 1;
    public static int ANDROID = 2;

    public static Long  DEFAULT_USER_ID = 0L;

    public static String ORDER_ID_DELIMITER = ",";
}
