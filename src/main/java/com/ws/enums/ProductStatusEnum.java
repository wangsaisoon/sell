package com.ws.enums;

import lombok.Getter;

/**
 * @author wangsaisoon
 * @title 商品状态
 * @time 2018/3/27 0027 上午 9:44
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP("0", "已上架"),
    DOWN("1", "已下架")
    ;
    private String code;
    private String message;

    ProductStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
