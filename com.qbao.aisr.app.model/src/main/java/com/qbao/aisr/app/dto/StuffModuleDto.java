
package com.qbao.aisr.app.dto;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class StuffModuleDto implements Serializable{

    @SerializedName("ads")
    private List<Ad> ads;
    @SerializedName("banners")
    private List<BannerDto> banners;
    @SerializedName("moduleId")
    private int moduleId;
    @SerializedName("more")
    private String more;
    @SerializedName("styleId")
    private int styleId;
    @SerializedName("title")
    private String title;
    @SerializedName("titleIcon")
    private String titleIcon;

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public List<BannerDto> getBanners() {
        return banners;
    }

    public void setBanners(List<BannerDto> banners) {
        this.banners = banners;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleIcon() {
        return titleIcon;
    }

    public void setTitleIcon(String titleIcon) {
        this.titleIcon = titleIcon;
    }

    public static class Builder {

        private List<Ad> mAds;
        private List<BannerDto> mBannerDtos;
        private int mModuleId;
        private String mMore;
        private int mStyleId;
        private String mTitle;
        private String mTitleIcon;

        public StuffModuleDto.Builder withAds(List<Ad> ads) {
            mAds = ads;
            return this;
        }

        public StuffModuleDto.Builder withBanners(List<BannerDto> bannerDtos) {
            mBannerDtos = bannerDtos;
            return this;
        }

        public StuffModuleDto.Builder withModuleId(int moduleId) {
            mModuleId = moduleId;
            return this;
        }

        public StuffModuleDto.Builder withMore(String more) {
            mMore = more;
            return this;
        }

        public StuffModuleDto.Builder withStyleId(int styleId) {
            mStyleId = styleId;
            return this;
        }

        public StuffModuleDto.Builder withTitle(String title) {
            mTitle = title;
            return this;
        }

        public StuffModuleDto.Builder withTitleIcon(String titleIcon) {
            mTitleIcon = titleIcon;
            return this;
        }

        public StuffModuleDto build() {
            StuffModuleDto StuffModuleDto = new StuffModuleDto();
            StuffModuleDto.ads = mAds;
            StuffModuleDto.banners = mBannerDtos;
            StuffModuleDto.moduleId = mModuleId;
            StuffModuleDto.more = mMore;
            StuffModuleDto.styleId = mStyleId;
            StuffModuleDto.title = mTitle;
            StuffModuleDto.titleIcon = mTitleIcon;
            return StuffModuleDto;
        }

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ads", ads)
                .append("banners", banners)
                .append("moduleId", moduleId)
                .append("more", more)
                .append("styleId", styleId)
                .append("title", title)
                .append("titleIcon", titleIcon)
                .toString();
    }
}
