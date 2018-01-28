package com.qbao.aisr.app.service.stuffappeal.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.qbao.aisr.app.dto.StuffAppealDetailDto;
import com.qbao.aisr.app.dto.StuffAppealDto;
import com.qbao.aisr.app.model.StuffAppeal;
import com.qbao.aisr.app.model.TaokeDetail;
import com.qbao.aisr.app.repository.mybatis.dao.stuffappeal.StuffAppealDao;
import com.qbao.aisr.app.repository.mybatis.dao.user.TaokeDetailDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.image.IImageService;
import com.qbao.aisr.app.service.stuffappeal.IStuffAppealService;


/**
 * @author zhangjun
 * @createTime 17/5/24 上午11:54
 * $$LastChangedDate: 2017-03-11 20:41:57 +0800 (周六, 11 三月 2017) $$
 * $$LastChangedRevision: 150 $$
 * $$LastChangedBy: zhangjun $$
 */
@Service
public class StuffAppealServiceImpl implements IStuffAppealService {
    Logger logger = Logger.getLogger(StuffAppealServiceImpl.class);

    @Autowired
    StuffAppealDao stuffAppealDao;
    
    @Autowired
    TaokeDetailDao taokeDetailDao;
    
    @Autowired
    IImageService imageService;
    
	@Override
	@RedisCache(expire = 60*1,clazz =Boolean.class,cacheType = CacheType.OBJECT)
	public Boolean right(long userId) {
		int appeals = stuffAppealDao.countTodayStuffAppealsByUserId(userId);
		return appeals<10;
	}

	@Override
	public Map<String, String> upload(long userId, byte[] image,String imageType) {
//		return imageService.uploadImage(image, imageType);
		return null;
	}

	
	@Override
	public void submit(long userId, String orderId, String source, int device, String phoneBrand, String phoneType, String content,
			String reason, String imgUrl, String qq, String phone) {
		stuffAppealDao.saveStuffAppeal(userId, orderId, source, device, phoneBrand, phoneType, content, reason, imgUrl, qq, phone);
	}

	@Override
	@RedisCache(expire = 60*1,clazz =StuffAppealDto.class,cacheType = CacheType.LIST)
	public List<StuffAppealDto> list(long userId) {
		List<StuffAppeal> list = stuffAppealDao.findStuffAppealsByUserId(userId);
		List<StuffAppealDto> retList = Lists.newArrayList();
		for (Iterator<StuffAppeal> iterator = list.iterator(); iterator.hasNext();) {
			StuffAppeal stuffAppeal = (StuffAppeal) iterator.next();
			StuffAppealDto dto = new StuffAppealDto();
			dto.setAppealId(stuffAppeal.getAppealId());
			dto.setAppealStatus(stuffAppeal.getAppealStatus());
			dto.setAppealTime(stuffAppeal.getAppealTime());
			dto.setOrderId(stuffAppeal.getOrderId());
			dto.setRebateValue(stuffAppeal.getRebateValue());
			int stuffNum = 0;
			BigDecimal amount = new BigDecimal(0);
			dto.setItem(taokeDetailDao.queryTaokeDetailByOrderId(stuffAppeal.getOrderId()));
			for (TaokeDetail detail : dto.getItem()) {
				stuffNum += detail.getStuffNum();
				amount = amount.add(detail.getPrice().multiply(new BigDecimal(stuffNum)));
			}
			dto.setStuffNum(stuffNum);
			dto.setAmount(amount);
			retList.add(dto);
		}
		return retList;
	}

	@Override
	public void cancel(long appealId, long userId) {
		stuffAppealDao.cancel(appealId, userId);
	}

	@Override
	@RedisCache(expire = 60*1,clazz =StuffAppealDetailDto.class,cacheType = CacheType.OBJECT)
	public StuffAppealDetailDto detail(long appealId, long userId) {
		StuffAppeal appeal = stuffAppealDao.findStuffAppealByAppealId(appealId, userId);
		StuffAppealDetailDto dto = new StuffAppealDetailDto();
		dto.setAppealId(appeal.getAppealId());
		dto.setAppealStatus(appeal.getAppealStatus());
		dto.setAppealTime(appeal.getAppealTime());
		dto.setComment(appeal.getComment());
		dto.setContent(appeal.getContent());
		dto.setOrderId(appeal.getOrderId());
		dto.setPhoneType(appeal.getPhoneType());
		dto.setReason(appeal.getReason());
		dto.setSource(appeal.getSource());
		return dto;
	}

}
