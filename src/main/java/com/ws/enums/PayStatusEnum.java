package com.ws.enums;

import lombok.Getter;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/27 0027 下午 2:20
 */
@Getter
public enum PayStatusEnum implements CodeEnum {

    WAIT("0", "未支付/等待支付"),
    SUCCESS("1", "支付成功");

    private String code;

    private String message;

    PayStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
