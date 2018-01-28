package com.qbao.aisr.app.service.qbzy.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.aisr.app.common.util.BeanUtils;
import com.qbao.aisr.app.dto.QbaozyClassesDto;
import com.qbao.aisr.app.model.QbaoZyClasses;
import com.qbao.aisr.app.repository.mybatis.dao.qbzy.QbaoZyClassesDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.qbzy.IQbzyClassesService;

/**
 * @author zhangjun
 * @createTime 2017/6/11 16:13
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Service
public class QbzyClassesServiceImpl implements IQbzyClassesService {

    Logger logger  = LoggerFactory.getLogger(QbzyClassesServiceImpl.class);

    @Autowired
    QbaoZyClassesDao qbaoZyClassesDao;
    
	@Override
    @RedisCache(expire = 60 * 2, clazz = QbaozyClassesDto.class, cacheType = CacheType.OBJECT)
	public QbaozyClassesDto findById(Long id) {
		
		QbaoZyClasses classes = qbaoZyClassesDao.findById(id);
		QbaozyClassesDto classesDto = new QbaozyClassesDto();
		BeanUtils.copy(classes, classesDto);
		return classesDto;
	}
	
	@Override
	public void save(QbaoZyClasses classes){
		qbaoZyClassesDao.saveQbaoZyClasses(classes);
	}
	
	@Override
	public void delete(Long id){
		qbaoZyClassesDao.deleteQbaoZyClassesById(id);
	}
	
	@Override
	public void update(QbaoZyClasses classes){
		qbaoZyClassesDao.updateQbaoZyClasses(classes);
	}

}
