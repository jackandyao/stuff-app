package com.qbao.aisr.app.service.facade;

import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.dto.StuffHotClassDto;
import com.qbao.aisr.app.dto.StuffHotGoodsDto;
import com.qbao.aisr.app.service.hot.IStuffHotClassService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liaijun
 * @createTime 17/3/10 下午5:25
 * $$LastChangedDate: 2017-03-30 16:11:14 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 588 $$
 * $$LastChangedBy: liaijun $$
 */
@Component
public class HotFacade {

    private Logger logger = Logger.getLogger(HotFacade.class);
    @Autowired
    private IStuffHotClassService stuffHotClassService;

    /**
     * 获取热卖好货所有类目
     * 
     * @return
     */
    public List<StuffHotClassDto> queryHotClassCat() {
        return stuffHotClassService.queryHotClassCat();
    }

    /**
     *热卖好货商品列表接口
     * @param id
     * @param page
     * @param size
     * @return
     */
    public Page<StuffHotGoodsDto> queryHotClassById(Long id, int page, int size, int device) throws Exception {
        return stuffHotClassService.queryHotClassById(id, page, size, device);
    }
}
