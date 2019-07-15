package com.share1024;

import org.elasticsearch.action.update.UpdateRequest;
import org.junit.Test;

/**
 * \* User: yesheng
 * \* Date: 2019/7/12 14:05
 * \* Description:
 * \
 */
public class UpdateDemo {

    private static final String INDEX = "user_idx";

    private static final String TYPE = "user";
    @Test
    public void updateDemo(){

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(INDEX);
        updateRequest.type(TYPE);

    }
}