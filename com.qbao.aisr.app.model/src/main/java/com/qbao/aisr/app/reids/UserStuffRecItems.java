package com.qbao.aisr.app.reids;

/**
 * @author wangping
 * @createTime 2017/3/21 上午9:23
 * $$LastChangedDate: 2017-03-21 15:16:32 +0800 (Tue, 21 Mar 2017) $$
 * $$LastChangedRevision: 383 $$
 * $$LastChangedBy: wangping $$
 */

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;

public class UserStuffRecItems implements Serializable {

    private static final long serialVersionUID = "$Id: UserStuffRecItems.java 383 2017-03-21 07:16:32Z wangping $".hashCode();
    private boolean flag;
    private ArrayList<StuffItem> goodsItems;
    private ArrayList<StuffItem> similaryItems;
    private ArrayList<StuffItem> directoryItems;
    private String userId;
    private Event event;
    private String createTime;
    private String updateTime;


    public ArrayList<StuffItem> getSimilaryItems() {
        return similaryItems;
    }

    public void setSimilaryItems(ArrayList<StuffItem> similaryItems) {
        this.similaryItems = similaryItems;
    }

    public ArrayList<StuffItem> getDirectoryItems() {
        return directoryItems;
    }

    public void setDirectoryItems(ArrayList<StuffItem> directoryItems) {
        this.directoryItems = directoryItems;
    }

    public boolean getFlag() {
        return this.flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public ArrayList<StuffItem> getGoodsItems() {
        return this.goodsItems;
    }

    public void setGoodsItems(ArrayList<StuffItem> goodsItems) {
        this.goodsItems = goodsItems;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public static UserStuffRecItems createDefault(){
        UserStuffRecItems userItems = new UserStuffRecItems();
        userItems.setGoodsItems(new ArrayList<StuffItem>());
        Event event = new Event();
        userItems.setEvent(event );
        return userItems;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(ToStringStyle.MULTI_LINE_STYLE);
    }

}
