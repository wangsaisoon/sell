package com.ws.viewobject;

import lombok.Data;

/**
 * @author wangsaisoon
 * @title http请求返回的最外层对象
 * @time 2018/3/27 0027 上午 10:26
 */
@Data
public class ResultVO<T> {

    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String message;

    /** 返回的具体内容 */
    private T data;
}
