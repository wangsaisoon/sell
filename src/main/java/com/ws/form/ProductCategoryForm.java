package com.ws.form;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/4/8 0008 下午 1:58
 */
@Data
public class ProductCategoryForm {

    /** 类目id */
    private String categoryId;

    /** 类目名称 */
    @NotEmpty(message = "类目名称必填")
    private String categoryName;

    /** 类目编号 */
    @NotEmpty(message = "类目编号必填")
    private String categoryType;
}
