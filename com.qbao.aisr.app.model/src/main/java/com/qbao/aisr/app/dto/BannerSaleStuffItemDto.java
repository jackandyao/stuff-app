package com.qbao.aisr.app.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author liaijun
 * @createTime 17/4/1 下午4:49
 * $$LastChangedDate: 2017-06-07 18:30:21 +0800 (Wed, 07 Jun 2017) $$
 * $$LastChangedRevision: 1116 $$
 * $$LastChangedBy: wangping $$
 */
public class BannerSaleStuffItemDto implements Serializable {
    private static final long serialVersionUID = "$Id: BannerSaleStuffItemDto.java 1116 2017-06-07 10:30:21Z wangping $".hashCode();

    private String title;

    private List<BannerSaleStuffInfoDto> item;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BannerSaleStuffInfoDto> getItem() {
        return item;
    }

    public void setItem(List<BannerSaleStuffInfoDto> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "BannerSaleStuffItemDto{" +
                "title='" + title + '\'' +
                ", item=" + item +
                '}';
    }
}
