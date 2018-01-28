package com.qbao.aisr.app.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author liaijun
 * @createTime 17/4/1 下午4:49
 * $$LastChangedDate: 2017-05-16 19:09:02 +0800 (Tue, 16 May 2017) $$
 * $$LastChangedRevision: 850 $$
 * $$LastChangedBy: wangping $$
 */
public class BannerStuffInfoDto implements Serializable {
    private static final long serialVersionUID = "$Id: BannerStuffInfoDto.java 850 2017-05-16 11:09:02Z wangping $".hashCode();
    private Integer level;

    private String title;

    private List<StuffPromotionDto> stuffs;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<StuffPromotionDto> getStuffs() {
        return stuffs;
    }

    public void setStuffs(List<StuffPromotionDto> stuffs) {
        this.stuffs = stuffs;
    }

    @Override
    public String toString() {
        return "BannerStuffInfoDto{" + "level=" + level + ", title='" + title + '\'' + ", stuffs=" + stuffs + '}';
    }
}
