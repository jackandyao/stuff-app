package com.qbao.aisr.app.dto;

import java.io.Serializable;

/**
 * @author liaijun
 * @createTime 17/3/2 下午5:41
 * $$LastChangedDate: 2017-05-09 14:04:32 +0800 (Tue, 09 May 2017) $$
 * $$LastChangedRevision: 793 $$
 * $$LastChangedBy: wangping $$
 */
public class UserStuffPaiDto implements Serializable {
    private static final long serialVersionUID = "$Id: UserStuffPaiDto.java 793 2017-05-09 06:04:32Z wangping $".hashCode();
    //
    private Long id;
    // 图片路径
    private String imgUrl;

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

    @Override
    public String toString() {
        return "UserStuffPaiDto{" + "id=" + id + ", imgUrl='" + imgUrl + '\'' + '}';
    }
}
