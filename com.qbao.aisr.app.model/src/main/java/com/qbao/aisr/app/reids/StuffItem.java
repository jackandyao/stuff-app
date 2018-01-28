package com.qbao.aisr.app.reids;

/**
 * @author wangping
 * @createTime 2017/3/21 上午9:25
 * $$LastChangedDate: 2017-03-21 15:16:32 +0800 (Tue, 21 Mar 2017) $$
 * $$LastChangedRevision: 383 $$
 * $$LastChangedBy: wangping $$
 */
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

public class StuffItem implements Serializable {
    private static final long serialVersionUID = "$Id: StuffItem.java 383 2017-03-21 07:16:32Z wangping $".hashCode();
    private Long stuffId;
    private String source;// jd, tmall, taobao, qbao
    private double score;

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Long getStuffId() {
        return this.stuffId;
    }

    public void setStuffId(Long stuffId) {
        this.stuffId = stuffId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(ToStringStyle.MULTI_LINE_STYLE);
    }
}