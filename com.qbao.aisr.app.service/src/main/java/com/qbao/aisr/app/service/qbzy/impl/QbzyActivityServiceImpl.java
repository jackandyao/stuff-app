package com.qbao.aisr.app.service.qbzy.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qbao.aisr.app.common.http.HttpClientUtil;
import com.qbao.aisr.app.common.http.common.HttpConfig;
import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.common.util.BeanUtils;
import com.qbao.aisr.app.dto.QbaozyActivityDto;
import com.qbao.aisr.app.dto.QbaozyClassesDto;
import com.qbao.aisr.app.model.QbaoZyActivity;
import com.qbao.aisr.app.model.QbaoZyClasses;
import com.qbao.aisr.app.model.search.ZwStuff;
import com.qbao.aisr.app.repository.mybatis.dao.qbzy.QbaoZyActivityDao;
import com.qbao.aisr.app.repository.mybatis.dao.qbzy.QbaoZyClassesDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.qbzy.IQbzyActivityService;

/**
 * @author zhangjun
 * @createTime 2017/6/11 16:13
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Service
public class QbzyActivityServiceImpl implements IQbzyActivityService {

    Logger logger  = LoggerFactory.getLogger(QbzyActivityServiceImpl.class);

    @Value("${search.domain}")
    private String search_domain;
    
    @Autowired
    QbaoZyActivityDao qbaoZyActivityDao;

    @Autowired
    QbaoZyClassesDao qbaoZyClassesDao;
    
	

	@Override
    @RedisCache(expire = 60 * 2, clazz = QbaozyActivityDto.class, cacheType = CacheType.OBJECT)
	public QbaozyActivityDto findById(Long id) {
		
		QbaoZyActivity activity = qbaoZyActivityDao.findById(id);
		return transformActivityToDto(activity);
	}
	
	private QbaozyActivityDto transformActivityToDto(QbaoZyActivity activity){
		
		QbaozyActivityDto activityDto = new QbaozyActivityDto();
		BeanUtils.copy(activity, activityDto);
		Long activityId = activity.getId();
		List<QbaoZyClasses> classList = qbaoZyClassesDao.findQbaoZyClassesListByActivityId(activityId);
		List<QbaozyClassesDto> classDtoList = Lists.newArrayList();
		for (QbaoZyClasses classes : classList) {
			QbaozyClassesDto classesDto = new QbaozyClassesDto();
			BeanUtils.copy(classes, classesDto);
			classDtoList.add(classesDto);
		}
		activityDto.setClassesList(classDtoList);
		return activityDto;
	}
	
	@Override
    @RedisCache(expire = 60 * 10, clazz = ZwStuff.class, cacheType = CacheType.PAGE)
    public Page<ZwStuff> goodsList(long cid, int page, int size) throws HttpProcessException {
        String url = this.createQbzyClassesUrl(cid,page,size);
        String result = HttpClientUtil.get(HttpConfig.custom().url(url));
        JSONObject reponse = JSON.parseObject(result);
        String returnCode = reponse.getString("errorCode");
        if(returnCode.equals("0")) {
            JSONObject dataObject = reponse.getJSONObject("data");
            int total = dataObject.getInteger("totalCount");
            JSONArray array = dataObject.getJSONArray("dataList");
            List<ZwStuff> list = JSON.parseArray(array.toJSONString(), ZwStuff.class);
            return new Page<ZwStuff>(total, page, size, list);
        }else{
            logger.error("查询出错！ result:"+result);
            throw new RuntimeException("查询出错!");
        }
    }
	
	private String createQbzyClassesUrl(long cid,int page,int size){
        if(cid==0){
           throw new RuntimeException("cid must not be 0");
        }else{
            QbaoZyClasses dir = qbaoZyClassesDao.findById(cid);
            if(dir == null){
                throw new RuntimeException("没有找到对应的分类！cid=" + cid);
            }else{
                String url = dir.getUrl();
                if(url.contains("?")){
                    url= search_domain+url+"&p="+page+"&size="+size;
                }else{
                    url= search_domain+url+"?p="+page+"&size="+size;
                }
                return url;
            }
        }
    }
}
