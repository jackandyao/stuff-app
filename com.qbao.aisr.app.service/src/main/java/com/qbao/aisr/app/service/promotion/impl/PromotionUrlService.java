package com.qbao.aisr.app.service.promotion.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.qbao.aisr.app.common.enums.DeviceEnum;
import com.qbao.aisr.app.model.ShopPromotion;
import com.qbao.aisr.app.model.StuffPromotion;
import com.qbao.aisr.app.model.search.ZwStuff;
import com.qbao.aisr.app.service.promotion.IPromotionUrlService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author wangping
 * @createTime 2017/3/30 下午3:31
 * $$LastChangedDate: 2017-04-10 11:47:22 +0800 (Mon, 10 Apr 2017) $$
 * $$LastChangedRevision: 662 $$
 * $$LastChangedBy: wangping $$
 */

@Service
public class PromotionUrlService implements IPromotionUrlService {

    @Override
    public String findPromtoionUrl(StuffPromotion stuffPromotion, DeviceEnum device) {

        if (null != stuffPromotion && null != device) {
            if (DeviceEnum.IOS == device) {
                return stuffPromotion.getIosPromotionUrl();
            }
            if (DeviceEnum.ANDROID == device) {
                return stuffPromotion.getAndroidPromotionUrl();
            }
        }
        return StringUtils.EMPTY;
    }

    @Override
    public String findPromtoionUrl(ShopPromotion shopPromotion, DeviceEnum device) {
        if (null != shopPromotion && null != device) {
            if (DeviceEnum.IOS == device) {
                return shopPromotion.getIosPromotionUrl();
            }
            if (DeviceEnum.ANDROID == device) {
                return shopPromotion.getAndroidPromotionUrl();
            }
        }
        return StringUtils.EMPTY;
    }
    @Override
    public String findPromtoionUrl(ZwStuff zwStuff, DeviceEnum device) {
        if (null != zwStuff && null != device) {
            if (DeviceEnum.IOS == device) {
                return zwStuff.getIosPromotionUrl();
            }
            if (DeviceEnum.ANDROID == device) {
                return zwStuff.getAndroidPromotionUrl();
            }
        }
        return StringUtils.EMPTY;
    }

    @Override
    public String findPromtoionUrl(JsonArray dataList, DeviceEnum device, int size) {

        if (null != dataList && null != device) {
            if (DeviceEnum.IOS == device) {
                JsonElement jsonElement = dataList.get(size).getAsJsonObject().get("iosPromotionUrl");
                return jsonElement == null ? StringUtils.EMPTY : jsonElement.getAsString();
            }
            if (DeviceEnum.ANDROID == device) {
                JsonElement jsonElement = dataList.get(size).getAsJsonObject().get("androidPromotionUrl");
                return jsonElement == null ? StringUtils.EMPTY : jsonElement.getAsString();
            }
        }
        return StringUtils.EMPTY;
    }
}
