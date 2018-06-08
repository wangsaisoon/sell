package com.ws.service;

import com.ws.datatransformobject.OrderMasterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/27 0027 下午 5:13
 */
public interface OrderMasterService {

    /**
     * 创建订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO create(OrderMasterDTO orderMasterDTO);

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    OrderMasterDTO getOne(String orderId);

    /**
     * 查询某人的订单列表
     * @param buyerOrderId
     * @param pageable
     * @return
     */
    Page<OrderMasterDTO> findAll(String buyerOrderId, Pageable pageable);

    /**
     * 取消订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO cancel(OrderMasterDTO orderMasterDTO);

    /**
     * 完结订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO finish(OrderMasterDTO orderMasterDTO);

    /**
     * 支付订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO paid(OrderMasterDTO orderMasterDTO);

    /**
     * 查询所有的订单列表
     * @param pageable
     * @return
     */
    Page<OrderMasterDTO> findAllList(Pageable pageable);
}
