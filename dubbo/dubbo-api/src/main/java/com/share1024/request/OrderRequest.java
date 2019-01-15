package com.share1024.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/16
 */
@Data
public class OrderRequest implements Serializable{

    @NotNull(message = "order id 不能为空")
    private String orderId;

    @Min(value = 0,message = "价格最低为0")
    private Float price;

}
