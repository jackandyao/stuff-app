package com.qbao.aisr.app.reids;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangping
 * @createTime 2017/3/21 上午9:24
 * $$LastChangedDate: 2017-03-21 15:16:32 +0800 (Tue, 21 Mar 2017) $$
 * $$LastChangedRevision: 383 $$
 * $$LastChangedBy: wangping $$
 */
public class Event implements Serializable {

    private static final long serialVersionUID = "$Id: Event.java 383 2017-03-21 07:16:32Z wangping $".hashCode();

    private List<Long> click = new ArrayList<>();
    private List<Long> favorites = new ArrayList<>();

    public List<Long> getClick() {
        return click;
    }

    public void setClick(List<Long> click) {
        this.click = click;
    }

    public List<Long> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Long> favorites) {
        this.favorites = favorites;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(ToStringStyle.SIMPLE_STYLE);
    }
}
