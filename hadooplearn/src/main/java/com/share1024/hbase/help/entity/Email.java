package com.share1024.hbase.help.entity;

import com.share1024.hbase.help.config.HbaseColumn;
import com.share1024.hbase.help.config.HbaseTable;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2019-01-01
 */
@HbaseTable(tableName = "t_email")
public class Email {
    private String id;

    @HbaseColumn(family = "content",qualifier = "data")
    private String content;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
