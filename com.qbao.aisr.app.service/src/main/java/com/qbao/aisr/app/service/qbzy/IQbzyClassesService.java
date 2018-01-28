package com.qbao.aisr.app.service.qbzy;

import com.qbao.aisr.app.dto.QbaozyClassesDto;
import com.qbao.aisr.app.model.QbaoZyClasses;

/**
 * @author zhangjun
 * @createTime 2017/6/11 16:00
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
public interface IQbzyClassesService {

    QbaozyClassesDto findById(Long id);

    void save(QbaoZyClasses classes);
	
	void delete(Long id);
	
	void update(QbaoZyClasses classes);
}
