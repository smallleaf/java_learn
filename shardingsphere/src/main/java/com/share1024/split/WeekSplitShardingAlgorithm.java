package com.share1024.split;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Collection;
import java.util.Date;

/**
 * \* @Author: yesheng
 * \* Date: 2021/2/7 11:30
 * \* Description:
 * \
 */
public class WeekSplitShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        Long createTime = shardingValue.getValue();
        Date date = new Date(createTime);
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,1);
        int week = localDateTime.get(weekFields.weekOfYear());
        int year = localDateTime.getYear();
        String tableName = shardingValue.getLogicTableName()+"_" + year +"_" + week;
        if(availableTargetNames.contains(tableName)){
            return tableName;
        }
        return null;
    }
}