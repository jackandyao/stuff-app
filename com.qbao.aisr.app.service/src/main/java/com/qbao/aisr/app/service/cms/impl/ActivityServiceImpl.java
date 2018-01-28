package com.qbao.aisr.app.service.cms.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.util.BeanUtils;
import com.qbao.aisr.app.model.ActivityFloor;
import com.qbao.aisr.app.model.ActivityGoods;
import com.qbao.aisr.app.repository.mybatis.dao.cms.ActivityDAO;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.cms.ActivityService;
import com.qbao.aisr.app.service.rebate.IStuffReBateService;

@Service
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	private ActivityDAO activityDAO;
	@Autowired
	private IStuffReBateService stuffReBateService;
	
	@Override
	@RedisCache(expire = 300, clazz = Object.class, cacheType = CacheType.OBJECT)
	public Object home(Long activityId){
//		Long activityId = activityDAO.getCurrentTemplateId();
		List<ActivityFloor> list = activityDAO.getFloorItem(activityId);
		for(ActivityFloor af:list){
			List<ActivityGoods> glist = this.getActivityGoods(af.getId());
			af.setGoods(glist);
		}
		Map<Object, List> floorMap = BeanUtils.group(list, "floorId");
		return floorMap;
	}
	
	private Integer getPoint(Long stuffId){
		String rebate_s = stuffReBateService.findStuffRebateValue(stuffId);
		rebate_s = rebate_s.replace(Constant.FAN_JFB, "").replace(Constant.RMB, "").replace(Constant.FAN, "");
		if(rebate_s.isEmpty()){
			return null;
		}
		Integer rebate = null;
		try{
			rebate = Integer.parseInt(rebate_s);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rebate;
	}

	@Override
	public List<ActivityGoods> getActivityGoods(Long floorId) {
		List<ActivityGoods> glist = this.activityDAO.getFloorGoods(floorId);
		for(ActivityGoods ag:glist){
			ag.setPoint(this.getPoint(ag.getStuffId()));
		}
		return glist;
	}

}
