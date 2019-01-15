package com.share1024.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;


/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/25
 */
public class HdfsDemo {

    private static final String HDFS_PATH = "hdfs://192.168.31.43:9000";

    FileSystem fileSystem;
    @Before
    public void before() throws Exception {
        System.setProperty("HADOOP_USER_NAME","hadoop") ;
        fileSystem = FileSystem.get(new URI(HDFS_PATH),new Configuration());
    }

    @Test
    public void downFileTest() throws Exception {

        FSDataInputStream in = fileSystem.open(new Path("/test/README.txt"));
        File targetFile = new File("/Users/yesheng/Documents/软件开发/workspace/learnproject/hadooplearn/file/test.txt");
        FileOutputStream out = new FileOutputStream(targetFile);

        IOUtils.copyBytes(in,out,4096,true);
        System.out.println("文件下载成功");
    }

    @Test
    public void upLoadFileTest() throws Exception{
        Path src = new Path("/Users/yesheng/Documents/软件开发/workspace/learnproject/hadooplearn/file/repeatData.txt");
        fileSystem.copyFromLocalFile(false,src,new Path("/data/repeat-2"));
        System.out.println("===文件上传成功====");
    }




    @Test
    public void createFileDir() throws IOException {
        fileSystem.mkdirs(new Path("/test2"));
    }

    @After
    public void after() throws IOException {
        fileSystem.close();
    }

}
