package com.qbao.aisr.app.service.ad;

import java.util.List;

import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.model.AdBrand;

/**
 * @author zhangjun
 * @createTime 17/3/7 上午10:33
 * $$LastChangedDate: 2017-03-10 21:09:54 +0800 (Fri, 10 Mar 2017) $$
 * $$LastChangedRevision: 127 $$
 * $$LastChangedBy: zhangjun $$
 */
public interface IAdBrandService {

    List<AdBrand> list(int page, int size);
    
    AdBrand findByBrandId(long brandId);
    
    List<StuffPromotionDto> detail(long brandId, int device, int page, int size);

}
