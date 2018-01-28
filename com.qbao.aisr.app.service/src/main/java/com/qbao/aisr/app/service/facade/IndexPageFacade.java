package com.qbao.aisr.app.service.facade;

import com.qbao.aisr.app.common.enums.DeviceEnum;
import com.qbao.aisr.app.dto.Ad;
import com.qbao.aisr.app.dto.BannerDto;
import com.qbao.aisr.app.dto.MenuDto;
import com.qbao.aisr.app.dto.StuffModuleDto;
import com.qbao.aisr.app.model.AdBanner;
import com.qbao.aisr.app.model.AdJu;
import com.qbao.aisr.app.model.AdQbao;
import com.qbao.aisr.app.model.AdShop;
import com.qbao.aisr.app.model.AdStuff;
import com.qbao.aisr.app.model.LocationStyleConf;
import com.qbao.aisr.app.model.ShopPromotion;
import com.qbao.aisr.app.model.StuffJu;
import com.qbao.aisr.app.model.StuffMenu;
import com.qbao.aisr.app.model.StuffModule;
import com.qbao.aisr.app.model.StuffPromotion;
import com.qbao.aisr.app.model.StuffRebate;
import com.qbao.aisr.app.repository.redis.cache.annotation.CacheType;
import com.qbao.aisr.app.repository.redis.cache.annotation.RedisCache;
import com.qbao.aisr.app.service.ad.IAdBannerService;
import com.qbao.aisr.app.service.ad.IAdJuService;
import com.qbao.aisr.app.service.ad.IAdQbaoService;
import com.qbao.aisr.app.service.ad.IAdShopService;
import com.qbao.aisr.app.service.ad.IAdStuffService;
import com.qbao.aisr.app.service.ju.IStuffJuService;
import com.qbao.aisr.app.service.location.ILocationStyleConfService;
import com.qbao.aisr.app.service.menu.IStuffMenuService;
import com.qbao.aisr.app.service.module.IStuffModuleService;
import com.qbao.aisr.app.service.promotion.IPromotionUrlService;
import com.qbao.aisr.app.service.rebate.IStuffReBateService;
import com.qbao.aisr.app.service.shop.IShopPromotionService;
import com.qbao.aisr.app.service.stuff.IStuffPromotionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangping
 * @createTime 17/3/3 下午7:03
 * $$LastChangedDate: 2017-05-17 11:07:29 +0800 (Wed, 17 May 2017) $$
 * $$LastChangedRevision: 851 $$
 * $$LastChangedBy: wangping $$
 */
@Component
public class IndexPageFacade {
    public Logger logger = Logger.getLogger(getClass());

    @Autowired
    private IStuffMenuService stuffMenuService;

    @Autowired
    private IStuffModuleService stuffModuleService;

    @Autowired
    private IAdShopService adShopService;

    @Autowired
    private IAdStuffService adStuffService;

    @Autowired
    private IAdJuService adJuService;

    @Autowired
    private IAdBannerService adBannerService;

    @Autowired
    private IAdQbaoService adQbaoService;

    @Autowired
    private ILocationStyleConfService locationStyleConfService;

    @Autowired
    private IStuffJuService stuffJuService;

    @Autowired
    private IShopPromotionService shopPromotionService;

    @Autowired
    private IStuffPromotionService stuffPromotionService;

    @Autowired
    private IStuffReBateService stuffReBateService;

    @Autowired
    private IPromotionUrlService promotionUrlService;

    @RedisCache(expire = 60 * 10, clazz = MenuDto.class, cacheType = CacheType.LIST)
    public List<MenuDto> fetchMenus() {
        List<MenuDto> result = new LinkedList<MenuDto>();
        List<StuffMenu> menus = stuffMenuService.findAllStuffMenu();
        if (CollectionUtils.isNotEmpty(menus)) {
            MenuDto.Builder builder = new MenuDto.Builder();
            for (StuffMenu menu : menus) {
                result.add(builder.withName(menu.getName()).withIconUrl(menu.getIconUrl()).withLinkUrl(menu.getLinkUrl()).build());
            }
        }
        return result;
    }

    /**
     *
     * @param userId
     * @return
     */
    @RedisCache(expire = 60 * 10, clazz = StuffModuleDto.class, cacheType = CacheType.LIST)
    public List<StuffModuleDto> fetchModules(long userId,int device) {
        List<StuffModuleDto> result = new LinkedList<StuffModuleDto>();
        List<StuffModule> modules = stuffModuleService.findAvailableModules();
        if (CollectionUtils.isNotEmpty(modules)) {
            StuffModuleDto.Builder builder = new StuffModuleDto.Builder();
            for (StuffModule module : modules) {
                int styleId = module.getStyleId();
                int moduleId = module.getId();
                List<Ad> ads = new LinkedList<Ad>();
                List<BannerDto> bannerDtos = new LinkedList<BannerDto>();
                List<LocationStyleConf> bannerConf = locationStyleConfService.findConfByStyleId(styleId); // banner
                if (CollectionUtils.isNotEmpty(bannerConf)) {
                    for (LocationStyleConf conf : bannerConf) {
                        int limit = conf.getCount();
                        int locationId = conf.getId();
                        if (0 == conf.getType()) { // banner conf
                            List<AdBanner> adBanners = adBannerService.findBannerByLocationId(conf.getId(),limit);
                            bannerDtos.addAll(asBanners(adBanners));
                        } else { // ad conf
                            if (1 == moduleId) { //聚好货
                                ads.addAll(generateAdJuItems(locationId, limit));
                            }
                            if (2 == moduleId ) {//热卖好货
                                ads.addAll(generateAdStuffItems(adStuffService.findByLocationId(locationId, limit),device));
                            }
                            if (3 == moduleId ) {//钱宝自营
                                ads.addAll(generateAdStuffItemsByAdQbao(adQbaoService.findByLocationId(locationId, limit)));
                            }
                            if (4 == moduleId) {//发现好店
                                ads.addAll(generateAdShopItems(locationId, limit,device));
                            }
                        }
                    }

                    result.add(builder.withTitle(module.getTitle()).
                            withTitleIcon(module.getTitleIcon()).
                            withMore(module.getMore()).
                            withModuleId(module.getId()).
                            withStyleId(module.getStyleId()).
                            withAds(ads).
                            withBanners(bannerDtos).
                            build()
                    );
                }
            }
        }
        return result;
    }

