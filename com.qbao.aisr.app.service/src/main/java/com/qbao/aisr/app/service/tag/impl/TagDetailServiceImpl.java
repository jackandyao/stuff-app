package com.qbao.aisr.app.service.tag.impl;

import com.qbao.aisr.app.model.TagDetail;
import com.qbao.aisr.app.repository.mybatis.dao.user.TagDetailDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.tag.ITagDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-27 15:27:31 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 549 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class TagDetailServiceImpl implements ITagDetailService {
    @Autowired
    private TagDetailDao tagDetailDao;

    @RedisCache(expire = 60 * 60, clazz = TagDetail.class, cacheType = CacheType.LIST)
    public List<TagDetail> findAll() {
        return tagDetailDao.findAll();
    }

    @RedisCache(expire = 60 * 60, clazz = TagDetail.class, cacheType = CacheType.LIST)
    public List<TagDetail> findByTagType(int tagTypeId) {
        return tagDetailDao.findByTagTypeId(tagTypeId);
    }

}
