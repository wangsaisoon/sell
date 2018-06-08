package com.ws.dataobject;

import com.ws.enums.OrderStatusEnum;
import com.ws.enums.PayStatusEnum;
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
 * @title 订单表
 * @time 2018/3/27 0027 下午 2:06
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /** 订单id */
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解
    @GeneratedValue(generator="idGenerator") //使用uuid的生成策略
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
     private  String orderStatus = OrderStatusEnum.NEW.getCode();

     /** 支付状态，默认0未支付 */
     private String payStatus = PayStatusEnum.WAIT.getCode();

     /** 创建时间 */
     private Date createTime;

     /** 修改时间 */
     private Date updateTime;

//     @Transient//添加这个注解不会映射到数据库//新增OrderMasterDTO--DataTransformObject类
//     private List<OrderDetail> orderDetailList;
}
