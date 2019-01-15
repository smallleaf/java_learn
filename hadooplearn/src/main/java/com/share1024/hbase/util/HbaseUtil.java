package com.share1024.hbase.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2017/11/29
 */
public class HbaseUtil {

    private Configuration conf;

    public HbaseUtil(){
        conf = HBaseConfiguration.create();
    }

    public boolean existTable(String tableName) throws IOException {
        TableName tblName = TableName.valueOf(Bytes.toBytes(tableName));
        Admin admin = ConnectionFactory.createConnection().getAdmin();
        if (admin.tableExists(tblName))
            return true;

        return false;
    }

    public boolean createTable(String tableName,String ... cols){


        TableName tblName = TableName.valueOf(Bytes.toBytes(tableName));
        HTableDescriptor tableDescriptor = new HTableDescriptor(tblName);
        tableDescriptor.addFamily(new HColumnDescriptor(Bytes.toBytes("cf1")));
        tableDescriptor.addFamily(new HColumnDescriptor(Bytes.toBytes("cf2")));
        try {
            Connection conn = ConnectionFactory.createConnection();
            conn.getAdmin().createTable(tableDescriptor);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        HbaseUtil util = new HbaseUtil();
        String tableName = "clientTable2";
        if(!util.existTable(tableName)){
            System.out.println("===");
            util.createTable(tableName);
        }
    }
}
