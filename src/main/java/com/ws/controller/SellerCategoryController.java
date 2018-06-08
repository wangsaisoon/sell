package com.ws.controller;

import com.ws.dataobject.ProductCategory;
import com.ws.exception.SellException;
import com.ws.form.ProductCategoryForm;
import com.ws.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @title
 * @time 2018/4/8 0008 上午 11:38
 */
@Slf4j
@Controller
@RequestMapping("seller/category")
public class SellerCategoryController {

    private final static String MENU_URL = "/sell/seller/category/list";

    // 商品类目
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 类目列表
     * @param map
     * @return
     */
    @GetMapping(value = "/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = productCategoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    /**
     * 去编辑页面
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping(value = "/toEdit")
    public ModelAndView toEdit(@RequestParam(value = "categoryId", required = false) String categoryId,
                               Map<String, Object> map) {
        map.put("url", MENU_URL);
        try {
            // 如果categoryId不为空，则去修改
            if (!StringUtils.isEmpty(categoryId)) {
                ProductCategory category = productCategoryService.findOne(categoryId);
                map.put("category", category);
            }
        } catch (SellException e) {
            log.error("【卖家去新增/修改类目】出现异常={}", e);
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        return new ModelAndView("category/edit", map);
    }

    /**
     * 新增/修改
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping(value = "/save")
    public ModelAndView save(@Valid ProductCategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        map.put("url", MENU_URL);
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/error", map);
        }
        try {
            ProductCategory productCategory = new ProductCategory();
            if (!StringUtils.isEmpty(form.getCategoryId())) {// 不为空，则是修改
                    productCategory = productCategoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form, productCategory);
            productCategoryService.save(productCategory);
        } catch (SellException e) {
            log.error("【卖家新增/修改类目】发生异常={}", e);
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        return new ModelAndView("common/success", map);
    }
}
