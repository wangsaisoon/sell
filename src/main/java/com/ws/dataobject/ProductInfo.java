package com.ws.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ws.enums.ProductStatusEnum;
import com.ws.utils.EnumUtil;
import com.ws.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangsaisoon
 * @title 商品
 * @time 2018/3/27 0027 上午 8:57
 */
@Entity
@Data//就不用写get/set/toString方法了
@DynamicUpdate//自动/动态更新'修改时间'字段
public class ProductInfo {

    /** 商品id */
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解
    @GeneratedValue(generator="idGenerator") //使用uuid的生成策略
    private String productId;

    /** 商品名称 */
    private String productName;

    /** 商品单价 */
    private BigDecimal productPrice;

    /** 库存 */
    private Integer productStock;

    /** 描述 */
    private String productDescription;

    /** 商品图片 */
    private String productIcon;

    /** 商品状态：0正常，1下架 */
    private String productStatus = "0";

    /** 类目编号 */
    private String categoryType;

    public ProductInfo() {
    }

    public ProductInfo(String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productIcon, String productStatus, String categoryType) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.productStatus = productStatus;
        this.categoryType = categoryType;
    }
    /** 创建时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /** 修改时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}
