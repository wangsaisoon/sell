package com.ws.controller;

import com.ws.converter.OrderForm2OrderMasterDTOConverter;
import com.ws.datatransformobject.OrderMasterDTO;
import com.ws.enums.ResultEnum;
import com.ws.exception.SellException;
import com.ws.form.OrderForm;
import com.ws.service.BuyerService;
import com.ws.service.OrderMasterService;
import com.ws.utils.ResultVOUtil;
import com.ws.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/29 0029 上午 9:46
 */
@RestController
@RequestMapping("buyer/order")
@Slf4j
public class BuyerOrderController {

    // 订单
    @Autowired
    private OrderMasterService orderMasterService;

    // 买家
    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                              BindingResult bindingResult) {
        // 如果参数不正确
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderMasterDTO orderMasterDTO = OrderForm2OrderMasterDTOConverter.convert(orderForm);

        if (CollectionUtils.isEmpty(orderMasterDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空，");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderMasterDTO createResult = orderMasterService.create(orderMasterDTO);
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    /**
     * 订单列表
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public  ResultVO<List<OrderMasterDTO>> list(@RequestParam("openid") String openid,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size) {

        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page, size);
        Page<OrderMasterDTO> orderMasterDTOPage = orderMasterService.findAll(openid, request);

        return ResultVOUtil.success(orderMasterDTOPage);
    }

    /**
     * 订单详情
     * @param openid
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ResultVO<OrderMasterDTO> detail(@RequestParam("openid") String openid,
                                           @RequestParam("orderId") String orderId) {

        // 安全性问题（越权访问什么什么的）
        OrderMasterDTO orderMasterDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderMasterDTO);
    }

    // 取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                  @RequestParam("orderId") String orderId) {

        // 安全性问题（越权访问什么什么的）
        buyerService.cancelOrder(openid, orderId);

        return ResultVOUtil.success();
    }
}
