package com.ws.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author wangsaisoon
 * @title 商品（包含类目）
 * @time 2018/3/27 0027 上午 10:51
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private String categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVO;

}
