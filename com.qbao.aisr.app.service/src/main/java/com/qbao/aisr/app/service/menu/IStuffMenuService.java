package com.qbao.aisr.app.service.menu;

import com.qbao.aisr.app.model.StuffMenu;

import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/5 下午9:36
 * $$LastChangedDate: 2017-03-06 14:18:07 +0800 (Mon, 06 Mar 2017) $$
 * $$LastChangedRevision: 72 $$
 * $$LastChangedBy: wangping $$
 */
public interface IStuffMenuService {

    public List<StuffMenu> findAllStuffMenu();
}
