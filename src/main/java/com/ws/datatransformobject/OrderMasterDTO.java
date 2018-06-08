package com.ws.datatransformobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ws.dataobject.OrderDetail;
import com.ws.enums.OrderStatusEnum;
import com.ws.enums.PayStatusEnum;
import com.ws.utils.EnumUtil;
import com.ws.utils.serializer.Date2LongSerializer;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/27 0027 下午 5:19
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)// 过期了
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderMasterDTO {

    /** 订单id */
    private String orderId;

    /** 买家姓名 */
    private String buyerName;

    /** 买家电话 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信openid */
    private String buyerOpenid;

    /** 订单金额 */
    private BigDecimal orderAmount;

    /** 订单状态，默认0新下单 */
    private  String orderStatus;

    /** 支付状态，默认0未支付 */
    private String payStatus;

    /** 创建时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /** 修改时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /** 订单详情 */
    private List<OrderDetail> orderDetailList;

    @JsonIgnore //对象转json会忽略这个方法
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore //对象转json会忽略这个方法
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
