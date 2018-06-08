package com.ws.service.impl;

import com.ws.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/26 0026 下午 5:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryServiceImpl;

    @Test
    public void findOne() {
        ProductCategory result = productCategoryServiceImpl.findOne("1");
        Assert.assertEquals("1", result.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = productCategoryServiceImpl.findAll();
        System.out.println(list);
        Assert.assertNotEquals(0, list.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> pcList =
                productCategoryServiceImpl.findByCategoryTypeIn(Arrays.asList("2", "6"));
        Assert.assertNotEquals(0, pcList.size());
    }

    @Test
    public void save() {
        ProductCategory pc = new ProductCategory("小希", "10");
        ProductCategory result = productCategoryServiceImpl.save(pc);
        Assert.assertNotNull(result);
    }
}