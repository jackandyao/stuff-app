package com.qbao.aisr.app.service.flash;

import com.qbao.aisr.app.dto.FlashSaleConfDto;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qbao.aisr.app.model.FlashSaleConf;
import com.qbao.aisr.app.repository.mybatis.annotation.DataSource;

import java.util.Date;
import java.util.List;

/**
 * @author liaijun
 * @createTime 2017/7/10 15:53
 * $$LastChangedDate: 2017-07-10 18:30:55 +0800 (Mon, 10 Jul 2017) $$
 * $$LastChangedRevision: 1413 $$
 * $$LastChangedBy: liaijun $$
 */
public interface IFlashSaleConfService {


    List<FlashSaleConfDto> findFlashSaleConf();

    Boolean findFlashSaleConfById(Long id,Date nowTime);

    List<StuffPromotionDto> findFlashSaleStuff(Long id, int page, int size, int device);
}
