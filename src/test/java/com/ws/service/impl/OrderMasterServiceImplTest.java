package com.ws.service.impl;

import com.ws.dataobject.OrderDetail;
import com.ws.dataobject.OrderMaster;
import com.ws.datatransformobject.OrderMasterDTO;
import com.ws.enums.OrderStatusEnum;
import com.ws.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/28 0028 上午 11:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterServiceImpl orderMasterServiceImpl;

    private final static String OPENID = "wx_123456";

    @Test
    public void create() {
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        orderMasterDTO.setBuyerName("小白");
        orderMasterDTO.setBuyerAddress("小白的白黑屋");
        orderMasterDTO.setBuyerOpenid(OPENID);
        orderMasterDTO.setBuyerPhone("13365148074");

        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        OrderDetail od1 = new OrderDetail();
        od1.setProductId("1001");
        od1.setProductQuantity(1);
        orderDetailList.add(od1);

        OrderDetail od2 = new OrderDetail();
        od2.setProductId("1002");
        od2.setProductQuantity(1);
        orderDetailList.add(od2);

        OrderDetail od3 = new OrderDetail();
        od3.setProductId("1003");
        od3.setProductQuantity(1);
        orderDetailList.add(od3);

        orderMasterDTO.setOrderDetailList(orderDetailList);

        OrderMasterDTO result = orderMasterServiceImpl.create(orderMasterDTO);
        log.info("【创建订单】 result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    @Transactional
    public void getOne() {
        OrderMasterDTO result = orderMasterServiceImpl.getOne("aaa");
        log.info("【查询单个订单】 result={}", result);
        Assert.assertEquals("aaa", result.getOrderId());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderMasterDTO> orderMasterDTOPage = orderMasterServiceImpl.findAll(OPENID, pageRequest);
        Assert.assertNotEquals(0, orderMasterDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderMasterDTO orderMasterDTO = orderMasterServiceImpl.getOne("aaa");
        OrderMasterDTO orderMasterDTO1 = orderMasterServiceImpl.cancel(orderMasterDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), orderMasterDTO1.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderMasterDTO orderMasterDTO = orderMasterServiceImpl.getOne("bbb");
        OrderMasterDTO orderMasterDTO2 = orderMasterServiceImpl.finish(orderMasterDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), orderMasterDTO2.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderMasterDTO orderMasterDTO = orderMasterServiceImpl.getOne("40288095626b30e101626b30eb1f0000");
        OrderMasterDTO orderMasterDTO2 = orderMasterServiceImpl.paid(orderMasterDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), orderMasterDTO2.getPayStatus());
    }

    @Test
    public void findAllList() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderMasterDTO> orderMasterDTOPage = orderMasterServiceImpl.findAllList(request);
//        Assert.assertNotEquals(0, orderMasterDTOPage.getTotalElements());
        Assert.assertTrue("查询所有的订单列表", orderMasterDTOPage.getTotalElements() > 0);
    }
}