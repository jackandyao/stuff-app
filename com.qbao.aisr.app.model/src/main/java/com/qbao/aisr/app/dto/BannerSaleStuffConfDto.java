package com.qbao.aisr.app.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author liaijun
 * @createTime 17/4/1 上午10:49
 * $$LastChangedDate: 2017-06-07 18:30:21 +0800 (Wed, 07 Jun 2017) $$
 * $$LastChangedRevision: 1116 $$
 * $$LastChangedBy: wangping $$
 */
public class BannerSaleStuffConfDto implements Serializable {
    private static final long serialVersionUID = "$Id: BannerSaleStuffConfDto.java 1116 2017-06-07 10:30:21Z wangping $".hashCode();

    private String name;

    private String imgUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<BannerStuffInfoDto> getDetails() {
        return details;
    }

    public void setDetails(List<BannerStuffInfoDto> details) {
        this.details = details;
    }

    private List<BannerStuffInfoDto> details;

    private BannerSaleStuffItemDto  flashSale;

    public BannerSaleStuffItemDto getFlashSale() {
        return flashSale;
    }

    public void setFlashSale(BannerSaleStuffItemDto flashSale) {
        this.flashSale = flashSale;
    }

    @Override
    public String toString() {
        return "BannerStuffConfDto{" + "name='" + name + '\'' + ", imgUrl='" + imgUrl + '\'' + ", details=" + details + '}';
    }
}
