package com.ws.enums;

import lombok.Getter;

/**
 * @author wangsaisoon
 * @title 订单状态
 * @time 2018/3/27 0027 下午 2:15
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW("0", "新订单"),
    FINISHED("1", "完结"),
    CANCEL("2", "已取消")
    ;

    private String code;
    private String message;

    OrderStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
