package com.share1024.hbase.help.service;

import com.share1024.hbase.help.config.HBaseDaoUtil;
import com.share1024.hbase.help.config.HconnectionFactory;
import com.share1024.hbase.help.entity.Email;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2019-01-01
 */
@Service
public class EmailService {


    @Autowired
    private HBaseDaoUtil hBaseDaoUtil;

    public void batchSave(List<Email> emails){
        hBaseDaoUtil.save(emails);
    }

    public void queryByUserId(String tableName,Filter filter){
        ResultScanner scanner =hBaseDaoUtil.queryScan(tableName,filter);
        if(scanner!=null){
            for (Result result : scanner) {
                System.out.println(result);
                System.out.println(Bytes.toString(result.getValue(Bytes.toBytes("content"),Bytes.toBytes("data"))));
            }
        }
    }

    public void queryByPage(int size,String tableName,String startRow) {
        try (Table table = HconnectionFactory.connection.getTable(TableName.valueOf(tableName)); Admin admin = HconnectionFactory.connection.getAdmin();) {
            Filter filter = new PageFilter(size);
            Scan scan = new Scan();
            scan.setFilter(filter);
            scan.setStartRow(Bytes.toBytes(startRow));
            ResultScanner scanner = table.getScanner(scan);
            for (Result result : scanner) {
                System.out.println(result);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void queryByPage(int size,String tableName) {

        try (Table table = HconnectionFactory.connection.getTable(TableName.valueOf(tableName)); Admin admin = HconnectionFactory.connection.getAdmin();) {

            Filter filter = new PageFilter(size);
            int totalRows = 0;
            byte[] lastRow = null;

            while(true){
                Scan scan = new Scan();
                scan.setFilter(filter);
                if(lastRow !=null){
                    byte[] startRow = Bytes.add(lastRow,Bytes.toBytes("postfix"));
                    System.out.println("start now:"+Bytes.toString(startRow));
                    scan.setStartRow(startRow);
                }
                ResultScanner scanner = table.getScanner(scan);
                int localRows = 0;
                Result result ;
                while((result = scanner.next()) != null){
                    System.out.println(localRows++ +":"+result);
                    totalRows ++;
                    lastRow = result.getRow();
                }
                scanner.close();
                if(localRows == 0){
                    break;
                }
            }
            System.out.println("total:"+totalRows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
