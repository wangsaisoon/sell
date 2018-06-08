package com.ws.repository;

import com.ws.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/27 0027 上午 9:12
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 查询上架的商品
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(String productStatus);
}
