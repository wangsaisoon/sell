package com.ws.service.impl;

import com.ws.converter.OrderMaster2orderMasterDTOConverter;
import com.ws.dataobject.OrderDetail;
import com.ws.dataobject.OrderMaster;
import com.ws.dataobject.ProductInfo;
import com.ws.datatransformobject.CartDTO;
import com.ws.datatransformobject.OrderMasterDTO;
import com.ws.enums.OrderStatusEnum;
import com.ws.enums.PayStatusEnum;
import com.ws.enums.ResultEnum;
import com.ws.exception.SellException;
import com.ws.repository.OrderDetailRepository;
import com.ws.repository.OrderMasterRepository;
import com.ws.service.OrderMasterService;
import com.ws.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/27 0027 下午 5:13
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {

    /** 订单 */
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    /** 商品 */
    @Autowired
    private ProductInfoService productInfoService;

    /** 订单详情 */
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional
    public OrderMasterDTO create(OrderMasterDTO orderMasterDTO) {

        // 商品总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        // 0、因为数据库的订单id是自增的，所以，得先保存订单表，才能获取到订单id保存子表（订单详情）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster orderMasterSaveResult = orderMasterRepository.save(orderMaster);
        if (orderMasterSaveResult == null) {
            log.error("【创建订单】失败");
            throw new SellException(ResultEnum.ORDER_CREATE_ERROR);
        }

        // 订单id
        String orderId = orderMasterSaveResult.getOrderId();
        orderMasterDTO.setOrderId(orderId);

        // 1、查询商品（数量、单价）
        for (OrderDetail od : orderMasterDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.getOne(od.getProductId());
            // 判断商品是否存在
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 2、计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(od.getProductQuantity()))
                    .add(orderAmount);
            // 订单详情入库
            od.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, od);
            orderDetailRepository.save(od);
        }

        // 0-3、更新总价
        orderMasterSaveResult.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMasterSaveResult);

        // 3、写入数据库（OrderMaster和OrderDetail）
//        OrderMaster orderMaster = new OrderMaster();
//        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
//        orderMaster.setOrderAmount(orderAmount);
//        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
//        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
//        orderMasterRepository.save(orderMaster);

        // 4、扣库存（先查询数量是否够）
        List<CartDTO> cartDTOList = orderMasterDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);
        return orderMasterDTO;
    }

    @Override
    @Transactional
    public OrderMasterDTO getOne(String orderId) {
        OrderMaster orderMaster = null;
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()) {
            orderMaster = orderMasterOptional.get();
        }
        if (orderMaster == null) {
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        BeanUtils.copyProperties(orderMaster, orderMasterDTO);
        orderMasterDTO.setOrderDetailList(orderDetailList);
        return orderMasterDTO;
    }

    /** 查询某人的订单列表 */
    @Override
    public Page<OrderMasterDTO> findAll(String buyerOrderId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOrderId, pageable);
        List<OrderMasterDTO> orderMasterDTOList = OrderMaster2orderMasterDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<OrderMasterDTO>(orderMasterDTOList, pageable, orderMasterPage.getTotalElements());
    }

    /** 取消订单 */
    @Override
    @Transactional
    public OrderMasterDTO cancel(OrderMasterDTO orderMasterDTO) {
        OrderMaster orderMaster = new OrderMaster();

        // 判断订单状态（只能取消新订单）
        if (!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确，orderId={}，orderStatus={}", orderMasterDTO.getOrderId(), orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 判断支付状态(未支付)
        if (!orderMasterDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【取消订单】订单支付状态不正确，orderMasterDTO={}", orderMasterDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        // 修改订单状态
        orderMasterDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【取消订单】更新订单状态失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        // 返还库存（判断订单有无商品）
        if (CollectionUtils.isEmpty(orderMasterDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情，orderMasterDTO={}", orderMasterDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderMasterDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        // 如果已支付，需要退款
        if (orderMasterDTO.getOrderStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            // TODO
        }
        return orderMasterDTO;
    }

    /**
     * 完结订单
     * @param orderMasterDTO
     * @return
     */
    @Override
    @Transactional
    public OrderMasterDTO finish(OrderMasterDTO orderMasterDTO) {
        // 判断订单状态（只能完结新订单）
        if (!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确，orderId={}，orderStatus={}", orderMasterDTO.getOrderId(), orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改状态
        OrderMaster orderMaster = new OrderMaster();
        orderMasterDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新订单状态失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderMasterDTO;
    }

    /**
     * 支付订单
     * @param orderMasterDTO
     * @return
     */
    @Override
    @Transactional
    public OrderMasterDTO paid(OrderMasterDTO orderMasterDTO) {
        // 判断订单状态（新订单）
        if (!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单】订单状态不正确，orderId={}，orderStatus={}", orderMasterDTO.getOrderId(), orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 判断支付状态(未支付)
        if (!orderMasterDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【支付订单】订单支付状态不正确，orderMasterDTO={}", orderMasterDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        // 修改支付状态、
        OrderMaster orderMaster = new OrderMaster();
        orderMasterDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【支付订单】更新订单支付状态失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_PAY_UPDATE_FAIL);
        }
        return orderMasterDTO;
    }

    @Override
    public Page<OrderMasterDTO> findAllList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderMasterDTO> orderMasterDTOList = OrderMaster2orderMasterDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<OrderMasterDTO>(orderMasterDTOList, pageable, orderMasterPage.getTotalElements());
    }
}