package com.qbao.aisr.app.service.ad.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.model.AdBrand;
import com.qbao.aisr.app.model.BrandStuffConf;
import com.qbao.aisr.app.repository.mybatis.dao.ad.AdBrandDao;
import com.qbao.aisr.app.repository.mybatis.dao.ad.BrandStuffConfDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.ad.IAdBrandService;
import com.qbao.aisr.app.service.stuff.IStuffPromotionService;

/**
 * @author zhangjun
 * @createTime 17/3/7 上午10:33
 * $$LastChangedDate: 2017-03-27 15:27:31 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 549 $$
 * $$LastChangedBy: zhangjun $$
 */
@Service
public class AdBrandServiceImpl implements IAdBrandService {
	
	Logger logger  = LoggerFactory.getLogger(AdBrandServiceImpl.class);
	
    @Autowired
    private AdBrandDao adBrandDao;
    
    @Autowired
    private BrandStuffConfDao brandStuffConfDao;
    
    @Autowired
    private IStuffPromotionService iStuffPromotionService;
    
	@Override
	@RedisCache(expire = 60 * 30, clazz = AdBrand.class, cacheType = CacheType.LIST)
	public List<AdBrand> list(int page, int size) {
		return adBrandDao.findList((page-1)*size, size);
	}
	
	@Override
    @RedisCache(expire = 60 * 30, clazz = StuffPromotionDto.class, cacheType = CacheType.LIST)
    public List<StuffPromotionDto> detail(long brandId, int device, int page, int size) {
		List<BrandStuffConf> confList = brandStuffConfDao.findBrandStuffConfByBrandId(brandId, (page-1)*size, page*size);
		List<StuffPromotionDto> result = Lists.newArrayList();
		if(CollectionUtils.isEmpty(confList)) return result;
		
		for (BrandStuffConf conf : confList) {
			Long stuffId = conf.getStuffId();
			if(stuffId==null) continue;
			StuffPromotionDto dto = iStuffPromotionService.findStuffPromotionInfo(stuffId, device);
			if(dto != null) result.add(dto);
		}
		return result;
    }

	@Override
	@RedisCache(expire = 60 * 30, clazz = AdBrand.class, cacheType = CacheType.OBJECT)
	public AdBrand findByBrandId(long brandId) {
		return adBrandDao.findById(brandId);
	}
	
}