    private List<BannerDto> asBanners(List<AdBanner> banners) {
        List<BannerDto> result = null;
        if (null != banners) {
            BannerDto.Builder builder = new BannerDto.Builder();
            result = new LinkedList<BannerDto>();
            for (AdBanner adBanner : banners) {
                result.add(builder.withId(adBanner.getId()).withName(adBanner.getName()).withLinkUrl(adBanner.getLinkUrl()).withImgUrl(adBanner.getImgUrl()).withLocationId(adBanner.getAdLocationId()).build());
            }
        }
        return result;
    }


    private List<Ad> generateAdShopItems(int locationId, int limit,int device) {
        List<AdShop> adShops = adShopService.findByLocationId(locationId, limit);
        List<Ad> items = new ArrayList<Ad>();
        if (CollectionUtils.isNotEmpty(adShops)) {
            Ad.Builder builder = new Ad.Builder();
            for (AdShop adShop : adShops) {
                ShopPromotion shop = shopPromotionService.getShopInfo(adShop.getShopId());
                if (null != shop) {
                    items.add(builder.withId(shop.getId()).
                            withName(shop.getName()).
                            withImgUrl(shop.getCoverUrl()).
                            withLinkUrl(promotionUrlService.findPromtoionUrl(shop, DeviceEnum.asDeviceEnum(device))).
                            withLocationId(locationId).
                            withSource(shop.getSource()).
                            build()
                    );
                }
            }

        }
        return items;
    }

    private List<Ad> generateAdStuffItems(List<AdStuff> ads, int device) {
        List<Ad> items = new ArrayList<Ad>();
        if (CollectionUtils.isNotEmpty(ads)) {
            Ad.Builder builder = new  Ad.Builder();
            for (AdStuff adStuff : ads) {
                StuffPromotion stuffPro = stuffPromotionService.findStuffPromotion(adStuff.getStuffId());
                if (null != stuffPro) {
                    items.add(builder.withId(stuffPro.getId()).
                            withName(StringUtils.trimToEmpty(stuffPro.getName())).
                            withImgUrl(StringUtils.trimToEmpty(stuffPro.getImgUrl())).
                            withLinkUrl(StringUtils.trimToEmpty(promotionUrlService.findPromtoionUrl(stuffPro, DeviceEnum.asDeviceEnum(device)))).
                            withSource(StringUtils.trimToEmpty(stuffPro.getSource())).
                            withRebateValue(stuffReBateService.findStuffRebateValue(stuffPro.getId())).
                            withPrice(stuffPro.getFinalPrice()).
                            withSaleCount(stuffPro.getOrderNum() == null ? 0 : stuffPro.getOrderNum()).
                            withLocationId(adStuff.getAdLocationId()).build()
                    );
                }
            }
        }
        return items;
    }
    private List<Ad> generateAdStuffItemsByAdQbao(List<AdQbao> ads) {
        List<Ad> items = new ArrayList<Ad>();
        if (CollectionUtils.isNotEmpty(ads)) {
            Ad.Builder builder = new Ad.Builder();
            for (AdQbao adQbao : ads) {
                    items.add(builder.withId(adQbao.getId()).
                            withImgUrl(StringUtils.trimToEmpty(adQbao.getImgUrl())).
                            withLinkUrl(StringUtils.trimToEmpty(adQbao.getLinkUrl())).
                            withName(StringUtils.trimToEmpty(adQbao.getName())).
                            withLocationId(adQbao.getAdLocationId()).build()
                    );
            }
        }
        return items;
    }

    private String getRebateValue(int rebateId) {
        StuffRebate stuffRebate = stuffReBateService.findStuffRebate(rebateId);
        if (null != stuffRebate) {
            return stuffRebate.getValue() + "";
        }
        return "";
    }

    private List<Ad> generateAdJuItems(int locationId, int limit) {
        Ad.Builder builder = new Ad.Builder();
        List<AdJu> adJus = adJuService.findByLocationId(locationId, limit);
        List<Ad> items = new ArrayList<Ad>();
        if (CollectionUtils.isNotEmpty(adJus)) {
            for (AdJu adJu : adJus) {
                StuffJu stuffJu = stuffJuService.findStuffJuById(adJu.getJuId());
                if (null != stuffJu) {
                    items.add(builder.withId(stuffJu.getId()).
                            withName(stuffJu.getName()).
                            withImgUrl(stuffJu.getImgUrl()).
                            withLinkUrl(stuffJu.getLinkUrl()).
                            withLocationId(adJu.getAdLocationId()).build()
                    );
                }
            }
        }
        return items;
    }

}
