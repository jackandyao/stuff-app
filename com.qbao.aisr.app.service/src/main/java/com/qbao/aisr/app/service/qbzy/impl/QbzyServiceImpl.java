package com.qbao.aisr.app.service.qbzy.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qbao.aisr.app.common.http.HttpClientUtil;
import com.qbao.aisr.app.common.http.common.HttpConfig;
import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.model.QbaoZyClass;
import com.qbao.aisr.app.model.search.ZnStuff;
import com.qbao.aisr.app.repository.mybatis.dao.qbzy.QbaoZyClassDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.qbzy.IQbzyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shuaizhihu
 * @createTime 2017/3/12 16:13
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Service
public class QbzyServiceImpl implements IQbzyService {

    Logger logger  = LoggerFactory.getLogger(QbzyServiceImpl.class);

    @Value("${search.domain}")
    private String search_domain;


    @Autowired
    QbaoZyClassDao qbaoZyClassDao;

    @Override
    @RedisCache(expire = 60 * 10, clazz = ZnStuff.class, cacheType = CacheType.PAGE)
    public Page<ZnStuff> goodsList(long cid, int page, int size) throws HttpProcessException {
        String url = this.createQbzyUrl(cid,page,size);
        String result = HttpClientUtil.get(HttpConfig.custom().url(url));
        JSONObject reponse = JSON.parseObject(result);
        String returnCode = reponse.getString("returnCode");
        if(returnCode.equals("1000")) {
            JSONObject dataObject = reponse.getJSONObject("data");
            int total = dataObject.getInteger("total");
            JSONArray array = dataObject.getJSONArray("list");
            List<ZnStuff> list = JSON.parseArray(array.toJSONString(), ZnStuff.class);
            return new Page<ZnStuff>(total, page, size, list);
        }else{
            logger.error("查询出错！ result:"+result);
            throw new RuntimeException("查询出错!");
        }
    }

    @Override
    @RedisCache(expire = 60 * 2, clazz = QbaoZyClass.class, cacheType = CacheType.LIST)
    public List<QbaoZyClass> goodClasses() {
       return  qbaoZyClassDao.findList();
    }


    private String createQbzyUrl(long cid,int page,int size){
        if(cid==0){
           throw new RuntimeException("cid must not be 0");
        }else{
            QbaoZyClass dir = qbaoZyClassDao.findById(cid);
            if(dir == null){
                throw new RuntimeException("没有找到对应的分类！");
            }else{
                String url = dir.getUrl();
                if(url.contains("?")){
                    url= search_domain+url+"&page="+page+"&size="+size;
                }else{
                    url= search_domain+url+"?page="+page+"&size="+size;
                }
                return url;
            }
        }
    }
}
