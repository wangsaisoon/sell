package com.ws.service.impl;

import com.ws.dataobject.SellerInfo;
import com.ws.service.SellerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/4/8 0008 下午 4:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    @Autowired
    private SellerService sellerService;

    private final static String OPENID = "wx_1001002";

    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(OPENID);
        Assert.assertEquals(OPENID, sellerInfo.getOpenid());
    }
}