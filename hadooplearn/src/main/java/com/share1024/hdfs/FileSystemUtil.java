package com.share1024.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/12/1
 */
public class FileSystemUtil {

    private Logger logger = LoggerFactory.getLogger(FileSystemUtil.class);

    private static FileSystemUtil instance = new FileSystemUtil();

    private FileSystemUtil(){}
    private static final String HDFS_PATH = "hdfs://192.168.31.43:9000";

    private static FileSystem fileSystem;

    static {
        System.setProperty("HADOOP_USER_NAME","hadoop") ;
        try {
            fileSystem = FileSystem.get(new URI(HDFS_PATH),new Configuration());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public void uploadFile(String originPath,String desPath){
        Path src = new Path(originPath);
        try {
            deletePath(desPath);
            fileSystem.copyFromLocalFile(false,src,new Path(desPath));
            logger.info("创建文件失败");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile(String path){
        try {
            fileSystem.create(new Path(path));
            logger.info("创建文件:{}",path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void deletePath(String path){
        try {
            fileSystem.delete(new Path(path),true);
            logger.info("删除文件:{}",path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
