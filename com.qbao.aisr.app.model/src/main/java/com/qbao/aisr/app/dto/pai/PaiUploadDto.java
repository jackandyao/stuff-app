package com.qbao.aisr.app.dto.pai;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author shuaizhihu
 * @createTime 2017/3/12 17:43
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
public class PaiUploadDto implements Serializable{

    private String key;
    private String src;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("key", key).append("src", src).toString();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
