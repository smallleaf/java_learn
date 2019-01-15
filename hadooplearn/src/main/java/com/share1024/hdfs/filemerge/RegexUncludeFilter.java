package com.share1024.hdfs.filemerge;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

/**
 * @author : yesheng
 * @Description : 文件过滤接受
 * @Date : 2018/11/30
 */
public class RegexUncludeFilter  implements PathFilter {

    private final String regex ;

    public RegexUncludeFilter(String regex){
        this.regex = regex ;
    }

    @Override
    public boolean accept(Path path) {
        return !path.toString().matches(regex);
    }
}
