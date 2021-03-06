package com.share1024;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;

/**
 * \* User: yesheng
 * \* Date: 2019/7/12 14:12
 * \* Description:
 * \
 */
public class SearchDemo {

    private static final String INDEX = "user_idx";

    private static final String TYPE = "user";

    @Test
    public void test(){
        SearchResponse response = EsConfig.client.prepareSearch(INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(QueryBuilders.termQuery("user","yesheng0"))
                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))
                .get();
        System.out.println(response.toString());
        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
    }
}