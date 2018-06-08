package com.ws.repository;

import com.ws.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/27 0027 上午 9:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void save() {
        ProductInfo info = new ProductInfo("旺旺", new BigDecimal(1.2), 100, "旺旺雪饼", "", "0", "5");
        ProductInfo info2 = repository.save(info);
        Assert.assertNotNull(info2);
    }

    @Test
    public void getOne (){
        ProductInfo info = repository.getOne("1001");
        Assert.assertNotNull(info);
    }

    @Test
    @Transactional
    public void update() {
        ProductInfo info = repository.getOne("1001");
        info.setProductStock(50);
        ProductInfo info2 = repository.save(info);
        Assert.assertNotNull(info2);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list = repository.findByProductStatus("0");
        Assert.assertNotEquals(0, list.size());
    }
}