package com.qbao.aisr.app.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shuaizhihu
 * @createTime 2017/3/12 15:44
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
public class QbaoZyClass implements Serializable {

    private long  id;
    private int sort;
    private String dirName;
    private String url;
    private Date createTime;
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
