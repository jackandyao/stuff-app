
package com.qbao.aisr.app.dto;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class BannerDto implements Serializable {
    private static final long serialVersionUID="$Id: BannerDto.java 851 2017-05-17 03:07:29Z wangping $".hashCode();
    @SerializedName("id")
    private Long id;
    @SerializedName("imgUrl")
    private String imgUrl;
    @SerializedName("linkUrl")
    private String linkUrl;
    @SerializedName("locationId")
    private int locationId;
    @SerializedName("name")
    private String name;



    public static class Builder {

        private Long mId;
        private String mImgUrl;
        private String mLinkUrl;
        private int mLocationId;
        private String mName;

        public BannerDto.Builder withId(Long id) {
            mId = id;
            return this;
        }

        public BannerDto.Builder withImgUrl(String imgUrl) {
            mImgUrl = imgUrl;
            return this;
        }

        public BannerDto.Builder withLinkUrl(String linkUrl) {
            mLinkUrl = linkUrl;
            return this;
        }

        public BannerDto.Builder withLocationId(int locationId) {
            mLocationId = locationId;
            return this;
        }

        public BannerDto.Builder withName(String name) {
            mName = name;
            return this;
        }

        public BannerDto build() {
            BannerDto BannerDto = new BannerDto();
            BannerDto.id = mId;
            BannerDto.imgUrl = mImgUrl;
            BannerDto.linkUrl = mLinkUrl;
            BannerDto.locationId = mLocationId;
            BannerDto.name = mName;
            return BannerDto;
        }

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("imgUrl", imgUrl)
                .append("linkUrl", linkUrl)
                .append("locationId", locationId)
                .append("name", name)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
