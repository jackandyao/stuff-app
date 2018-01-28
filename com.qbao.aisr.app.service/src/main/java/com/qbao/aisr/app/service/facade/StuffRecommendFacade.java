package com.qbao.aisr.app.service.facade;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qbao.aisr.app.common.enums.DeviceEnum;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.dto.StuffRecommendDto;
import com.qbao.aisr.app.model.StuffPromotion;
import com.qbao.aisr.app.service.promotion.IPromotionUrlService;
import com.qbao.aisr.app.service.rebate.IStuffReBateService;
import com.qbao.aisr.app.service.stuff.IStuffPromotionService;
import com.qbao.aisr.app.service.stuffrecommend.IStuffRecommendService;

/**
 * @author xueming
 * @createTime 17/3/8 上午11:24 $$LastChangedDate: 2017-03-09 14:54:45 +0800 (周四,
 *             09 三月 2017) $$ $$LastChangedRevision: 110 $$ $$LastChangedBy:
 *             xueming $$
 */
@Component
public class StuffRecommendFacade {

	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private IStuffRecommendService service;
	@Autowired
	private IStuffReBateService stuffReBateService;

	@Autowired
	private IPromotionUrlService promotionService;

	@Autowired
	private IStuffPromotionService stuffPromotionService;

	public List<StuffRecommendDto> stuffRecommendHomePage(Long userId, Integer page, Integer size, int device) {
		if (userId == null) {
			userId = 0l;
		}
		List<StuffRecommendDto> responseResult = new LinkedList<StuffRecommendDto>();
		// redis处理
		/*
		 * BaseRedis baseRedis = new BaseRedis(); Object redisObj =
		 * baseRedis.getCache("key",StuffRecommendDto.class,CacheType.LIST);
		 */
		List<StuffPromotion> result = service.stuffRecommendHomePage(userId, page, size);
		if (CollectionUtils.isNotEmpty(result)) {
			StuffRecommendDto.Builder builder = new StuffRecommendDto.Builder();
			for (StuffPromotion tss : result) {
				responseResult.add(builder.withStuffId(tss.getId()).withName(tss.getName())
						.withPrice(tss.getFinalPrice()).withImgUrl(tss.getImgUrl())
						.withLinkUrl(promotionService.findPromtoionUrl(tss, DeviceEnum.asDeviceEnum(device)))
						.withSaleCount(tss.getOrderNum())
						.withRebateValue(stuffReBateService.findStuffRebateValue(tss.getId()))
						.withSource(tss.getSource()).build());
			}
		}
		logger.info("total get [" + (CollectionUtils.isEmpty(result) ? 0 : result.size())
				+ "] StuffRecommendHomePage,userId=[" + userId + "]page=[" + page + "], size=[" + size + "]");
		return responseResult;
	}

	// @RedisCache(expire = 60,clazz =StuffRecommendDto.class,cacheType =
	// CacheType.LIST)
	public List<StuffRecommendDto> stuffRecommend(Long userId, Integer page, Integer size, int device) {
		if (userId == null) {
			userId = 0l;
		}
		List<StuffRecommendDto> responseResult = new LinkedList<StuffRecommendDto>();
		List<StuffPromotion> result = service.stuffRecommend(userId, page, size);
		if (CollectionUtils.isNotEmpty(result)) {
			StuffRecommendDto.Builder builder = new StuffRecommendDto.Builder();
			for (StuffPromotion tss : result) {
				responseResult.add(builder.withStuffId(tss.getId()).withName(tss.getName())
						.withPrice(tss.getFinalPrice()).withImgUrl(tss.getImgUrl())
						.withLinkUrl(promotionService.findPromtoionUrl(tss, DeviceEnum.asDeviceEnum(device)))
						.withSaleCount(tss.getOrderNum())
						.withRebateValue(stuffReBateService.findStuffRebateValue(tss.getId()))
						.withSource(tss.getSource()).build());
			}
		}
		logger.info("total get [" + (CollectionUtils.isEmpty(result) ? 0 : result.size()) + "] StuffRecommend,userId=["
				+ userId + "]page=[" + page + "], size=[" + size + "]");
		return responseResult;
	}

