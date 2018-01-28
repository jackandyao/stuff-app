package com.qbao.aisr.app.web.api;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qbao.aisr.app.model.ActivityGoods;
import com.qbao.aisr.app.service.cms.ActivityService;
import com.qbao.aisr.app.web.controller.base.BaseController;

/**
 * 合伙人接口
 * @author wangjg
 */
@RestController
@RequestMapping("/cms/activity")
public class ActivityController extends BaseController{
    private final Logger logger = LoggerFactory.getLogger(ActivityController.class);
	@Autowired
	private ActivityService activityTemplateService;
	
	/**
	 * 合伙人首页信息
	 */
	@RequestMapping("/index")
	public Object home(Long activityId){
		Object data = activityTemplateService.home(activityId);
		return this.getSuccessResult(data);
	}
	
	/**
	 * 活动商品
	 */
	@RequestMapping("/goods")
	public Object goods(Long floorId){
		List<ActivityGoods> data = activityTemplateService.getActivityGoods(floorId);
		return this.getSuccessResult(data);
	}

}
