package com.ws.dataobject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ws.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author wangsaisoon
 * @title 类目
 * @time 2018/3/26 0026 下午 2:44
 */
@Table(name = "product_category")
@Entity
@DynamicUpdate//自动/动态更新'修改时间'字段
@Data
public class ProductCategory {

    /**
     * 类目id
     * jpa (hibernate实现)的UUID生成主键策略
     * eclipse会提示错误，但程序可以执行
     */
    @Id
    @Column(name = "category_id")
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解
    @GeneratedValue(generator="idGenerator") //使用uuid的生成策略
    private String categoryId;

    /** 类目名称 */
    @Column(name = "category_name")
    private String categoryName;

    /** 类目编号 */
    @Column(name = "category_type")
    private String categoryType;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    public ProductCategory() {}

    public ProductCategory(String categoryName, String categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}