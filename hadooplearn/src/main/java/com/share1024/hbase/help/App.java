package com.share1024.hbase.help;

import com.share1024.hbase.help.config.JavaConfig;
import com.share1024.hbase.help.entity.Email;
import com.share1024.hbase.help.service.EmailService;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.MD5Hash;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-31
 */
public class App {

    private ApplicationContext app;

    @Autowired
    private EmailService emailService;

    private static final String TANLE_NAME = "t_split";

    @Before
    public void before(){
        app = new AnnotationConfigApplicationContext(JavaConfig.class);
        emailService = (EmailService) app.getBean("emailService");
    }


    @Test
    public void batchEmailSave(){

        List<Email> emailList = new ArrayList<>();
        for(int i=3;i<10;i++){
            Email email = new Email();
            email.setContent("this is email:"+i);
            String key = String.join(":","user_"+i,"touser_"+i,(new Date()).getTime()+"","title_"+i);
            email.setId(key);
            emailList.add(email);
        }
        emailService.batchSave(emailList);

    }


    @Test
    /**
     * 行过滤器
     */
    public void testRowFilter(){
        Filter filter = new RowFilter(CompareFilter.CompareOp.LESS_OR_EQUAL,new BinaryComparator(Bytes.toBytes("user_1000")));
        emailService.queryByUserId("t_email",filter);
    }



    @Test
    public void regexStringRowFilter(){

        Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,new RegexStringComparator("user_[0-9]{2}:."));
        emailService.queryByUserId("t_email",filter);

    }

    @Test
    public void startRowFilter(){
        Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,new SubstringComparator("user_10"));
        emailService.queryByUserId("t_email",filter);
    }


    @Test
    public void familyFilter(){
        Filter filter = new FamilyFilter(CompareFilter.CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes("content")));
        emailService.queryByUserId("t_email",filter);
    }



    @Test
    public void qualifierFilter(){
        Filter filter = new QualifierFilter(CompareFilter.CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes("data")));
        emailService.queryByUserId("t_email",filter);
    }


    @Test
    public void valueFilter(){
        Filter filter = new ValueFilter(CompareFilter.CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes("this is email:598")));
        emailService.queryByUserId("t_email",filter);

    }


    @Test
    public void simpleColumnValueFilter(){

        Filter filter = new SingleColumnValueFilter(Bytes.toBytes("content"),Bytes.toBytes("data"), CompareFilter.CompareOp.EQUAL,
                new BinaryComparator(Bytes.toBytes("this is email:598")));
        emailService.queryByUserId("t_email",filter);

    }

    @Test
    public void simpleColumnValueExcludeFilter(){
        Filter filter = new SingleColumnValueExcludeFilter(Bytes.toBytes("content"),Bytes.toBytes("data"), CompareFilter.CompareOp.EQUAL,
                new BinaryComparator(Bytes.toBytes("this is email:598")));
        emailService.queryByUserId("t_email",filter);
    }

    @Test
    public void prefixFilter(){

        Filter filter = new PrefixFilter(Bytes.toBytes("user_10"));
        emailService.queryByUserId("t_email",filter);

    }

    @Test
    public void pageFilter(){
       emailService.queryByPage(15,"t_email");
    }


    @Test
    public void testPageFilter(){
        emailService.queryByPage(15,"t_email","user_144:touser_144:1546351099157:title_144");
    }


    /**
     * 只返回行key
     */
    @Test
    public void keyOnlyFilter(){
        Filter filterPrex = new PrefixFilter(Bytes.toBytes("user_10"));
        Filter filter = new KeyOnlyFilter();
        FilterList filterList = new FilterList();
        filterList.addFilter(filter);
        filterList.addFilter(filterPrex);
        emailService.queryByUserId("t_email",filterList);
    }


    @Test
    public void timeStampFilter(){


    }

    @Test
    public void skipFIlter(){
        Filter filter = new ValueFilter(CompareFilter.CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes("this is email:598")));
        Filter skipFilter = new SkipFilter(filter);
        emailService.queryByUserId("t_email",skipFilter);
    }



}
