package com.ws.service.impl;

import com.ws.dataobject.ProductCategory;
import com.ws.repository.ProductCategoryRepository;
import com.ws.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/26 0026 下午 5:43
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(String categoryId) {
        Optional<ProductCategory> categoryOptional = repository.findById(categoryId);
        if (!categoryOptional.isPresent()) {
            return null;
        }
        return categoryOptional.get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<String> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
