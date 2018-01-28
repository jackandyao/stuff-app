package com.qbao.aisr.app.service.rebate;

import com.qbao.aisr.app.model.StuffRebate;

/**
 * @author wangping
 * @createTime 17/3/7 上午9:24
 * $$LastChangedDate: 2017-03-23 10:21:11 +0800 (Thu, 23 Mar 2017) $$
 * $$LastChangedRevision: 469 $$
 * $$LastChangedBy: wangping $$
 */
public interface IStuffReBateService {

    public StuffRebate findStuffRebate(long id);

    public StuffRebate findStuffRebateByStuffId(Long stuffId);

    public String findStuffRebateValue(long stuffId);
}
