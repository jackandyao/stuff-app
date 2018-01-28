package com.qbao.aisr.app.service.promotion;

import com.google.gson.JsonArray;
import com.qbao.aisr.app.common.enums.DeviceEnum;
import com.qbao.aisr.app.model.ShopPromotion;
import com.qbao.aisr.app.model.StuffPromotion;
import com.qbao.aisr.app.model.search.ZwStuff;

/**
 * @author wangping
 * @createTime 2017/3/30 下午3:30
 * $$LastChangedDate: 2017-04-10 11:47:22 +0800 (Mon, 10 Apr 2017) $$
 * $$LastChangedRevision: 662 $$
 * $$LastChangedBy: wangping $$
 */
public interface IPromotionUrlService {

    public String findPromtoionUrl(StuffPromotion stuffPromotion, DeviceEnum device);

    public String findPromtoionUrl(ShopPromotion shopPromotion, DeviceEnum device);

    public String findPromtoionUrl(JsonArray dataList, DeviceEnum device, int size);

    public String findPromtoionUrl(ZwStuff zwStuff, DeviceEnum device);

}
