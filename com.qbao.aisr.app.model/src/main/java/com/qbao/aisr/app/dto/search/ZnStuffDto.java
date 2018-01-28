package com.qbao.aisr.app.dto.search;




import org.dozer.Mapping;

import java.io.Serializable;

/**
 * @author shuaizhihu
 * @createTime 2017/3/11 12:16
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
public class ZnStuffDto implements Serializable{

    @Mapping("productId")
    private long stuffId;
    @Mapping("productName")
    private String name;
    @Mapping("productPrice")
    private long viewPrice;
    @Mapping("mainImg")
    private String imgUrl;
    @Mapping("saleNum")
    private int saleCount;
    private String source="qbao";
    private Float haohuoScore;
    private String haohuoUrl;

    public long getStuffId() {
        return stuffId;
    }

    public void setStuffId(long stuffId) {
        this.stuffId = stuffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getViewPrice() {
        return viewPrice;
    }

    public void setViewPrice(long viewPrice) {
        this.viewPrice = viewPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public Float getHaohuoScore() {
        return haohuoScore;
    }

    public void setHaohuoScore(Float haohuoScore) {
        this.haohuoScore = haohuoScore;
    }

    public String getHaohuoUrl() {
        return haohuoUrl;
    }

    public void setHaohuoUrl(String haohuoUrl) {
        this.haohuoUrl = haohuoUrl;
    }
}
