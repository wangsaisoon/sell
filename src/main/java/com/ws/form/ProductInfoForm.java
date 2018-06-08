package com.ws.form;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/4/8 0008 上午 11:01
 */
@Data
public class ProductInfoForm {

    /** 商品id */
    private String productId;

    /** 商品名称 */
    @NotEmpty(message = "商品名称必填")
    private String productName;

    /** 商品单价 */
    @NotNull(message = "单价必填")
    private BigDecimal productPrice;

    /** 库存 */
    @NotNull(message = "库存必填")
    private Integer productStock;

    /** 描述 */
    @NotEmpty(message = "描述必填")
    private String productDescription;

    /** 商品图片 */
    @NotEmpty(message = "图片必上传")
    private String productIcon;

    /** 类目编号 */
    @NotEmpty(message = "类目必选")
    private String categoryType;

}
