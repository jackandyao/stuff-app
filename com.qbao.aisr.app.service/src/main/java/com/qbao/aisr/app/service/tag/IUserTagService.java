package com.qbao.aisr.app.service.tag;

import com.qbao.aisr.app.model.UserTags;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-03 17:59:07 +0800 (Fri, 03 Mar 2017) $$
 * $$LastChangedRevision: 53 $$
 * $$LastChangedBy: wangping $$
 */
public interface IUserTagService {

    /**
     *
     * @param userId
     * @param typeId   标签类型(个人标签 : 1 购物标签 :2)
     * @return
     */
    public UserTags findByUserId(long userId, int typeId);

    /**
     *
     * @param userId
     * @param typeId 标签类型(个人标签 : 1 购物标签 :2)
     * @param tags  "1,3,5,6,8"
     * @return
     */
    public void modifyUserTags(long userId, int typeId, String tags);

}
