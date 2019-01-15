package com.share1024.hdfs.filemerge;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.net.URI;

/**
 * 小文件合并
 * @author huang
 *
 */
public class MegerSmallFiles {
	//写入到HDFS的FileSystem对象
	private static FileSystem fs = null ;
	
	//本地文件系统的FileSystem
	private static FileSystem local = null ;
	
	//HDFS服务路径
	private static final String HDFS_SERVER = "hdfs://192.168.31.43:9000" ;
	
	//合并小文件的主要方法
	public static void megerFiles() throws Exception {
		//设置系统用户为hadoop
		System.setProperty("HADOOP_USER_NAME", "hadoop") ;
		
		//读取hadoop文件的配置信息
		Configuration conf = new Configuration() ;
		
		//创建URI
		URI uri = new URI(HDFS_SERVER) ;
		
		//创建两个文件系统的fs
		fs = FileSystem.get(uri, conf) ;	//针对HDFS
		local = FileSystem.get(conf) ;		//针对本地文件系统
		
		/* 获取指定路径下的所有文件
		 * 过滤该路径下的所有svn文件
		 *  ^匹配一行的开头 ；.表示匹配任意一个字符
		 *  *表示匹配0个或多个前面这个字符 ；$匹配一行的结束
		 * */
		FileStatus[] globStatus = local.globStatus(new Path("/Users/yesheng/Documents/软件开发/workspace/learnproject/hadooplearn/file/smallfile/*"),
				new RegexUncludeFilter("^.*svn$"));
		//调试输出
		for (FileStatus fileStatus : globStatus) {
			System.out.println(fileStatus.getPath().toString());
		}
		
		//将一组FileStatus对象转换成Path对象
		Path[] dirs = FileUtil.stat2Paths(globStatus);
		
		//获取输入输出流
		FSDataOutputStream out = null ;
		FSDataInputStream in = null ;
		
		for (Path dir : dirs) {	//具体的每个目录下面的所有文件
			//文件名称
			String fileName = dir.getName().replaceAll("-", "") ;
			//只接受该目录下的txt文件
			FileStatus[] txtPaths = local.globStatus(new Path(dir + "/*") ,
					new RegexAcceptFilter("^.*txt$"));
			Path[] txtFiles = FileUtil.stat2Paths(txtPaths);
			
			//设置输出路径
			Path hdfsFile = new Path(HDFS_SERVER + "/vip/" + fileName + ".txt") ;
			
			//打开输入输出流，进行读写
			out = fs.create(hdfsFile) ;	//输出流
			for (Path p : txtFiles) {
				in = local.open(p) ;
				IOUtils.copyBytes(in, out, 4096, false);
				//关闭输入流
				in.close();
			}
			if(null != out){
				out.close();
			}
		}
	}
	
	//程序入口
	public static void main(String[] args) throws Exception {
		megerFiles() ;
		System.out.println("=====小文件合并成功=====");
	}

}
