package com.ws.service;

import com.ws.datatransformobject.OrderMasterDTO;

/**
 * @author wangsaisoon
 * @title 买家
 * @time 2018/3/29 0029 上午 11:48
 */
public interface BuyerService {
    // 查询一个订单
    OrderMasterDTO findOrderOne(String openid, String orderId);

    // 取消订单
    OrderMasterDTO cancelOrder(String openid, String orderId);
}
