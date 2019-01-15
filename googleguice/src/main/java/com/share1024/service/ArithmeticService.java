package com.share1024.service;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/26
 */

import com.google.inject.ImplementedBy;
import com.share1024.service.impl.DivisionService;

@ImplementedBy(DivisionService.class)
public interface ArithmeticService {
    void calculate(int a, int b);
}
