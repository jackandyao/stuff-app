package com.qbao.aisr.app.repository.redis.dao;

import com.qbao.aisr.app.reids.UserStuffRecItems;
import com.qbao.aisr.app.repository.redis.common.BaseRedisClusterDao;
import org.springframework.stereotype.Service;

/**
 * @author wangping
 * @createTime 2017/3/21 上午11:28
 * $$LastChangedDate: 2017-04-25 14:17:42 +0800 (Tue, 25 Apr 2017) $$
 * $$LastChangedRevision: 706 $$
 * $$LastChangedBy: wangping $$
 */
@Service
public class UserStuffRecItemsDao extends BaseRedisClusterDao<UserStuffRecItems> {

    @Override
    public String generateKey(String userId) {
        return this.getClass().getSimpleName() + "_" + userId;
    }
}
