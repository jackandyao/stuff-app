package com.qbao.aisr.app.service.facade;

import com.qbao.aisr.app.dto.BannerDto;
import com.qbao.aisr.app.dto.BannerSaleStuffConfDto;
import com.qbao.aisr.app.dto.BannerSaleStuffInfoDto;
import com.qbao.aisr.app.dto.BannerSaleStuffItemDto;
import com.qbao.aisr.app.dto.BannerStuffConfDto;
import com.qbao.aisr.app.dto.BannerStuffInfoDto;
import com.qbao.aisr.app.dto.StuffPromotionDto;
import com.qbao.aisr.app.model.AdBanner;
import com.qbao.aisr.app.model.AdJu;
import com.qbao.aisr.app.model.AdShop;
import com.qbao.aisr.app.model.AdStuff;
import com.qbao.aisr.app.model.BannerFlashSaleStuffConf;
import com.qbao.aisr.app.model.BannerStuffConf;
import com.qbao.aisr.app.model.LocationStyleConf;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.ad.IAdBannerService;
import com.qbao.aisr.app.service.ad.IAdStuffService;
import com.qbao.aisr.app.service.location.ILocationStyleConfService;
import com.qbao.aisr.app.service.stuff.IStuffPromotionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author wangping
 * @createTime 17/3/8 上午11:24
 * $$LastChangedDate: 2017-08-07 10:28:41 +0800 (Mon, 07 Aug 2017) $$
 * $$LastChangedRevision: 1483 $$
 * $$LastChangedBy: liaijun $$
 */
@Component
public class AdFacade {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private IAdBannerService adBnnerService;

    @Autowired
    private IAdStuffService adStuffService;


    @Autowired
    private ILocationStyleConfService locationStyleConfService;

    @Autowired
    private IStuffPromotionService stuffPromotionService;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @RedisCache(expire = 60 * 2, clazz = BannerDto.class, cacheType = CacheType.LIST)
    public List<BannerDto> fetchAdBanner(int locationId, Integer device) {
        LocationStyleConf conf = locationStyleConfService.findConfById(locationId);
        int limit = null == conf ? 1 : conf.getCount();
        List<BannerDto> result = adBannerToBannerDto(adBnnerService.findBannerByLocationId(locationId, limit));
        logger.info("total get [" + (CollectionUtils.isEmpty(result) ? 0 : result.size()) + "] ad banners, the  locationId=[" + locationId + "], limit=[" + limit + "]");
        return result;
    }

    private List<BannerDto> adBannerToBannerDto(List<AdBanner> adBanners) {
        List<BannerDto> result = null;
        if (null != adBanners) {
            BannerDto.Builder builder = new BannerDto.Builder();
            result = new LinkedList<>();
            for (AdBanner adbanner : adBanners) {
                result.add(builder.withId(adbanner.getId()).withLocationId(adbanner.getAdLocationId()).withName(adbanner.getName()).withLinkUrl(adbanner.getLinkUrl()).withImgUrl(adbanner.getImgUrl()).build());
            }
        }
        return result;
    }

    @RedisCache(expire = 60 * 2, clazz = StuffPromotionDto.class, cacheType = CacheType.LIST)
    public List<StuffPromotionDto> fetchAdStuff(int locationId, int device) {
        List<StuffPromotionDto> result = new ArrayList<StuffPromotionDto>();
        LocationStyleConf conf = locationStyleConfService.findConfById(locationId);
        int limit = null == conf ? 1 : conf.getCount();

        List<AdStuff> adStuffs = adStuffService.findByLocationId(locationId);
        if (CollectionUtils.isNotEmpty(adStuffs)) {
            for(int index= 0; index< adStuffs.size() && limit > 0; index++){
                StuffPromotionDto dto = stuffPromotionService.findStuffPromotionInfo(adStuffs.get(index).getStuffId(),device);
                if (null != dto  ) {
                    result.add(dto);
                    limit --;
                }
            }

        }
        return result;
    }

    public List<AdShop> fetchAdShop(int locationId, int device) {
        return null;
    }

    public List<AdJu> fetchAdJu(int locationId, int device) {
        return null;
    }

    @RedisCache(expire = 60 * 2, clazz = BannerStuffConfDto.class, cacheType = CacheType.OBJECT)
    public BannerStuffConfDto queryBannerStuffConf(Integer bannerId, int device) {
        logger.info("queryBannerStuffConf bannerId=" + bannerId + "   device=" + device);
        BannerStuffConfDto confDto = new BannerStuffConfDto();


        AdBanner adBanner = adBnnerService.findBannerById(bannerId);
        if (adBanner != null) {
            confDto.setImgUrl(adBanner.getImgUrl());
            confDto.setName(adBanner.getName());
            confDto.setDetails(queryBannerStuffInfo(bannerId, device));
        }
        return confDto;
    }

