package com.share1024.hbase.help.service;

import com.share1024.hbase.help.config.HBaseDaoUtil;
import com.share1024.hbase.help.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-31
 */
@Service
public class LogService {

    @Autowired
    private HBaseDaoUtil hBaseDaoUtil;


    public void save(Log log){
        hBaseDaoUtil.save(Arrays.asList(log));
    }


    public void saveBatch(List<Log> logList){
        hBaseDaoUtil.save(logList);
    }
}
