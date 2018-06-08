package com.ws.exception;

import com.ws.enums.ResultEnum;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/28 0028 上午 8:49
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
