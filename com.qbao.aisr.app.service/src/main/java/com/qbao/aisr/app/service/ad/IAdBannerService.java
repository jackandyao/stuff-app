package com.qbao.aisr.app.service.ad;

import java.util.List;

import com.qbao.aisr.app.model.AdBanner;
import com.qbao.aisr.app.model.BannerFlashSaleStuffConf;
import com.qbao.aisr.app.model.BannerStuffConf;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-06-07 18:30:21 +0800 (Wed, 07 Jun 2017) $$
 * $$LastChangedRevision: 1116 $$
 * $$LastChangedBy: wangping $$
 */
public interface IAdBannerService {


    public List<AdBanner> findBannerByLocationId(int locationId, int limit);

    public List<BannerStuffConf> findBannerStuffConfByBannerId(Integer bannerId);

    public AdBanner findBannerById(Integer id);

    public List<BannerFlashSaleStuffConf> findBannerSaleStuffConfByBannerId(Integer bannerId);

}
