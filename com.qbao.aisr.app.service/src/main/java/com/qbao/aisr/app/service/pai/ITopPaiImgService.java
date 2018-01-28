package com.qbao.aisr.app.service.pai;

import java.util.List;

import com.qbao.aisr.app.dto.TopPaiImgDto;
import com.qbao.aisr.app.dto.UserStuffPaiDto;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-03-28 14:36:05 +0800 (Tue, 28 Mar 2017) $$
 * $$LastChangedRevision: 562 $$
 * $$LastChangedBy: liaijun $$
 */
public interface ITopPaiImgService {
    public List<TopPaiImgDto> findTopPaiList(int page, int pageSize);

    public Long countTopPaiImg();

    public List<UserStuffPaiDto> findUserPaiList(Long userId, int page, int pageSize);

    public Long countUserPai(Long userId);

}
