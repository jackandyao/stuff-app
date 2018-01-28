
package com.qbao.aisr.app.dto;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.util.Date;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class TopSearchStuffComplexDto {

    @SerializedName("id")
    private long id;//统一商品id
    @SerializedName("name")
    private String name;//商品名称
    @SerializedName("imgUrl")
    private String imgUrl;//商品图片链接
    @SerializedName("url")
    private String url;//商品链接
    @SerializedName("finalPrice")
    private BigDecimal finalPrice;
    @SerializedName("rebateValue")
    private String rebateValue;//返利多少
    @SerializedName("source")
    private String source;//商品来源:taobao,tmall,jd

    // 统计数
    @SerializedName("orderNum")
    private Integer orderNum;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public String getRebateValue() {
        return rebateValue;
    }

    public String getSource() {
        return source;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public static class Builder {

        private long id;//统一商品id
        private String name;//商品名称
        private String imgUrl;//商品图片链接
        private String url;//商品链接
        private BigDecimal finalPrice;
        private String rebateValue;//返利多少
        private String source;//商品来源:taobao,tmall,jd
        private Integer orderNum;

        public TopSearchStuffComplexDto.Builder withId(long id) {
            this.id = id;
            return this;
        }

        public TopSearchStuffComplexDto.Builder withName(String name) {
            if(name!=null){
                this.name = name;
            }else{
                this.name = "";
            }

            return this;
        }

        public TopSearchStuffComplexDto.Builder withImgUrl(String imgUrl) {
            if(imgUrl!=null){
                this.imgUrl = imgUrl;
            }else{
                this.imgUrl = "";
            }

            return this;
        }
        public TopSearchStuffComplexDto.Builder withUrl(String url) {
            if(url!=null){
                this.url = url;
            }else{
                this.url = "";
            }

            return this;
        }

        public TopSearchStuffComplexDto.Builder withFinalPrice(BigDecimal finalPrice) {
            this.finalPrice = finalPrice;
            return this;
        }

        public TopSearchStuffComplexDto.Builder withRebateValue(String rebateValue) {
            this.rebateValue = rebateValue;
            return this;
        }
        public TopSearchStuffComplexDto.Builder withSource(String source) {
            if(source!=null){
                this.source = source;
            }else{
                source="";
            }

            return this;
        }
        public TopSearchStuffComplexDto.Builder withOrderNum(Integer orderNum) {
            if(orderNum!=null){
                this.orderNum = orderNum;
            }else{
                orderNum=0;
            }

            return this;
        }


        public TopSearchStuffComplexDto build() {
            TopSearchStuffComplexDto topSearchStuffComplexDto = new TopSearchStuffComplexDto();
            topSearchStuffComplexDto.id = this.id;
            topSearchStuffComplexDto.name = this.name;
            topSearchStuffComplexDto.imgUrl = this.imgUrl;
            topSearchStuffComplexDto.url = this.url;
            topSearchStuffComplexDto.finalPrice = this.finalPrice;
            topSearchStuffComplexDto.rebateValue = this.rebateValue;
            topSearchStuffComplexDto.source = this.source;
            topSearchStuffComplexDto.orderNum = this.orderNum;
            return topSearchStuffComplexDto;
        }

    }

}
