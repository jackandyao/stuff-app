package com.qbao.aisr.app.service.facade;

import com.qbao.aisr.app.dto.StuffRecommendDto;
import com.qbao.aisr.app.model.StuffPromotion;
import com.qbao.aisr.app.service.dislikestuff.IUserDislikeStuffService;
import com.qbao.aisr.app.service.rebate.IStuffReBateService;
import com.qbao.aisr.app.service.stuffrecommend.IStuffRecommendService;
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
public class UserDislikeStuffFacade {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private IUserDislikeStuffService service;


    public Boolean updateUserDislikeStuff(Long userId, Long stuffId) {
        if(userId==null){
            userId = 0l;
        }
        Boolean result = service.updateUserDislikeStuff(userId, stuffId);

        logger.info("updateUserDislikeStuff,userId=["+userId+"]stuffId=[" + stuffId + "]");
        return result;
    }


}
