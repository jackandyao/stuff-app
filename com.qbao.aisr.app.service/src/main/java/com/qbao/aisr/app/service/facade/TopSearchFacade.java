package com.qbao.aisr.app.service.facade;

import com.qbao.aisr.app.dto.TopSearchKeyDto;
import com.qbao.aisr.app.dto.TopSearchStuffComplexDto;
import com.qbao.aisr.app.model.TopSearchKey;
import com.qbao.aisr.app.model.TopSearchStuffComplex;
import com.qbao.aisr.app.service.rebate.IStuffReBateService;
import com.qbao.aisr.app.service.top.ITopSearchKeyService;
import com.qbao.aisr.app.service.top.ITopSearchStuffService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xueming
 * @createTime 17/3/8 上午11:24
 * $$LastChangedDate: 2017-03-09 14:54:45 +0800 (周四, 09 三月 2017) $$
 * $$LastChangedRevision: 110 $$
 * $$LastChangedBy: xueming $$
 */
@Component
public class TopSearchFacade {

    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private ITopSearchStuffService topSearchStuffService;
    @Autowired
    private ITopSearchKeyService topSearchKeyService;
    @Autowired
    private IStuffReBateService stuffReBateService;
    //@RedisCache(expire = 60,clazz =TopSearchStuffComplexDto.class,cacheType = CacheType.LIST)
    public List<TopSearchStuffComplexDto> topSearchStuffComplex(Integer page, Integer size,int device) {
        List<TopSearchStuffComplexDto> responseResult = new LinkedList<TopSearchStuffComplexDto>();
        List<TopSearchStuffComplex> result = topSearchStuffService.topSearchStuffComplex(page, size,device);
        if (CollectionUtils.isNotEmpty(result)) {
            TopSearchStuffComplexDto.Builder builder = new TopSearchStuffComplexDto.Builder();
            for (TopSearchStuffComplex tss : result) {
                 responseResult.add(builder.withId(tss.getId())
                                           .withName(tss.getName())
                                           .withImgUrl(tss.getImgUrl())
                                           .withUrl(tss.getUrl())
                                           .withFinalPrice(tss.getFinalPrice())
                                           .withRebateValue(stuffReBateService.findStuffRebateValue(tss.getId()))
                                           .withSource(tss.getSource())
                                           .withOrderNum(tss.getCount())
                                           .build());
            }
        }
        logger.info("total get [" + (CollectionUtils.isEmpty(result) ? 0 : result.size()) + "] TopSearchStuff,page=[" + page + "], size=[" + size + "]");
        return responseResult;
    }
    public List<TopSearchKeyDto> topSearchKey(int limit) {
        List<TopSearchKeyDto> responseResult = new LinkedList<TopSearchKeyDto>();
        List<TopSearchKey> result = topSearchKeyService.topSearchKey(limit);
        if (CollectionUtils.isNotEmpty(result)) {
            TopSearchKeyDto.Builder builder = new TopSearchKeyDto.Builder();
            for (TopSearchKey tsk : result) {
                responseResult.add(builder.withKey(tsk.getKey())
                        .withUrl(tsk.getUrl())
                        .build());
            }
        }
        logger.info("total get [" + (CollectionUtils.isEmpty(result) ? 0 : result.size()) + "] TopSearchKey, limit=[" + limit + "]");
        return responseResult;
    }

}
