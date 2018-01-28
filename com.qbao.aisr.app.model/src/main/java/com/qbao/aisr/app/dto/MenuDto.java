
package com.qbao.aisr.app.dto;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class MenuDto implements Serializable {

    @SerializedName("icon_url")
    private String iconUrl;
    @SerializedName("link_url")
    private String linkUrl;
    @SerializedName("name")
    private String name;

    public String getIconUrl() {
        return iconUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public String getName() {
        return name;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("iconUrl", iconUrl)
                .append("linkUrl", linkUrl)
                .append("name", name)
                .toString();
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder {

        private String mIconUrl;
        private String mLinkUrl;
        private String mName;

        public MenuDto.Builder withIconUrl(String iconUrl) {
            mIconUrl = iconUrl;
            return this;
        }

        public MenuDto.Builder withLinkUrl(String linkUrl) {
            mLinkUrl = linkUrl;
            return this;
        }

        public MenuDto.Builder withName(String name) {
            mName = name;
            return this;
        }

        public MenuDto build() {
            MenuDto MenuDto = new MenuDto();
            MenuDto.iconUrl = mIconUrl;
            MenuDto.linkUrl = mLinkUrl;
            MenuDto.name = mName;
            return MenuDto;
        }

    }

}
