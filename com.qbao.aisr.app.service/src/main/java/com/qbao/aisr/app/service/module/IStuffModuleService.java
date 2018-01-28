package com.qbao.aisr.app.service.module;

import com.qbao.aisr.app.model.StuffModule;

import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/6 上午10:27
 * $$LastChangedDate: 2017-03-07 10:25:05 +0800 (Tue, 07 Mar 2017) $$
 * $$LastChangedRevision: 81 $$
 * $$LastChangedBy: wangping $$
 */
public interface IStuffModuleService {

    public List<StuffModule> findAvailableModules();
}
