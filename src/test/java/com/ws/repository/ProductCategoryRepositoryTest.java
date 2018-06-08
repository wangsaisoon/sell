package com.ws.repository;

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
 * @time 2018/3/26 0026 下午 2:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    /**
     * 查询单条
     */
    @Test
    @Transactional
    public void findOneTest(){
        ProductCategory pc = repository.getOne("1");
        System.out.println("**************"+pc);
    }

    @Test
    public void saveTest(){
        ProductCategory pc = new ProductCategory("xie a", "6");
        ProductCategory pc2 = repository.save(pc);
        Assert.assertNotNull(pc2);
//        Assert.assertNotEquals(null, pc2);//这俩意思一样呢
    }

    @Test
    @Transactional
    public void updateTest(){
        ProductCategory pc = repository.getOne("1");
        pc.setCategoryType("66");
        repository.save(pc);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<String> list = Arrays.asList("5", "2", "8");
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }
}