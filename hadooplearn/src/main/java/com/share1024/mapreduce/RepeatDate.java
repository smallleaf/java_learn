package com.share1024.mapreduce;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * @author : yesheng
 * @Description : 数据去重,用reduce返回一次即可
 * @Date : 2018/12/1
 */
public class RepeatDate extends Configured implements Tool {


    public static class RepeateMapper  extends Mapper<Object,Text,Text,Text>{
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            context.write(value,new Text(""));
        }
    }

    public static class RepeateReducer extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            context.write(key,new Text(""));
        }
    }


    @Override
    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance(getConf(),"repeateData");
        job.setJarByClass(RepeatDate.class);
        job.setMapperClass(RepeateMapper.class);
        job.setReducerClass(RepeateReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job,new Path(strings[0]));
        FileOutputFormat.setOutputPath(job,new Path(strings[1]));
        /*提交作业到集群并等待任务完成*/
        boolean isSuccess = job.waitForCompletion(true);
        return isSuccess?0:1;
    }


    public static void main(String[] args) throws Exception {

        int rest = ToolRunner.run(new RepeatDate(),args);
        System.exit(rest);
    }
}
