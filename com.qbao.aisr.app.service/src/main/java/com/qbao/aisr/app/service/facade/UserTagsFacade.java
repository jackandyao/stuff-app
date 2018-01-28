package com.qbao.aisr.app.service.facade;

import com.qbao.aisr.app.common.constant.UserTagConstant;
import com.qbao.aisr.app.dto.TagItem;
import com.qbao.aisr.app.dto.UserTagsDto;
import com.qbao.aisr.app.model.TagDetail;
import com.qbao.aisr.app.model.UserTags;
import com.qbao.aisr.app.service.tag.ITagDetailService;
import com.qbao.aisr.app.service.tag.IUserTagService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/3 下午1:48
 * $$LastChangedDate: 2017-03-27 10:50:26 +0800 (Mon, 27 Mar 2017) $$
 * $$LastChangedRevision: 541 $$
 * $$LastChangedBy: wangping $$
 */
@Component
public class UserTagsFacade {

    public Logger logger = Logger.getLogger(getClass());

    @Autowired
    private IUserTagService userTagService;

    @Autowired
    private ITagDetailService tagDetailService;


    public UserTagsDto fetchUserTags(long userId, int tagTypeId) {
        UserTagsDto.Builder builder = new UserTagsDto.Builder();
        List<String> checkedDetailsIds = new ArrayList<String>();
        List<TagItem> items = new ArrayList<TagItem>();

        UserTags userTags = userTagService.findByUserId(userId, tagTypeId);
        if (null != userTags) {
            checkedDetailsIds = Arrays.asList(StringUtils.trimToEmpty(userTags.getTagDetailIds()).split(UserTagConstant.DELIMIT_COMMA));
        }
        logger.info("user id = [" + userId + "], tag Type id = [" + tagTypeId + "], and detail tags ids : " + checkedDetailsIds);

        List<TagDetail> tagDetails = tagDetailService.findByTagType(tagTypeId);
        logger.info("total get tag details [" + tagDetails.size() + "] record by tag type id = [" + tagTypeId + "]");
        if (CollectionUtils.isNotEmpty(tagDetails)) {
            for (TagDetail detail : tagDetails) {
                TagItem.Builder tagItemsBuilder = new TagItem.Builder();
                tagItemsBuilder.withTagDetailId(detail.getId()).
                        withName(detail.getName()).
                        withCount(detail.getCount()).
                        withIcon(detail.getIcon()).
                        withCheck(checkedDetailsIds.contains(String.valueOf(detail.getId())));
                items.add(tagItemsBuilder.build());
            }
        }
        return builder.withTagTypeId(Integer.valueOf(tagTypeId)).withItems(items).build();
    }

    public void modifyUserTags(long userId, int tagTypeId, String detailTagIds) {
        userTagService.modifyUserTags(userId, tagTypeId, detailTagIds);
    }

}
