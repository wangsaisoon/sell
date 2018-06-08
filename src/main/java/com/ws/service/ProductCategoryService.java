package com.ws.service;

import com.ws.dataobject.ProductCategory;

import java.util.List;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/26 0026 下午 5:40
 */
public interface ProductCategoryService {


    ProductCategory findOne(String categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<String> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
