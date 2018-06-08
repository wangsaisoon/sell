package com.ws.controller;

import com.ws.dataobject.ProductCategory;
import com.ws.dataobject.ProductInfo;
import com.ws.datatransformobject.OrderMasterDTO;
import com.ws.enums.ResultEnum;
import com.ws.exception.SellException;
import com.ws.form.ProductInfoForm;
import com.ws.service.ProductCategoryService;
import com.ws.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author wangsaisoon
 * @title 卖家商品
 * @time 2018/4/3 0003 上午 9:49
 */
@Slf4j
@Controller
@RequestMapping("seller/product")
public class SellerProductController {

    private final static String MENU_URL = "/sell/seller/product/list";

    // 商品
    @Autowired
    private ProductInfoService productInfoService;

    // 类目
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("product/list", map);
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping(value = "/onSale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        map.put("url", MENU_URL);
        try {
            ProductInfo productInfo = productInfoService.onSale(productId);
            map.put("productInfo", productInfo);
        } catch (SellException e) {
            log.error("【卖家商品上架】发生异常={}", e);
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        return new ModelAndView("common/success", map);
    }

    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping(value = "/offSale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        map.put("url", MENU_URL);
        try {
            ProductInfo productInfo = productInfoService.offSale(productId);
            map.put("productInfo", productInfo);
        } catch (SellException e) {
            log.error("【卖家商品下架】发生异常={}", e);
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        return new ModelAndView("common/success", map);
    }

    /**
     * 去新增/修改页面
     * @param productId
     * @param map
     * @return
     */
    @GetMapping(value = "/toEdit")
    public ModelAndView toUpdate(@RequestParam(value = "productId", required = false) String productId,
                               Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            try {
                ProductInfo productInfo = productInfoService.getOne(productId);
                map.put("productInfo", productInfo);
            } catch (SellException e) {
                log.error("【卖家去新增/修改商品】发生异常={}", e);
                map.put("url", MENU_URL);
                map.put("msg", e.getMessage());
                return new ModelAndView("common/error", map);
            }
        }
        // 查询所有的类目
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList", productCategoryList);
        return new ModelAndView("product/edit", map);
    }

    /**
     * 新增/修改
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping(value = "/save")
    public ModelAndView save(@Valid ProductInfoForm form,
                               BindingResult bindingResult,
                               Map<String, Object> map) {
        map.put("url", MENU_URL);
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/error", map);
        }
        try {
            ProductInfo productInfo = new ProductInfo();
            // 如果ProductId不为空，说明是修改
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfoService.getOne(form.getProductId());
            }
            BeanUtils.copyProperties(form, productInfo);
            productInfoService.save(productInfo);
        } catch (SellException e) {
            log.error("【卖家新增/修改商品】发生异常={}", e);
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_SAVE_OR_UPDATE_SUCCESS.getMessage());
        return new ModelAndView("common/success", map);
    }
}