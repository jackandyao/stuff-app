package com.qbao.aisr.app.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author liaijun
 * @createTime 17/4/1 上午10:49
 * $$LastChangedDate: 2017-04-01 17:27:04 +0800 (Sat, 01 Apr 2017) $$
 * $$LastChangedRevision: 625 $$
 * $$LastChangedBy: liaijun $$
 */
public class BannerStuffConfDto implements Serializable {
    private static final long serialVersionUID = "$Id: BannerStuffConfDto.java 625 2017-04-01 09:27:04Z liaijun $".hashCode();

    private String name;

    private String imgUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<BannerStuffInfoDto> getDetails() {
        return details;
    }

    public void setDetails(List<BannerStuffInfoDto> details) {
        this.details = details;
    }

    private List<BannerStuffInfoDto> details;

    @Override
    public String toString() {
        return "BannerStuffConfDto{" + "name='" + name + '\'' + ", imgUrl='" + imgUrl + '\'' + ", details=" + details + '}';
    }
}
