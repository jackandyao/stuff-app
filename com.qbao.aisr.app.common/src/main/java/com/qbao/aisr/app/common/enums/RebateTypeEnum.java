package com.qbao.aisr.app.common.enums;

import java.util.HashMap;

/**
 * @author wangping
 * @createTime 2017/6/28 下午2:20
 * $$LastChangedDate: 2017-06-28 16:28:43 +0800 (Wed, 28 Jun 2017) $$
 * $$LastChangedRevision: 1369 $$
 * $$LastChangedBy: wangping $$
 */
public enum  RebateTypeEnum {
    ////返利类型 0  宝券 1 RMB
    COUPON(0,"宝券"),SUCCESS(1,"¥");

    private Integer code;
    private String name;

    private RebateTypeEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getCode() {
        return code;
    }
    private static HashMap<Integer, RebateTypeEnum> maps = new HashMap<>();
    static {
        for (RebateTypeEnum type : RebateTypeEnum.values()) {
            maps.put(type.code, type);
        }
    }
    public static RebateTypeEnum asRebateTypeEnum(Integer value) {
        return maps.get(value);
    }
}
