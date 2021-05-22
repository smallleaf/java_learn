package com.share1024.iocCircle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(20)
public class Room {

    @Autowired
    private User user1;

    @Autowired
    private User user12;

}
