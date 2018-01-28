package com.qbao.aisr.app.service.pai.impl;

import java.util.ArrayList;
import java.util.List;

import com.qbao.aisr.app.model.StuffHeadline;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.aisr.app.common.page.PageManager;
import com.qbao.aisr.app.dto.TopPaiImgDto;
import com.qbao.aisr.app.dto.UserStuffPaiDto;
import com.qbao.aisr.app.model.TopPaiImg;
import com.qbao.aisr.app.model.UserStuffPai;
import com.qbao.aisr.app.repository.mybatis.dao.pai.TopPaiImgDao;
import com.qbao.aisr.app.repository.mybatis.dao.pai.UserStuffPaiDao;
import com.qbao.aisr.app.service.pai.ITopPaiImgService;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-28 14:36:05 +0800 (Tue, 28 Mar 2017) $$
 * $$LastChangedRevision: 562 $$
 * $$LastChangedBy: liaijun $$
 */
@Service
public class TopPaiImgServiceImpl implements ITopPaiImgService {
    @Autowired
    private TopPaiImgDao topPaiImgDao;
    @Autowired
    private UserStuffPaiDao userStuffPaiDao;

    @RedisCache(expire = 60 * 10, clazz = TopPaiImgDto.class, cacheType = CacheType.LIST)
    public List<TopPaiImgDto> findTopPaiList(int page, int pageSize) {
        PageManager pageManager = new PageManager(page, pageSize);
        List<TopPaiImg> topPaiImgList = topPaiImgDao.findTopPaiList(pageManager.getPage(), pageManager.getPageSize());
        List<TopPaiImgDto> dtoList = new ArrayList<TopPaiImgDto>();
        if (topPaiImgList == null) {
            return dtoList;
        }
        for (TopPaiImg img : topPaiImgList) {
            TopPaiImgDto dto = new TopPaiImgDto();
            BeanUtils.copyProperties(img, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @RedisCache(expire = 60 * 10, clazz = Long.class, cacheType = CacheType.OBJECT)
    public Long countTopPaiImg() {
        return topPaiImgDao.countTopPaiImg();
    }

    @RedisCache(expire = 60 * 10, clazz = UserStuffPaiDto.class, cacheType = CacheType.LIST)
    public List<UserStuffPaiDto> findUserPaiList(Long userId, int page, int pageSize) {
        PageManager pageManager = new PageManager(page, pageSize);
        List<UserStuffPai> topUserImgList = userStuffPaiDao.findUserPaiList(userId, pageManager.getPage(), pageManager.getPageSize());
        List<UserStuffPaiDto> dtoList = new ArrayList<UserStuffPaiDto>();
        if (topUserImgList == null) {
            return dtoList;
        }
        for (UserStuffPai img : topUserImgList) {
            UserStuffPaiDto dto = new UserStuffPaiDto();
            BeanUtils.copyProperties(img, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @RedisCache(expire = 60 * 10, clazz = Long.class, cacheType = CacheType.OBJECT)
    public Long countUserPai(Long userId) {
        return userStuffPaiDao.countUserPaiImg(userId);
    }
}
