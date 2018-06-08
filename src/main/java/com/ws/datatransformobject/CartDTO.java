package com.ws.datatransformobject;

import lombok.Data;

/**
 * @author wangsaisoon
 * @title 购物车
 * @time 2018/3/28 0028 上午 9:18
 */
@Data
public class CartDTO {

    /** 商品id */
    private  String productId;

    /** 数量 */
    private  Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
