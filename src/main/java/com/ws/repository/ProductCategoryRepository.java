package com.ws.repository;

import com.ws.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/26 0026 下午 2:54
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {

    /**
     * 通过类目类型查询
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<String> categoryTypeList);
}
