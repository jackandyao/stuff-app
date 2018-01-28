package com.qbao.aisr.app.service.stuffrecommend.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.page.ListPage;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.common.page.PageManager;
import com.qbao.aisr.app.common.util.BeanUtils;
import com.qbao.aisr.app.dto.search.ZwStuffDetailDto;
import com.qbao.aisr.app.dto.search.ZwStuffOnedayDto;
import com.qbao.aisr.app.model.StuffPromotion;
import com.qbao.aisr.app.model.StuffRecommend;
import com.qbao.aisr.app.reids.StuffItem;
import com.qbao.aisr.app.reids.UserStuffRecItems;
import com.qbao.aisr.app.repository.mybatis.dao.shop.StuffPromotionDao;
import com.qbao.aisr.app.repository.mybatis.dao.stuffrecommend.RecStuffDao;
import com.qbao.aisr.app.repository.mybatis.dao.stuffrecommend.StuffRecommendDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.repository.redis.dao.UserStuffRecItemsDao;
import com.qbao.aisr.app.service.dislikestuff.IUserDislikeStuffService;
import com.qbao.aisr.app.service.search.ISearchStuffService;
import com.qbao.aisr.app.service.stuff.IStuffPromotionService;
import com.qbao.aisr.app.service.stuffrecommend.IStuffRecommendService;

/**
 * @author xueming
 * @createTime 17/3/6 下午3:33
 * $$LastChangedDate: 2017-07-19 14:31:59 +0800 (Wed, 19 Jul 2017) $$
 * $$LastChangedRevision: 1457 $$
 * $$LastChangedBy: zhangjun $$
 */

@Service
public class StuffRecommendServiceImpl implements IStuffRecommendService {

    private Logger logger = Logger.getLogger(StuffRecommendServiceImpl.class);
    @Autowired
    private StuffRecommendDao stuffRecommendDao; //离线推荐结果

    @Autowired
    private UserStuffRecItemsDao userStuffRecItemsDao; // 实时推荐结果数据

    @Autowired
    private StuffPromotionDao stuffPromotionDao;

    @Autowired
    private IUserDislikeStuffService userDislikeService;

    @Autowired
    private IStuffPromotionService stuffPromotionService;
    
    @Autowired
    private RecStuffDao recStuffDao;
    @Autowired
    private ISearchStuffService iSearchStuffService;

    public List<StuffPromotion> stuffRecommendHomePage(Long userId, Integer page, Integer size) {
        logger.info("Start Home page recommend userId=[" + userId + "], page=[" + page + "], size=[" + size + "] ...");
        PageManager pageManager = new PageManager(page, size);

        Set<Long> realTimeRecIds = fetchRealTimeRecommend(userId);
        logger.info("fetch userId=[" + userId + "] real time recommend stuff [" + realTimeRecIds.size() + " ] ids. and value : " + realTimeRecIds);

        Set<Long> offTimeRecIds = fetchOffTimeRecommend(userId);
        logger.info("fetch userId=[" + userId + "] off time recommend stuff [" + offTimeRecIds.size() + " ] ids. and value : " + offTimeRecIds);

        Set<Long> zeroOffTimeRecIds = new HashSet<>();
        if (Constant.DEFAULT_USER_ID != userId) {
            zeroOffTimeRecIds = fetchOffTimeRecommend(Constant.DEFAULT_USER_ID);
            logger.info("fetch userId=[" + Constant.DEFAULT_USER_ID + "] default recommend stuff [" + zeroOffTimeRecIds.size() + " ] ids. and value : " + zeroOffTimeRecIds);
        }

        List<Long> disLikeIds = userDislikeService.fetchDisLikeStuffIds(userId);
        logger.info("fetch userId=[" + userId + "] dislike stuff [" + disLikeIds.size() + " ] ids. and value : " + disLikeIds);

        List<Long> shufferIds = shuffer(realTimeRecIds, offTimeRecIds, zeroOffTimeRecIds);
        logger.info("userId=[" + userId + "]:  After shuffer operation the recommend stuff ids :" + shufferIds);

        List<Long> ids = ListUtils.subtract(shufferIds, disLikeIds);
        logger.info("userId=[" + userId + "]:  After remove the dis like stuff ids  the ids :" + ids);

        List<StuffPromotion> avaliableStuff = filterOfflineStuff(ids);

        return ListPage.page(page, size, avaliableStuff);
    }

