package com.qbao.aisr.app.service.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.qbao.aisr.app.dto.AdBrandDto;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.model.AdBrand;
import com.qbao.aisr.app.service.ad.IAdBrandService;

/**
 * @author zhangjun
 * @createTime 2017/6/14 17:38
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Component
public class AdBrandFacade {
    @Autowired
    IAdBrandService iAdBrandService;

    public List<AdBrandDto> list(int page,int size){
    	List<AdBrand> list = iAdBrandService.list(page, size);
    	List<AdBrandDto> result = Lists.newArrayList();
    	for (AdBrand adBrand : list) {
    		AdBrandDto dto = new AdBrandDto();
    		dto.setBrandId(adBrand.getId());
    		dto.setBrandName(adBrand.getName());
    		dto.setImgUrl(adBrand.getImgUrl());
    		result.add(dto);
		}
    	return result;
    }
    
    public List<StuffPromotionDto> detail(long brandId, int device,int page,int size){
    	return  iAdBrandService.detail(brandId,device,page,size);
    }
    
    public AdBrand findByBrandId(long brandId){
    	return iAdBrandService.findByBrandId(brandId);
    }

}
