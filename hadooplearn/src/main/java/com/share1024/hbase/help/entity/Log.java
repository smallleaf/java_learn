package com.share1024.hbase.help.entity;

import com.share1024.hbase.help.config.HbaseColumn;
import com.share1024.hbase.help.config.HbaseTable;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-31
 */
@HbaseTable(tableName = "t_log")
public class Log  {

    private String id;

    @HbaseColumn(family = "data",qualifier = "url")
    private String url;

    @HbaseColumn(family = "data",qualifier = "requestTime")
    private String requestTime;

    @HbaseColumn(family = "data",qualifier = "responseTime")
    private String responseTime;

    @HbaseColumn(family = "content",qualifier = "responseTime")
    private String ip;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
