package com.ws.repository;

import com.ws.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/27 0027 下午 2:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final static String OPENID = "wx_123456";

    @Test
    public void save() {
        OrderMaster om = new OrderMaster();
        om.setBuyerName("小希");
        om.setBuyerPhone("15236521021");
        om.setBuyerAddress("大明宫");
        om.setBuyerOpenid(OPENID);
        om.setOrderAmount(new BigDecimal(22.1));
        OrderMaster om2 = repository.save(om);
        Assert.assertNotNull(om2);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, request);
        Assert.assertNotEquals(0, result.getTotalElements());
    }
}