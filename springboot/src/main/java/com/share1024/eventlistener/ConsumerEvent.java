package com.share1024.eventlistener;

import org.springframework.context.ApplicationEvent;

/**
 * \* @Author: yesheng
 * \* Date: 2020/3/3 19:11
 * \* Description:
 * \
 */
public class ConsumerEvent<T> extends ApplicationEvent {

    private  T data;
    public ConsumerEvent(Object source,T data) {
        super(source);
        this.data = data;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}