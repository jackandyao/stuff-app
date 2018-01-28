package com.qbao.aisr.app.dto;

import java.io.Serializable;

/**
 * @author shuaizhihu
 * @createTime 2017/3/12 16:32
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
public class QbaoZyClassDto implements Serializable{
    private long id;
    private String dirName;

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
