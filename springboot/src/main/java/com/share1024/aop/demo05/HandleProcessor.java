package com.share1024.aop.demo05;

import java.util.ArrayList;
import java.util.List;

/**
 * \* @Author: yesheng
 * \* Date: 2021/5/28 14:19
 * \* Description:
 * \
 */
public class HandleProcessor implements IHandler{
    private int index = -1;
    private List<Inteceport> inteceports = new ArrayList<>();

    @Override
    public void handle() {
        if(index >= inteceports.size() - 1){
            return;
        }
        Inteceport inteceport = inteceports.get(++index);
        inteceport.handle(this);
    }
}