package com.ws.repository;

import com.ws.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/27 0027 下午 2:31
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 通过订单id查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
