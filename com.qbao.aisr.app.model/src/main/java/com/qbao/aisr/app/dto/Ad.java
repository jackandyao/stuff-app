
package com.qbao.aisr.app.dto;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.io.Serializable;
import java.math.BigDecimal;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Ad implements Serializable {

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
    @SerializedName("price")
    private BigDecimal price;
    @SerializedName("rebateValue")
    private String rebateValue;
    @SerializedName("saleCount")
    private int saleCount;
    @SerializedName("source")
    private String source;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRebateValue() {
        return rebateValue;
    }

    public void setRebateValue(String rebateValue) {
        this.rebateValue = rebateValue;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("imgUrl", imgUrl)
                .append("linkUrl", linkUrl)
                .append("locationId", locationId)
                .append("name", name)
                .append("price", price)
                .append("rebateValue", rebateValue)
                .append("saleCount", saleCount)
                .append("source", source)
                .toString();
    }

    public static class Builder {

        private Long mId;
        private String mImgUrl;
        private String mLinkUrl;
        private int mLocationId;
        private String mName;
        private BigDecimal mPrice;
        private String mRebateValue;
        private int mSaleCount;
        private String mSource;

        public Ad.Builder withId(Long id) {
            mId = id;
            return this;
        }

        public Ad.Builder withImgUrl(String imgUrl) {
            mImgUrl = imgUrl;
            return this;
        }

        public Ad.Builder withLinkUrl(String linkUrl) {
            mLinkUrl = linkUrl;
            return this;
        }

        public Ad.Builder withLocationId(int locationId) {
            mLocationId = locationId;
            return this;
        }

        public Ad.Builder withName(String name) {
            mName = name;
            return this;
        }

        public Ad.Builder withPrice(BigDecimal price) {
            mPrice = price;
            return this;
        }

        public Ad.Builder withRebateValue(String rebateValue) {
            mRebateValue = rebateValue;
            return this;
        }

        public Ad.Builder withSaleCount(int saleCount) {
            mSaleCount = saleCount;
            return this;
        }

        public Ad.Builder withSource(String source) {
            mSource = source;
            return this;
        }

        public Ad build() {
            Ad Ad = new Ad();
            Ad.id = mId;
            Ad.imgUrl = mImgUrl;
            Ad.linkUrl = mLinkUrl;
            Ad.locationId = mLocationId;
            Ad.name = mName;
            Ad.price = mPrice;
            Ad.rebateValue = mRebateValue;
            Ad.saleCount = mSaleCount;
            Ad.source = mSource;
            return Ad;
        }


    }

}
