package com.qbao.aisr.app.service.cms;

import java.util.List;

import com.qbao.aisr.app.model.ActivityGoods;

public interface ActivityService {

	Object home(Long activityId);

	List<ActivityGoods> getActivityGoods(Long floorId);

}
