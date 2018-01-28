package com.qbao.aisr.app.service.comm;

import com.qbao.aisr.app.model.CommMsg;

/**
 * @author zhangjun
 * @createTime 17/5/30 上午10:33
 * $$LastChangedDate: 2017-03-10 21:09:54 +0800 (Fri, 10 Mar 2017) $$
 * $$LastChangedRevision: 127 $$
 * $$LastChangedBy: zhangjun $$
 */
public interface ICommMsgService {

    public CommMsg findByTypeId(int typeId);

}
