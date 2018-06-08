package com.ws.service.impl;

import com.ws.datatransformobject.OrderMasterDTO;
import com.ws.enums.ResultEnum;
import com.ws.exception.SellException;
import com.ws.service.BuyerService;
import com.ws.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/29 0029 下午 1:38
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public OrderMasterDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderMasterDTO cancelOrder(String openid, String orderId) {
        OrderMasterDTO orderMasterDTO = checkOrderOwner(openid, orderId);

        if (orderMasterDTO == null) {
            log.error("【取消订单】查寻不到该订单，orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderMasterService.cancel(orderMasterDTO);
    }

    /**
     * 判断是否是自己的订单
     * @param openid
     * @param orderId
     * @return
     */
    private OrderMasterDTO checkOrderOwner(String openid, String orderId) {
        OrderMasterDTO orderMasterDTO = orderMasterService.getOne(orderId);

        if (orderMasterDTO == null) {
            return null;
        }

        // 判断是否是自己的订单
        if (!orderMasterDTO.getBuyerOpenid().equals(openid)) {
            log.error("【查询订单】订单的openid不一致，openid={}，orderMasterDTO={}", openid, orderMasterDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }

        return orderMasterDTO;
    }
}
