package com.ws.repository;

import com.ws.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/27 0027 下午 2:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void save() {
        OrderDetail od = new OrderDetail();
        od.setOrderId("2");
        od.setProductId("1006");
        od.setProductIcon("");
        od.setProductName("鸡腿");
        od.setProductPrice(new BigDecimal(3.5));
        od.setProductQuantity(10);
        OrderDetail od2 = repository.save(od);
        Assert.assertNotNull(od2);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> list = repository.findByOrderId("1");
        Assert.assertNotEquals(0, list.size());
    }
}