package com.ws.service;

import com.ws.dataobject.ProductInfo;
import com.ws.datatransformobject.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/27 0027 上午 9:37
 */
public interface ProductInfoService {

    ProductInfo getOne(String productId);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 查询所有商品，带分页
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 查询已上架的商品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 加库存
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 上架
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     * 下架
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);
}
