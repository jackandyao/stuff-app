package com.qbao.aisr.app.service.hot.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qbao.aisr.app.common.enums.DeviceEnum;
import com.qbao.aisr.app.common.http.HttpClientUtil;
import com.qbao.aisr.app.common.http.common.HttpConfig;
import com.qbao.aisr.app.common.page.Page;
import com.qbao.aisr.app.dto.StuffHotClassDto;
import com.qbao.aisr.app.dto.StuffHotGoodsDto;
import com.qbao.aisr.app.model.StuffHotClass;
import com.qbao.aisr.app.repository.mybatis.dao.hot.StuffHotClassDao;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.hot.IStuffHotClassService;
import com.qbao.aisr.app.service.promotion.IPromotionUrlService;
import com.qbao.aisr.app.service.rebate.IStuffReBateService;

/**
 * @author liaijun
 * @createTime 17/3/10 下午5:09
 * $$LastChangedDate: 2017-06-21 14:44:51 +0800 (Wed, 21 Jun 2017) $$
 * $$LastChangedRevision: 1304 $$
 * $$LastChangedBy: zhangjun $$
 */
@Service
public class StuffHotClassServiceImpl implements IStuffHotClassService {
    Logger logger= Logger.getLogger(StuffHotClassServiceImpl.class);
    @Autowired
    private StuffHotClassDao stuffHotClassDao;
    @Autowired
    private IStuffReBateService stuffReBateService;
    @Value("${search.domain}")
    private String  goodsAddress;
    @Autowired
    private IPromotionUrlService promotionUrlService;
    /**
     * 获取热卖好货所有类目
     * 
     * @return
     */
    @RedisCache(expire = 60 * 60, clazz = StuffHotClassDto.class, cacheType = CacheType.LIST)
    public List<StuffHotClassDto> queryHotClassCat() {
        List<StuffHotClassDto> dtoList = new ArrayList<StuffHotClassDto>();
        List<StuffHotClass> list = stuffHotClassDao.queryHotClassCat();
        if (CollectionUtils.isNotEmpty(list)) {
            for (StuffHotClass hotClass : list) {
                StuffHotClassDto dto = new StuffHotClassDto();
                BeanUtils.copyProperties(hotClass, dto);
                dtoList.add(dto);
            }
        }
        return dtoList;

    }

    /**
     * 热卖好货商品列表接口
     * @param id
     * @return
     */
    @RedisCache(expire = 60 * 2, clazz = StuffHotGoodsDto.class, cacheType = CacheType.PAGE)
    public Page<StuffHotGoodsDto> queryHotClassById(Long id, int page, int size, int device) throws Exception {
        String url = createHotUrl(id, page, size);
        logger.info(">>>>>>>>>>>>>>>>>>>> url="+url);
        String result = HttpClientUtil.get(HttpConfig.custom().url(url));
        JsonObject rootJsonObject = new JsonParser().parse(result).getAsJsonObject();
        String errorCode=rootJsonObject.get("errorCode").toString();
        logger.info("queryHotClassById>>>>>>>>>>>>>>> id="+id+"  errorCode="+errorCode);
        return parseResponse(result, page, size, device);

    }

    @RedisCache(expire = 60 * 2, clazz = StuffHotGoodsDto.class, cacheType = CacheType.PAGE)
    private Page<StuffHotGoodsDto> parseResponse(String response, int page, int size, int device) {
        JsonObject rootJsonObject = new JsonParser().parse(response).getAsJsonObject();
        String errorCode=rootJsonObject.get("errorCode").toString();
        JsonObject data = rootJsonObject.getAsJsonObject("data");//取数组
        JsonArray dataList=data.getAsJsonArray("dataList");
        Integer totalCount=data.getAsJsonPrimitive("totalCount").getAsInt();
        List<StuffHotGoodsDto> list =new ArrayList<StuffHotGoodsDto>();
        if(dataList!=null) {
            for (int i = 0; i < dataList.size(); i++) {
            	JsonObject jsonObject = dataList.get(i).getAsJsonObject();
            	if(jsonObject.isJsonNull()) continue;
                StuffHotGoodsDto dto=new StuffHotGoodsDto();
                if(!jsonObject.get("id").isJsonNull()) dto.setId(jsonObject.get("id").getAsLong());
                if(!jsonObject.get("name").isJsonNull()) dto.setName(jsonObject.get("name").getAsString());
                if(!jsonObject.get("finalPrice").isJsonNull()) dto.setFinalPrice(jsonObject.get("finalPrice").getAsBigDecimal());
                if(!jsonObject.get("imgUrl").isJsonNull()) dto.setImgUrl(jsonObject.get("imgUrl").getAsString());
                dto.setLinkUrl(promotionUrlService.findPromtoionUrl(dataList, DeviceEnum.asDeviceEnum(device), i));
                long stuffId = 0L;
                if(!jsonObject.get("imgUrl").isJsonNull()) stuffId=jsonObject.get("id").getAsLong();
                String rebeatValue="";
                if(stuffId>0) {
                    rebeatValue = stuffReBateService.findStuffRebateValue(stuffId);
                }
                dto.setRebateValue(rebeatValue);
                if(!jsonObject.get("source").isJsonNull()) dto.setSource(jsonObject.get("source").getAsString());
                if(!jsonObject.get("orderNum").isJsonNull()) dto.setOrderNum(jsonObject.get("orderNum").getAsLong());
                list.add(dto);
            }
        }
        return new Page<StuffHotGoodsDto>(totalCount, page, size, list);
    }

    @Override
    @RedisCache(expire = 60 * 2, clazz = StuffHotGoodsDto.class, cacheType = CacheType.PAGE)
    public Page<StuffHotGoodsDto> queryHotClassByCatId(String catId, int page, int size, int device) throws Exception {
        String url = createUrlByCatId(catId, page, size);
        logger.info(">>>>>>>>>>>>>>>>>>>> url="+url);
        String result = HttpClientUtil.get(HttpConfig.custom().url(url));
        JsonObject rootJsonObject = new JsonParser().parse(result).getAsJsonObject();
        String errorCode=rootJsonObject.get("errorCode").toString();
        logger.info("queryHotClassByCatId>>>>>>>>>>>>>>> catId="+catId+"  errorCode="+errorCode);
        return parseResponse(result, page, size, device);
    }

    private String createHotUrl(long id, int page, int size) {
        if(id==0){
            throw new RuntimeException("cid must not be 0");
        }else{
            StuffHotClass hotClass = stuffHotClassDao.queryHotClassCatById(id);
            if(hotClass == null){
                throw new RuntimeException("没有找到对应的分类！");
            }else{
                String url = hotClass.getUrl();
                if(url.contains("?")){
                    url = goodsAddress + url + "&p=" + page + "&size=" + size;
                }else{
                    url = goodsAddress + url + "?p=" + page + "&size=" + size;
                }
                return url;
            }
        }
    }
    private String createUrlByCatId(String catId, int page, int size) {
                String url = goodsAddress + "/search_v2/api/stuff/search?cat_id="+catId+"&p=" + page + "&size=" + size;
                return url;
    }
}
