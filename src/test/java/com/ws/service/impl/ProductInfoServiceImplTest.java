package com.ws.service.impl;

import com.ws.dataobject.ProductInfo;
import com.ws.enums.ProductStatusEnum;
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
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/27 0027 上午 9:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoServiceImpl;

    @Test
    public void getOne() {
        ProductInfo info = productInfoServiceImpl.getOne("1001");
        Assert.assertEquals("1001", info.getProductId());
    }

    @Test
    public void save() {
        ProductInfo info = new ProductInfo("安慕希", new BigDecimal(5.99), 12 * 10, "安慕希，希腊好酸奶！", "", ProductStatusEnum.DOWN.getCode(), "5");
        ProductInfo info2 = productInfoServiceImpl.save(info);
        Assert.assertNotNull(info2);
    }

    @Test
    public void findAll() {
        Pageable pageable = new PageRequest(1, 2);
        Page<ProductInfo> list = productInfoServiceImpl.findAll(pageable);
        System.out.println(list.getTotalElements());
        Assert.assertNotEquals(0, list.getSize());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productInfoServiceImpl.findUpAll();
        Assert.assertNotEquals(0, list.size());
    }

    @Test
    public void onSale() {
        ProductInfo productInfo = productInfoServiceImpl.onSale("1001");
        Assert.assertEquals(ProductStatusEnum.UP.getCode(), productInfo.getProductStatus());
    }

    @Test
    public void offSale() {
        ProductInfo productInfo = productInfoServiceImpl.offSale("1001");
        Assert.assertEquals(ProductStatusEnum.DOWN.getCode(), productInfo.getProductStatus());
    }
}