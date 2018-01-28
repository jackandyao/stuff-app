
package com.qbao.aisr.app.dto;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class TopSearchKeyDto {

    @SerializedName("key")
    private String key;//热搜关键字
    @SerializedName("url")
    private String url;//跳转链接


    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }

    public static class Builder {

        private String key;//热搜关键字
        private String url;//跳转链接

        public TopSearchKeyDto.Builder withKey(String key) {
            if(key!=null){
                this.key = key;
            }else{
                this.key = "";
            }

            return this;
        }
        public TopSearchKeyDto.Builder withUrl(String url) {
            if(url!=null){
                this.url = url;
            }else{
                this.url = "";
            }

            return this;
        }
        public TopSearchKeyDto build() {
            TopSearchKeyDto topSearchKeyDto = new TopSearchKeyDto();
            topSearchKeyDto.key = this.key;
            topSearchKeyDto.url = this.url;
            return topSearchKeyDto;
        }

    }

}
