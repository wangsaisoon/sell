package com.ws.controller;

import com.ws.dataobject.ProductCategory;
import com.ws.dataobject.ProductInfo;
import com.ws.service.ProductCategoryService;
import com.ws.service.ProductInfoService;
import com.ws.utils.ResultVOUtil;
import com.ws.viewobject.ProductInfoVO;
import com.ws.viewobject.ProductVO;
import com.ws.viewobject.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangsaisoon
 * @title 买家商品
 * @time 2018/3/27 0027 上午 10:21
 */
@RestController
@RequestMapping("buyer/product")
public class BuyerProductController {

    // 商品
    @Autowired
    private ProductInfoService productInfoService;

    // 类目
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list() {
        // 1、查询所有的上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        // 2、查询类目（一次性查询）
        // 传统方法取商品list里面的类目编号（category_type)
//        List<String> categoryTypeList = new ArrayList<String>();
//        for (ProductInfo info : productInfoList) {
//            categoryTypeList.add(info.getCategoryType());
//        }
        // 精简做法（java8的特性，lambda）
        List<String> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategories = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        // 3、数据拼装
        List<ProductVO> productVOList = new ArrayList<ProductVO>();
        for (ProductCategory pc : productCategories) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(pc.getCategoryType());
            productVO.setCategoryName(pc.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<ProductInfoVO>();
            for (ProductInfo info : productInfoList) {
                if (pc.getCategoryType().equals(info.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
//                    productInfoVO.setProductId(info.getProductId());
//                    productInfoVO.setProductName(info.getProductName());
//                    productInfoVO.setProductPrice(info.getProductPrice());
//                    productInfoVO.setProductIcon(info.getProductIcon());
//                    productInfoVO.setProductDescription(info.getProductDescription());
                    // 将info对象里面的值copy到productInfoVOd对象里面去
                    BeanUtils.copyProperties(info, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVO(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
