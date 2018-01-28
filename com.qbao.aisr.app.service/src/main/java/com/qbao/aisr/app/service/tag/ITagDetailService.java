package com.qbao.aisr.app.service.tag;

import com.qbao.aisr.app.model.TagDetail;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-03 17:59:07 +0800 (Fri, 03 Mar 2017) $$
 * $$LastChangedRevision: 53 $$
 * $$LastChangedBy: wangping $$
 */
public interface ITagDetailService {

    public List<TagDetail> findAll();

    public List<TagDetail> findByTagType(int tagTypeId);

}
