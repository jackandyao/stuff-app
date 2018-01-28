package com.qbao.aisr.app.service.pai.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.common.util.UploadUtils;
import com.qbao.aisr.app.dto.pai.PaiUploadDto;
import com.qbao.aisr.app.model.search.ZwStuff;
import com.qbao.aisr.app.repository.mybatis.dao.pai.UserStuffPaiDao;
import com.qbao.aisr.app.repository.redis.dao.RedisDao;
import com.qbao.aisr.app.repository.rest.search.StuffSearchBiz;
import com.qbao.aisr.app.service.pai.IPaiService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shuaizhihu
 * @createTime 2017/3/12 17:46
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Service
public class PaiServiceImpl implements IPaiService {
    Logger logger = Logger.getLogger(PaiServiceImpl.class);
    @Autowired
    StuffSearchBiz stuffSearchBiz;
    @Autowired
    UserStuffPaiDao userStuffPaiDao;
    @Autowired
    RedisDao redisDao;
    @Override
    public PaiUploadDto upload(MultipartFile mFile, Long userId) {
        String key =  UploadUtils.uploadFile(mFile,UploadUtils.TAOBAO_URL,UploadUtils.TAOBAO_FIELD_NAME);
        PaiUploadDto dto = new PaiUploadDto();
        if (key != null & key.indexOf("http") < 0) {
            key = "http:" + key;
        }
        dto.setKey(key);
        dto.setSrc(mFile.getOriginalFilename());

        userStuffPaiDao.saveUserStuffPai(userId, key, new Date());

        delCache(userId);
        return dto;
    }


    private void delCache(Long userId) {
        String cacheKey = "TopPaiImgServiceImpl_countUserPai_"+userId;
        logger.info("del the user id=["+userId+"] , redis cache, the redis key=["+cacheKey+"]");
        long size = userStuffPaiDao.countUserPaiImg(userId);
        redisDao.del(cacheKey);
        delUserPaiListCache(userId,size,24);//android cache
        delUserPaiListCache(userId,size,15);//IOS cache
    }

    private void delUserPaiListCache(Long userId,long size, int pageSize){
        long page = (size / pageSize)  > 0  ? 1: (size / pageSize)+1 ;
        for(int i=1; i<=page; i++){
            String  cacheKey = "TopPaiImgServiceImpl_findUserPaiList_"+userId+"_"+i+"_"+pageSize;
            logger.info("del the user id=["+userId+"] , redis cache, the redis key=["+cacheKey+"]");
            redisDao.del(cacheKey);
        }
    }

    @Override
    public List facetsImg(String imgUrl) throws HttpProcessException {
        String result = stuffSearchBiz.facetsImg(imgUrl);
        JSONObject jsonObject = JSON.parseObject(result);
        if(jsonObject.containsKey("data")){
            JSONObject dataObject = jsonObject.getJSONObject("data");
            if(dataObject.containsKey("dirProductList")){
                return  dataObject.getObject("dirProductList",List.class);
            }
        }
        return null;
    }

    @Override
    public Page<ZwStuff> search(String imgUrl, long userId, String cId, String source, String sort, int page, int size)
            throws HttpProcessException, UnsupportedEncodingException {
        String result = stuffSearchBiz.searchZwImg(imgUrl,userId,cId,source,sort,page,size);
        int total = 0;
        List<ZwStuff> list = new ArrayList<ZwStuff>();
        JSONObject resultObject = JSON.parseObject(result);
        String code = resultObject.getString("errorCode");
        if("0".equals(code)){
            JSONObject dataObject = resultObject.getJSONObject("data");
            total = dataObject.getInteger("totalCount");
            JSONArray dataArray = dataObject.getJSONArray("dataList");
            list.addAll(JSON.parseArray(dataArray.toJSONString(),ZwStuff.class));
        }else{
            logger.error("查询出错！ result:"+result);
            throw new RuntimeException("查询出错！");
        }
        return new Page<ZwStuff>(total,page,size,list);
    }

}
