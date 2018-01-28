package com.qbao.aisr.app.service.pai;

import com.alibaba.fastjson.JSONObject;
import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.dto.pai.PaiUploadDto;
import com.qbao.aisr.app.model.search.ZnStuff;
import com.qbao.aisr.app.model.search.ZwStuff;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author shuaizhihu
 * @createTime 2017/3/12 17:39
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
public interface IPaiService {

    public PaiUploadDto upload(MultipartFile mFile, Long userId);

    public List facetsImg(String imgUrl) throws HttpProcessException;

    public Page<ZwStuff> search(String imgUrl,long userId,String cId,String source,String sort,int page, int size)
            throws HttpProcessException, UnsupportedEncodingException;

}

