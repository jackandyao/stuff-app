package com.qbao.aisr.app.dto;

import java.io.Serializable;
import java.util.List;

import com.qbao.aisr.app.dto.StuffPromotionDto;

/**
 * @author liaijun
 * @createTime 17/4/1 下午4:49
 * $$LastChangedDate: 2017-06-07 18:30:21 +0800 (Wed, 07 Jun 2017) $$
 * $$LastChangedRevision: 1116 $$
 * $$LastChangedBy: wangping $$
 */
public class BannerSaleStuffInfoDto implements Serializable {
    private static final long serialVersionUID = "$Id: BannerSaleStuffInfoDto.java 1116 2017-06-07 10:30:21Z wangping $".hashCode();

    private String date;

    private List<StuffPromotionDto> stuffs;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StuffPromotionDto> getStuffs() {
        return stuffs;
    }

    public void setStuffs(List<StuffPromotionDto> stuffs) {
        this.stuffs = stuffs;
    }

    @Override
    public String toString() {
        return "BannerSaleStuffInfoDto{" +
                "date='" + date + '\'' +
                ", stuffs=" + stuffs +
                '}';
    }
}
