package com.qbao.aisr.app.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author liaijun
 * @email liaijun@qbao.com
 * @date 2017-03-10 16:50:57
 */
public class StuffHotClassDto implements Serializable {
    private static final long serialVersionUID = "$Id: StuffHotClassDto.java 166 2017-03-12 11:06:46Z allen $".hashCode();

    //
    private Long id;

    //
    private String dirName;


    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }



    /**
     * 设置：
     */
    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    /**
     * 获取：
     */
    public String getDirName() {
        return dirName;
    }

    @Override
    public String toString() {
        return "StuffHotClassDto{" +
                "id=" + id +
                ", dirName='" + dirName + '\'' +
                '}';
    }
}
