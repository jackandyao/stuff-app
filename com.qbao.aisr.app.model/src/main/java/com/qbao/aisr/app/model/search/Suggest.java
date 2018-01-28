package com.qbao.aisr.app.model.search;

import java.io.Serializable;

/**
 * @author shuaizhihu
 * @createTime 2017/3/11 18:38
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
public class Suggest implements Serializable {
    private static final long serialVersionUID="$Id$".hashCode();
    private String keyword;
    /**
     * 搜索次数（约值）
     */
    private long searchCount;

    /**
     * 结果数量（约值）
     */
    private long resultNum;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public long getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(long searchCount) {
        this.searchCount = searchCount;
    }

    public long getResultNum() {
        return resultNum;
    }

    public void setResultNum(long resultNum) {
        this.resultNum = resultNum;
    }
}
