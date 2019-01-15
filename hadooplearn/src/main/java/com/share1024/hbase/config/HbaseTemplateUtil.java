package com.share1024.hbase.config;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.ResultsExtractor;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author : yesheng
 * @Description :  hbase基本操作
 * @Date : 2017/12/29
 */
@Service
public class HbaseTemplateUtil {

    private static final String TABLE_NAME = "hbase_test";

    @Autowired
    private HbaseTemplate hbaseTemplate;


    public void add(){
        Put put = new Put(Bytes.toBytes("row3"));
        put.addColumn(Bytes.toBytes("data"),Bytes.toBytes("username"),Bytes.toBytes("yesheng-spring"));
        put.addColumn(Bytes.toBytes("data"),Bytes.toBytes("password"),Bytes.toBytes("xiaoyezi-spring"));
        hbaseTemplate.put(TABLE_NAME,"row3","data","username",Bytes.toBytes("yesheng-spring"));
        hbaseTemplate.put(TABLE_NAME,"row3","data","password",Bytes.toBytes("xiaoyezi-spring"));

    }

    public void get(){
        hbaseTemplate.get(TABLE_NAME, "row3", new RowMapper<Object>() {
            public Object mapRow(Result result, int i) throws Exception {
                List<Cell> cellList = result.listCells();
                for(Cell cell : cellList){
                    System.out.println(Bytes.toString(cell.getQualifier())+":"+Bytes.toString(cell.getValue()));
                }
                return null;
            }
        });
    }

    public void scan(){
        Scan scan = new Scan();
        hbaseTemplate.find(TABLE_NAME, scan, new ResultsExtractor<Object>() {
            public Object extractData(ResultScanner resultScanner) throws Exception {
                for(Result result : resultScanner){
                    List<Cell> cellList = result.listCells();
                    for(Cell cell : cellList){
                        System.out.println(Bytes.toString(CellUtil.cloneRow(cell))+":"+Bytes.toString(CellUtil.cloneFamily(cell))+":"+Bytes.toString(CellUtil.cloneQualifier(cell))+":"+Bytes.toString(CellUtil.cloneValue(cell)));
                    }
                }
                return null;
            }
        });
    }


    public void createFakeData(){
        String tableName = "fake_table";
        for(int i=0;i<100000;i++){
            String row = "row:"+i+":"+new Date().getTime();
            hbaseTemplate.put(tableName,row,"data","id",Bytes.toBytes(i));
            hbaseTemplate.put(tableName,row,"data","name",Bytes.toBytes("yesheng"+i));
            hbaseTemplate.put(tableName,row,"data","age",Bytes.toBytes(new Random(100l).nextInt()));
        }
    }


    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(HadoopConfig.class);
        HbaseTemplateUtil hbaseTemplateUtil = (HbaseTemplateUtil) applicationContext.getBean("hbaseTemplateUtil");
        hbaseTemplateUtil.createFakeData();
    }
}
