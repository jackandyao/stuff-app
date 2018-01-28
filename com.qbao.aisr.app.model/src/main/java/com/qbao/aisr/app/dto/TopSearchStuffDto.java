
package com.qbao.aisr.app.dto;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.Date;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class TopSearchStuffDto {

    @SerializedName("id")
    private long id;
    @SerializedName("stuffId")
    private Long stuffId;
    @SerializedName("count")
    private Integer count;
    @SerializedName("createTime")
    private Date createTime;

    public long getId() {
        return id;
    }

    public Long getStuffId() {
        return stuffId;
    }

    public Integer getCount() {
        return count;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public static class Builder {

        private long id;
        private Long stuffId;
        private Integer count;
        private Date createTime;

        public TopSearchStuffDto.Builder withId(long id) {
            id = id;
            return this;
        }

        public TopSearchStuffDto.Builder withStuffId(Long stuffId) {
            stuffId = stuffId;
            return this;
        }

        public TopSearchStuffDto.Builder withCount(Integer count) {
            count = count;
            return this;
        }
        public TopSearchStuffDto.Builder withCount(Date createTime) {
            createTime = createTime;
            return this;
        }

        public TopSearchStuffDto build() {
            TopSearchStuffDto topSearchStuffDto = new TopSearchStuffDto();
            topSearchStuffDto.id = id;
            topSearchStuffDto.stuffId = stuffId;
            topSearchStuffDto.count = count;
            topSearchStuffDto.createTime = createTime;
            return topSearchStuffDto;
        }

    }

}