	private List<StuffPromotionDto> stuffRecommendType(Long userId, String type, int device) {
		List<StuffPromotionDto> list = Lists.newArrayList();
		List<Long> ids = null;
		switch (type) {
		case "buy":
			ids = service.fetchBuyRecommendIds(userId);
			break;
		case "view":
			ids = service.fetchViewRecommendIds(userId);
			break;
		case "stuff":
			ids = service.fetchRecommendIds(userId);
			break;
		}
		if (ids == null)
			return list;
		for (Long stuffId : ids) {
			StuffPromotionDto dto = stuffPromotionService.findStuffPromotionInfo(stuffId, device);
			if (dto != null) {
				dto.setRebateValue("");//TODO 签到抓不到订单，临时取消返券
				list.add(dto);
			}
		}
		return list;
	}

	public List<Map<String, Object>> todaylist(Long userId, int device) {
		List<Map<String, Object>> result = Lists.newArrayList();
		List<StuffPromotionDto> buyList = this.stuffRecommendType(userId, "buy", device);
		List<StuffPromotionDto> viewList = this.stuffRecommendType(userId, "view", device);
		List<StuffPromotionDto> defaultList = this.stuffRecommendType(0L, "stuff", device);
		if (CollectionUtils.isEmpty(buyList) && CollectionUtils.isEmpty(viewList)) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("level", 1);
			map.put("title", "");
			map.put("stuffs", Lists.newArrayList());
			result.add(map);
			map = Maps.newHashMap();
			map.put("level", 2);
			map.put("title", "");
			map.put("stuffs", Lists.newArrayList());
			result.add(map);
			map = Maps.newHashMap();
			map.put("level", 3);
			map.put("title", "您可能购买的的相关推荐");
			map.put("stuffs", defaultList.size() > 18 ? defaultList.subList(0, 18) : defaultList);
			result.add(map);
			return result;
		}

		List<StuffPromotionDto> buyRec = buyList.size() > 6 ? buyList.subList(0, 6) : buyList;
		List<StuffPromotionDto> viewRec = viewList.size() > 6 ? viewList.subList(0, 6) : viewList;

		List<StuffPromotionDto> hotRec = Lists.newArrayList();
		if (viewRec.size() > 6)
			hotRec.addAll(viewRec.subList(6, viewRec.size()));
		if (buyRec.size() > 6)
			hotRec.addAll(buyRec.subList(6, buyRec.size()));

		if (buyRec.size() < 6 || viewRec.size() < 6 || hotRec.size() < 18) {
			Set<Long> filter = Sets.newHashSet();
			for (StuffPromotionDto dto : buyRec) {
				filter.add(dto.getId());
			}
			for (StuffPromotionDto dto : viewRec) {
				filter.add(dto.getId());
			}
			for (StuffPromotionDto dto : hotRec) {
				filter.add(dto.getId());
			}
			Iterator<StuffPromotionDto> it = defaultList.iterator();
			while (it.hasNext()) {
				StuffPromotionDto dto = it.next();
				if (filter.contains(dto.getId())) {
					it.remove();
				}
			}
			int buySize = buyRec.size();
			int viewSize = viewRec.size();
			int hotSize = hotRec.size();
			int defSize = defaultList.size();
			if (buySize < 6 && defSize > 0)
				buyRec.addAll((defSize > 6 - buySize) ? defaultList.subList(0, 6 - buySize) : defaultList);
			if (viewSize < 6 && defSize > 6 - buySize)
				viewRec.addAll(
						(defSize > 12 - buySize - viewSize) ? defaultList.subList(6 - buySize, 12 - buySize - viewSize)
								: defaultList.subList(6 - buySize, defSize));
			if (hotSize < 18 && defSize > 12 - buySize - viewSize)
				hotRec.addAll((defSize > 30 - buySize - viewSize - hotSize)
						? defaultList.subList(12 - buySize - viewSize, 30 - buySize - viewSize - hotSize)
						: defaultList.subList(12 - buySize - viewSize, defSize));
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("level", 1);
		map.put("title", "您近日购买的相关推荐");
		map.put("stuffs", buyRec);
		result.add(map);
		map = Maps.newHashMap();
		map.put("level", 2);
		map.put("title", "您近日浏览的商品推荐");
		map.put("stuffs", viewRec);
		result.add(map);
		map = Maps.newHashMap();
		map.put("level", 3);
		map.put("title", "您可能购买的的相关推荐");
		map.put("stuffs", hotRec);
		result.add(map);

		return result;
	}

}
