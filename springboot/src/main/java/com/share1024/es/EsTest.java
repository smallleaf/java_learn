package com.share1024.es;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.GetSourceRequest;
import org.elasticsearch.client.core.GetSourceResponse;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;

import java.io.IOException;

/**
 * \* @Author: yesheng
 * \* Date: 2021/5/7 20:25
 * \* Description:
 * \
 */
public class EsTest {

    private static final String index = "website";
    private static final String hostName = "";
    private static final int port = 0;
    public RestHighLevelClient client(){
        return  new RestHighLevelClient(RestClient.builder(new HttpHost(hostName,port,"http")));
    }

    @Test
    public void get() throws IOException {
        GetRequest getRequest = new GetRequest(index+"2","90205");
        getRequest.type("blog");
        try {
            GetResponse getResponse = client().get(getRequest, RequestOptions.DEFAULT);
            System.out.println("=="+getResponse);
        }catch (ElasticsearchException e){
            if(e.status() == RestStatus.NOT_FOUND){
                System.out.println("===");
            }
        }
    }

    @Test
    public void getSource(){
        GetSourceRequest getSourceRequest = new GetSourceRequest(index,"123");
        getSourceRequest.type("blog");
        getSourceRequest.fetchSourceContext(new FetchSourceContext(true, Strings.EMPTY_ARRAY,new String[]{"title"}));
        try {
            GetSourceResponse response = client().getSource(getSourceRequest,RequestOptions.DEFAULT);
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void exists(){
        GetRequest getRequest = new GetRequest(index+"2","90205");
        getRequest.type("blog");
        try {
            System.out.println(client().exists(getRequest,RequestOptions.DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void index() throws IOException {
//        IndexRequest indexRequest = new IndexRequest("website","blog","123");
        IndexRequest indexRequest = new IndexRequest("website","blog");
        WebSite webSite = new WebSite("My first blog entry","Just trying this out...4","2014/01/01");
        indexRequest.source(JSON.toJSONString(webSite), XContentType.JSON);
        IndexResponse response = client().index(indexRequest,RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

    @Test
    public void update(){
        UpdateRequest updateRequest = new UpdateRequest(index,"blog","123");
        WebSite webSite = new WebSite("My first blog entry","Just trying this out...5","2014/01/01");
        updateRequest.doc(JSON.toJSONString(webSite),XContentType.JSON);
        try {
            System.out.println(client().update(updateRequest,RequestOptions.DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete(){
        DeleteRequest deleteRequest = new DeleteRequest(index,"blog","123");
        try {
            System.out.println(client().delete(deleteRequest,RequestOptions.DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}