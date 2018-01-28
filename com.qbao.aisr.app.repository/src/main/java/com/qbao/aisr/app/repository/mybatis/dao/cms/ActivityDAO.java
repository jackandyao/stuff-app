package com.qbao.aisr.app.repository.mybatis.dao.cms;

import java.util.List;

import com.qbao.aisr.app.model.ActivityFloor;
import com.qbao.aisr.app.model.ActivityGoods;

/**
 * 
 * @author wangjg
 *
 */
public interface ActivityDAO {

	public Long getCurrentTemplateId();

	public List<ActivityFloor> getFloorItem(Long templateId);
	
	public List <ActivityGoods> getFloorGoods(Long floorId);

}
