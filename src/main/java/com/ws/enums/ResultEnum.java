package com.ws.enums;

import lombok.Getter;

/**
 * @author wangsaisoon
 * @title 异常的结果
 * @time 2018/3/28 0028 上午 8:50
 */
@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "库存不足"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_UPDATE_FAIL(15, "更新订单状态失败"),
    ORDER_DETAIL_EMPTY(16, "订单中无商品详情"),
    ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),
    ORDER_PAY_UPDATE_FAIL(18, "更新订单支付状态失败"),
    CART_EMPTY(19, "购物车不能为空"),
    ORDER_OWNER_ERROR(20, "该订单不属于当前用户"),
    ORDER_CANCEL_SUCCESS(21, "取消订单成功"),
    ORDER_FINISH_SUCCESS(22, "完结订单成功"),
    PRODUCT_SAVE_OR_UPDATE_SUCCESS(23, "新增/修改商品成功"),
    PRODUCT_STATUS_ERROR(24, "商品状态不正确"),

    ORDER_CREATE_ERROR(100, "创建订单失败"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    ResultEnum() {

    }
}
