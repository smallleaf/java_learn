package com.share1024.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.MD5Hash;

import java.io.IOException;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2017/12/29
 */
public class HbaseAdminTest {

    private Configuration conf;

    private static final String TABLE_NAME="t_email";

    public HbaseAdminTest(){
//        conf = HBaseConfiguration.create();
    }


    public boolean isExistTable(String tableNameStr) throws IOException {
        Admin hBaseAdmin = ConnectionFactory.createConnection().getAdmin();
        TableName tableName = TableName.valueOf(tableNameStr);
        if(hBaseAdmin.tableExists(tableName)){
            return  true;
        }

        return  false;
    }

    public void printAlltable() throws IOException {
        Admin hBaseAdmin = ConnectionFactory.createConnection().getAdmin();
        TableName[] tableNames = hBaseAdmin.listTableNames();
        for(TableName tableName : tableNames){
            System.out.println(tableName.getNameAsString());
        }
    }

    public void createTable(String tableName) throws IOException {
        if(isExistTable(tableName)){
            return;
        }
        TableName tn = TableName.valueOf(tableName);
        HTableDescriptor descriptor = new HTableDescriptor(tn);
        HColumnDescriptor columnDescriptor1 = new HColumnDescriptor("data");
        descriptor.addFamily(columnDescriptor1);
        Admin admin = ConnectionFactory.createConnection().getAdmin();
        admin.createTable(descriptor);

    }

    public void addData() throws IOException {
        createTable(TABLE_NAME);

        Table table = ConnectionFactory.createConnection().getTable(TableName.valueOf(TABLE_NAME));
        Put put = new Put(Bytes.add(MD5Hash.getMD5AsHex(Bytes.toBytes("row")).substring(0, 8).getBytes(),Bytes.toBytes("row")));
        put.addColumn(Bytes.toBytes("content"),Bytes.toBytes("data"),Bytes.toBytes("yesheng"));
        table.put(put);

    }


    public void get() throws IOException {
        Table table = ConnectionFactory.createConnection().getTable(TableName.valueOf(TABLE_NAME));

        Get get = new Get(Bytes.toBytes("row2"));
        Result result = table.get(get);
        System.out.println(Bytes.toString(result.getValue(Bytes.toBytes("data"),Bytes.toBytes("username"))));

    }

    public void scan() throws IOException {
        Table table = ConnectionFactory.createConnection().getTable(TableName.valueOf(TABLE_NAME));

        ResultScanner results = table.getScanner(Bytes.toBytes("data"));

        for(Result result : results){
            System.out.println(Bytes.toString(result.getValue(Bytes.toBytes("data"),Bytes.toBytes("username"))));

        }

    }





    public static void main(String[] args) throws IOException {
        HbaseAdminTest hbaseAdminTest = new HbaseAdminTest();
//        String tableName = "test";
//        if(hbaseAdminTest.isExistTable(tableName)){
//            System.out.println("存在");
//        }
        //hbaseAdminTest.printAlltable();

//        hbaseAdminTest.createTable("hbase_test");

        hbaseAdminTest.addData();

//        hbaseAdminTest.get();

//        hbaseAdminTest.scan();
    }



}