    private List<StuffPromotion> filterOfflineStuff(List<Long> ids) {
        List<StuffPromotion> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(ids)) {
            for (Long id : ids) {
                StuffPromotion stuffPromotion = stuffPromotionService.findStuffPromotion(id);
                if (null != stuffPromotion) {
                    result.add(stuffPromotion);
                } else {
                    logger.info("stuff id =[" + id + "]  is off line");
                }
            }
        }
        return result;
    }

    //@RedisCache(expire = 60*30,clazz =StuffPromotion.class,cacheType = CacheType.LIST)
    private List<StuffPromotion> fetchStuffPromotion(List<Long> ids) {
        List<StuffPromotion> result = new ArrayList<>();
        //result = stuffPromotionDao.findStuffPromotionByIdsList(ids);
        if (ids != null && ids.size() > 0) {
            for (Long id : ids) {
                StuffPromotion stuffPromotion = fetchStuffPromotion(id);
                result.add(stuffPromotion);
            }
        }
        return result;
    }

    @RedisCache(expire = 60 * 2, clazz = StuffPromotion.class, cacheType = CacheType.OBJECT)
    private StuffPromotion fetchStuffPromotion(Long id) {
        StuffPromotion result = new StuffPromotion();
        result = stuffPromotionDao.findStuffPromotionById(id);
        return result;
    }

    private Set<Long> fetchRealTimeRecommend(Long userId) {
        Set<Long> ids = new TreeSet<>();
        String key = userStuffRecItemsDao.generateKey(String.valueOf(userId));
        UserStuffRecItems rec = userStuffRecItemsDao.get(key, UserStuffRecItems.class);
        if (null != rec && CollectionUtils.isNotEmpty(rec.getGoodsItems())) {
            List<StuffItem> items = rec.getGoodsItems();
            for (StuffItem goodsItem : items) {
                ids.add(goodsItem.getStuffId());
            }
        }
        return ids;
    }

    //TODO结果混排
    public static List<Long> shuffer(Set<Long> ids1, Set<Long> ids2, Set<Long> ids3) {
        List<Long> result = new LinkedList<>();
        List<Long> baseIds = new LinkedList<>();
        List<Long> otherIds = new LinkedList<>();
        if (null != ids1) {
            baseIds.addAll(ids1);
        }

        if (null != ids2) {
            for (Long id : ids2) {
                if (baseIds.contains(id)) {
                    continue;
                }
                otherIds.add(id);
            }
        }

        if (null != ids3) {
            for (Long id : ids3) {
                if (baseIds.contains(id) || otherIds.contains(id)) {
                    continue;
                }
                otherIds.add(id);
            }
        }
        if (baseIds.size() < otherIds.size()) {
            int length = baseIds.size();
            for (int i = 0; i < length; i++) {
                result.add(baseIds.get(i));
                result.add(otherIds.get(i));
            }
            result.addAll(otherIds.subList(baseIds.size(), otherIds.size()));
        } else {
            int length = otherIds.size();
            for (int i = 0; i < length; i++) {
                result.add(baseIds.get(i));
                result.add(otherIds.get(i));
            }
            result.addAll(baseIds.subList(otherIds.size(), baseIds.size()));
        }

        return result;
    }

    @RedisCache(expire = 60 * 60, clazz = Long.class, cacheType = CacheType.SET)
    private Set<Long> fetchOffTimeRecommend(Long userId) {
        Set<Long> set = new TreeSet<Long>();
        StuffRecommend stuffRecommend = stuffRecommendDao.stuffRecommendHomePage(userId);
        if (stuffRecommend != null && stuffRecommend.getStuffIds() != null) {
            String[] ids = stuffRecommend.getStuffIds().split(",");
            for (String id : ids) {
                id = StringUtils.trimToEmpty(id);
                if (StringUtils.isNotBlank(id) && NumberUtils.isDigits(id)) {
                    set.add(Long.parseLong(id));
                }
            }
        }
        return set;
    }

    public List<StuffPromotion> stuffRecommend(Long userId, Integer page, Integer size) {
        PageManager pageManager = new PageManager(page, size);
        List<Long> offlineRecIds = fetchStuffRecommend(userId);
        logger.info("fetch userId=[" + userId + "] off time recommend stuff [" + offlineRecIds.size() + " ] ids. and value : " + offlineRecIds);

        List<Long> disLikeIds = userDislikeService.fetchDisLikeStuffIds(userId);
        logger.info("fetch userId=[" + userId + "] dislike stuff [" + disLikeIds.size() + " ] ids. and value : " + disLikeIds);

        List<Long> ids = ListUtils.subtract(offlineRecIds, disLikeIds);
        logger.info("userId=[" + userId + "]:  After remove the dis like stuff ids  the ids :" + ids);

        List<StuffPromotion> avaliableStuff = filterOfflineStuff(ids);
        return ListPage.page(page, size, avaliableStuff);

    }
    
    @RedisCache(expire = 60 * 60, clazz = Long.class, cacheType = CacheType.LIST)
    private List<Long> fetchStuffRecommend(Long userId) {
        List<Long> list = Lists.newArrayList();
        StuffRecommend stuffRecommend = stuffRecommendDao.stuffRecommend(userId);
        if (null == stuffRecommend) {
            stuffRecommend = stuffRecommendDao.stuffRecommend(0L);//默认推荐结果L
            logger.info("user id =[" + userId + "], 搜索推荐无离线推荐结果, 使用默认推荐结果: ");
        }
        if (stuffRecommend != null && stuffRecommend.getStuffIds() != null) {
            String[] ids = stuffRecommend.getStuffIds().split(",");
            for (String id : ids) {
                if (id != null) {
                    list.add(Long.parseLong(id));
                }
            }
        }
        return list;
    }

    @RedisCache(expire = 60 * 60, clazz = Long.class, cacheType = CacheType.LIST)
    private List<Long> fetchOffLineRecommend(Long userId, String column) {
        List<Long> list = Lists.newArrayList();
        StuffRecommend stuffRecommend = stuffRecommendDao.stuffRecommendHomePage(userId);
        if (null == stuffRecommend) {
            stuffRecommend = stuffRecommendDao.stuffRecommendHomePage(0L);//默认推荐结果L
            logger.info("user id =[" + userId + ", " + column + "], 首页推荐无离线推荐结果, 使用默认推荐结果: ");
        }
        if(stuffRecommend==null) return list;
        String idStr = null;
        switch (column) {
		case "stuff":
			idStr = stuffRecommend.getStuffIds();
			break;
		case "buy":
			idStr = stuffRecommend.getBuyIds();
			break;
		case "view":
			idStr = stuffRecommend.getViewIds();
			break;
		}
        if(StringUtils.isEmpty(idStr)) return list;
        String[] ids = idStr.split(",");
        Set<String> filter = Sets.newHashSet();
        for (String id : ids) {
            if (id != null && !filter.contains(id)) {
                list.add(Long.parseLong(id));
                filter.add(id);
            }
        }
        return list;
    }

    @Override
    public List<Long> fetchBuyRecommendIds(Long userId) {
    	return fetchOffLineRecommend(userId, "buy");
    }
    
    @Override
    public List<Long> fetchViewRecommendIds(Long userId) {
    	List<Long> buyList = this.fetchBuyRecommendIds(userId);
    	List<Long> viewList = fetchOffLineRecommend(userId, "view");
    	viewList.removeAll(buyList);
    	return viewList;
    }
    @Override
    public List<Long> fetchRecommendIds(Long userId) {
    	return fetchOffLineRecommend(userId, "stuff");
    }
    
    public static void main(String[] args){
   //     Set<Long> id1s = Sets.newHashSet(0L,1L,2L,3L,4L,5L,6L,7L,8L,9L,10L);
        Set<Long> id1s = Sets.newHashSet();
       //Set<Long> id2s = Sets.newHashSet(1L,2L,3L,4L,5L,6L,7L,8L,9L,10L);
       Set<Long> id2s = Sets.newHashSet();
        Set<Long> id3s = Sets.newHashSet(1L,2L,3L,4L,5L,6L,7L,8L,9L,10L);
      //  Set<Long> id3s = Sets.newHashSet();

        List<Long> lists = shuffer(id1s, id2s, id3s);
        System.out.println(lists.size() + ": " + lists );
        System.out.println( Sets.union(Sets.union(id1s,id2s),id3s).size());

    }
    
    
	@Override
	public Page<ZwStuffDetailDto> zwSimilar(long stuffId, int page, int size, int device) {
		String recStuff = recStuffDao.findStuffLikeByStuffId(stuffId);
		if(recStuff == null || recStuff.length()==0) return this.zwHot(page, size, device);
		
		String[] stuffIds = recStuff.split("\\D");
		List<ZwStuffDetailDto> list = Lists.newArrayList();
		for (String sid : stuffIds) {
			ZwStuffDetailDto zwStuffDetailDto = iSearchStuffService.searchStuffById(0, Long.valueOf(sid), device);
			if(zwStuffDetailDto==null || zwStuffDetailDto.getId()==null) continue;
			list.add(zwStuffDetailDto);
		}
		return returnPage(list, page, size);
	}


	@Override
	public Page<ZwStuffDetailDto> zwRelated(long stuffId, int page, int size, int device) {
		String recStuff = recStuffDao.findStuffRelateByStuffId(stuffId);
		if(recStuff == null || recStuff.length()==0) return this.zwHot(page, size, device);
		
		String[] stuffIds = recStuff.split("\\D");
		List<ZwStuffDetailDto> list = Lists.newArrayList();
		for (String sid : stuffIds) {
			ZwStuffDetailDto zwStuffDetailDto = iSearchStuffService.searchStuffById(0, Long.valueOf(sid), device);
			if(zwStuffDetailDto==null || zwStuffDetailDto.getId()==null) continue;
			list.add(zwStuffDetailDto);
		}
		return returnPage(list, page, size);
	}


	@Override
	public Page<ZwStuffDetailDto> zwHot(int page, int size, int device) {
		String stuffIds = recStuffDao.hot();
		List<ZwStuffDetailDto> list = Lists.newArrayList();
		if(stuffIds == null || stuffIds.length()==0) return new Page<ZwStuffDetailDto>(list.size(),page,size,list);
		
		String[] sids = stuffIds.split("\\D");
		for (String sid : sids) {
			ZwStuffDetailDto zwStuffDetailDto = iSearchStuffService.searchStuffById(0, Long.valueOf(sid), device);
			if(zwStuffDetailDto==null || zwStuffDetailDto.getId()==null) continue;
			list.add(zwStuffDetailDto);
		}
		return returnPage(list, page, size);
	}
	
	@Override
	public Page<ZwStuffDetailDto> zwGuess(long userId, int page, int size, int device) {
		String stuffIds = recStuffDao.guess(userId);
		if(stuffIds == null || stuffIds.length()==0) return this.zwHot(page, size, device);
		
		String[] sids = stuffIds.split("\\D");
		List<ZwStuffDetailDto> list = Lists.newArrayList();
		for (String sid : sids) {
			ZwStuffDetailDto zwStuffDetailDto = iSearchStuffService.searchStuffById(0, Long.valueOf(sid), device);
			if(zwStuffDetailDto==null || zwStuffDetailDto.getId()==null) continue;
			list.add(zwStuffDetailDto);
		}
		return returnPage(list, page, size);
	}
	
	
	@Override
	public Page<ZwStuffOnedayDto> oneday(long userId, String userProfile, int page, int size, int device) {
		String stuffIds = recStuffDao.findOnedayStuffByStuffId(userId);
		if(StringUtils.isEmpty(stuffIds)) stuffIds = recStuffDao.findOnedayStuffByStuffId(0);
		List<ZwStuffOnedayDto> list = Lists.newArrayList();
		if(stuffIds == null || stuffIds.length()==0) return new Page<ZwStuffOnedayDto>(list.size(),page,size,list);
		
		String[] sids = stuffIds.split("\\D");
		for (String sid : sids) {
			ZwStuffDetailDto zwStuffDetailDto = iSearchStuffService.searchStuffById(0, Long.valueOf(sid), device);
			if(zwStuffDetailDto==null || zwStuffDetailDto.getId()==null) continue;
			ZwStuffOnedayDto onedayDto = new ZwStuffOnedayDto();
			BeanUtils.copy(zwStuffDetailDto, onedayDto);
			onedayDto.setCoupon(zwStuffDetailDto.getCoupon());
			onedayDto.setId(zwStuffDetailDto.getId());
			onedayDto.setOrderNum(zwStuffDetailDto.getSaleCount());
			onedayDto.setLinkUrl(zwStuffDetailDto.getLinkUrl());
			list.add(onedayDto);
		}
		return returnPage(list, page, size);
	}
	
	private <T>Page<T> returnPage(List<T> list, int page, int size){
		List<T> retList = Lists.newArrayList();
		if(page<1) return new Page<T>(list.size(),page,size,retList);
		if(page>(list.size()-1)/size+1) return new Page<T>(list.size(),page,size,retList);
		int fromIndex = (page-1)*size;
		int toIndex = (list.size()<page*size)?list.size():page*size;
		retList = list.subList(fromIndex, toIndex);
		return new Page<T>(list.size(),page,size,retList);
	}

}
