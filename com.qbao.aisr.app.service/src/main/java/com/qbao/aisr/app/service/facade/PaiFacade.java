package com.qbao.aisr.app.service.facade;

import com.qbao.aisr.app.common.constant.Constant;
import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.common.util.BeanUtils;
import com.qbao.aisr.app.dto.pai.PaiUploadDto;
import com.qbao.aisr.app.dto.search.ZwStuffDto;
import com.qbao.aisr.app.model.search.ZwStuff;
import com.qbao.aisr.app.service.pai.IPaiService;
import com.qbao.aisr.app.service.rebate.impl.StuffReBateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shuaizhihu
 * @createTime 2017/3/12 18:00
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Component
public class PaiFacade {
    @Autowired
    IPaiService iPaiService;

    @Autowired
    StuffReBateService stuffReBateService;

    public PaiUploadDto upload(MultipartFile mFile, Long userId) {
        return iPaiService.upload(mFile, userId);
    }

    public List facetsImg(String imgUrl) throws HttpProcessException {
        return iPaiService.facetsImg(imgUrl);
    }

    public Page<ZwStuffDto> search(String imgUrl, long userId, String cId, String source, String sort, int page, int size, int device)
            throws HttpProcessException, UnsupportedEncodingException {
        Page<ZwStuff> result = iPaiService.search(imgUrl,userId,cId,source,sort,page,size);
        int total = result.getTotalNumber();
        List<ZwStuffDto> list = new ArrayList<ZwStuffDto>();
        for (ZwStuff dto : result.getItems()) {
            ZwStuffDto zwStuffDto = new ZwStuffDto();
            BeanUtils.copy(dto, zwStuffDto);
            if (Constant.IOS == device) {
                zwStuffDto.setLinkUrl(dto.getIosPromotionUrl());
            } else {
                zwStuffDto.setLinkUrl(dto.getAndroidPromotionUrl());
            }
            zwStuffDto.setRebateValue(stuffReBateService.findStuffRebateValue(zwStuffDto.getStuffId()));
            list.add(zwStuffDto);
        }

        return new Page<ZwStuffDto>(total,result.getCurrentIndex(),result.getPageSize(),list);
    }
}
