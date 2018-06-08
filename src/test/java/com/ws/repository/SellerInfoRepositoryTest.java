package com.ws.repository;

import com.ws.dataobject.SellerInfo;
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
 * @time 2018/4/8 0008 下午 4:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;

    private final static String OPENID = "wx_1001002";

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo("xiaohei", "1" ,OPENID);
        SellerInfo sellerInfo1 = repository.save(sellerInfo);
        Assert.assertNotNull(sellerInfo1);
    }

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = repository.findByOpenid(OPENID);
        Assert.assertEquals(OPENID, sellerInfo.getOpenid());
    }
}