    @RedisCache(expire = 60 * 2, clazz = BannerStuffInfoDto.class, cacheType = CacheType.LIST)
    public List<BannerStuffInfoDto> queryBannerStuffInfo(Integer bannerId, int device) {
        logger.info("queryBannerStuffInfo bannerId=" + bannerId + "   device=" + device);
        Map<Integer, BannerStuffInfoDto> map = new LinkedHashMap<Integer, BannerStuffInfoDto>();
        List<BannerStuffInfoDto> infoDtos = new ArrayList<BannerStuffInfoDto>();

        List<BannerStuffConf> bannerStuffConfList = adBnnerService.findBannerStuffConfByBannerId(bannerId);
        if (CollectionUtils.isNotEmpty(bannerStuffConfList)) {
            for (BannerStuffConf conf : bannerStuffConfList) {
                Integer level = conf.getLevel();
                if (level == null) {
                    continue;
                }
                if (map.get(level) == null) {
                    map.put(level, queryStuffPromotionInfo(conf, device));
                } else {
                    BannerStuffInfoDto infoDto = map.get(level);
                    List<StuffPromotionDto> stuffPromotionDtos = infoDto.getStuffs();
                    StuffPromotionDto stuffPromotion = stuffPromotionService.findStuffPromotionInfo(conf.getStuffId(), device);
                    if (stuffPromotion != null) {
                        stuffPromotionDtos.add(stuffPromotion);
                    }
                }
            }
            for (BannerStuffInfoDto infoDto : map.values()) {
                infoDtos.add(infoDto);
            }
        }
        return infoDtos;
    }

    @RedisCache(expire = 60 * 2, clazz = BannerStuffInfoDto.class, cacheType = CacheType.OBJECT)
    public BannerStuffInfoDto queryStuffPromotionInfo(BannerStuffConf conf, int device) {
        logger.info("queryStuffPromotionInfo conf=" + conf + " device=" + device);
        StuffPromotionDto stuffPromotion = stuffPromotionService.findStuffPromotionInfo(conf.getStuffId(), device);
        List<StuffPromotionDto> stuffPromotionDtos = new ArrayList<StuffPromotionDto>();
        BannerStuffInfoDto infoDto = new BannerStuffInfoDto();
        if (stuffPromotion != null) {
            stuffPromotionDtos.add(stuffPromotion);
        }
        infoDto.setLevel(conf.getLevel());
        infoDto.setTitle(conf.getTitle());
        infoDto.setStuffs(stuffPromotionDtos);
        return infoDto;
    }


    @RedisCache(expire = 60 * 2, clazz = BannerSaleStuffConfDto.class, cacheType = CacheType.OBJECT)
    public BannerSaleStuffConfDto queryBannerSaleStuffConf(Integer bannerId, int device) {
        logger.info("queryBannerSaleStuffConf bannerId=" + bannerId + "   device=" + device);
        BannerSaleStuffConfDto confDto = new BannerSaleStuffConfDto();


        AdBanner adBanner = adBnnerService.findBannerById(bannerId);
        if (adBanner != null) {
            confDto.setImgUrl(adBanner.getImgUrl());
            confDto.setName(adBanner.getName());
            confDto.setFlashSale(queryBannerSaleStuffInfo(bannerId,device));
            confDto.setDetails(queryBannerStuffInfo(bannerId, device));

        }
        return confDto;
    }

    @RedisCache(expire = 60 * 10, clazz = BannerSaleStuffItemDto.class, cacheType = CacheType.OBJECT)
    public BannerSaleStuffItemDto queryBannerSaleStuffInfo(Integer bannerId, int device) {
        logger.info("queryBannerSaleStuffInfo bannerId=" + bannerId + "   device=" + device);
        BannerSaleStuffItemDto itemDto=new BannerSaleStuffItemDto();
        itemDto.setTitle("限时抢");
        Map<String, BannerSaleStuffInfoDto> map = new LinkedHashMap<String, BannerSaleStuffInfoDto>();
        List<BannerSaleStuffInfoDto> infoDtos = new ArrayList<BannerSaleStuffInfoDto>();

        List<BannerFlashSaleStuffConf> bannerStuffConfList = adBnnerService.findBannerSaleStuffConfByBannerId(bannerId);
        if (CollectionUtils.isNotEmpty(bannerStuffConfList)) {
            for (BannerFlashSaleStuffConf conf : bannerStuffConfList) {
                Date onTime = conf.getOnTime();

                String dateString = formatter.format(onTime);
                if (map.get(dateString) == null) {
                    map.put(dateString, querySaleStuffPromotionInfo(conf, device));
                } else {
                    BannerSaleStuffInfoDto infoDto = map.get(dateString);
                    List<StuffPromotionDto> stuffPromotionDtos = infoDto.getStuffs();
                    StuffPromotionDto stuffPromotion = stuffPromotionService.findStuffPromotionInfo(conf.getStuffId(), device);
                    if (stuffPromotion != null) {
                        stuffPromotionDtos.add(stuffPromotion);
                    }
                }
            }
            for (BannerSaleStuffInfoDto infoDto : map.values()) {
                infoDtos.add(infoDto);
            }
        }


        itemDto.setItem(infoDtos);
        return itemDto;
    }

    @RedisCache(expire = 60 * 2, clazz = BannerSaleStuffInfoDto.class, cacheType = CacheType.OBJECT)
    public BannerSaleStuffInfoDto querySaleStuffPromotionInfo(BannerFlashSaleStuffConf conf, int device) {
        logger.info("querySaleStuffPromotionInfo conf=" + conf + " device=" + device);

        StuffPromotionDto stuffPromotion = stuffPromotionService.findStuffPromotionInfo(conf.getStuffId(), device);
        List<StuffPromotionDto> stuffPromotionDtos = new ArrayList<StuffPromotionDto>();
        BannerSaleStuffInfoDto infoDto = new BannerSaleStuffInfoDto();
        if (stuffPromotion != null) {
            stuffPromotionDtos.add(stuffPromotion);
        }
        infoDto.setDate(formatter.format(conf.getOnTime()));
        infoDto.setStuffs(stuffPromotionDtos);
        return infoDto;
    }
}
