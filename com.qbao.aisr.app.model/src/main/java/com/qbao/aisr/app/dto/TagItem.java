
package com.qbao.aisr.app.dto;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class TagItem {

    @SerializedName("check")
    private Boolean mCheck;
    @SerializedName("count")
    private Long mCount;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("name")
    private String mName;
    @SerializedName("tag_detail_id")
    private Long mTagDetailId;

    public Boolean getCheck() {
        return mCheck;
    }

    public Long getCount() {
        return mCount;
    }

    public String getIcon() {
        return mIcon;
    }

    public String getName() {
        return mName;
    }

    public Long getTagDetailId() {
        return mTagDetailId;
    }

    public static class Builder {

        private Boolean mCheck;
        private Long mCount;
        private String mIcon;
        private String mName;
        private Long mTagDetailId;

        public TagItem.Builder withCheck(Boolean check) {
            mCheck = check;
            return this;
        }

        public TagItem.Builder withCount(Long count) {
            mCount = count;
            return this;
        }

        public TagItem.Builder withIcon(String icon) {
            mIcon = icon;
            return this;
        }

        public TagItem.Builder withName(String name) {
            mName = name;
            return this;
        }

        public TagItem.Builder withTagDetailId(Long tagDetailId) {
            mTagDetailId = tagDetailId;
            return this;
        }

        public TagItem build() {
            TagItem TagItem = new TagItem();
            TagItem.mCheck = mCheck;
            TagItem.mCount = mCount;
            TagItem.mIcon = mIcon;
            TagItem.mName = mName;
            TagItem.mTagDetailId = mTagDetailId;
            return TagItem;
        }

    }

}
