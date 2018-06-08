package com.ws.controller;

import com.ws.datatransformobject.OrderMasterDTO;
import com.ws.enums.ResultEnum;
import com.ws.exception.SellException;
import com.ws.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author wangsaisoon
 * @title 卖家端订单
 * @time 2018/3/29 0029 下午 2:28
 */
@Slf4j
@Controller
@RequestMapping("seller/order")
public class SellerOrderController {

    private final static String MENU_URL = "/sell/seller/order/list";

    // 订单
    @Autowired
    private OrderMasterService orderMasterService;

    /**
     * 订单列表
     * @param page 第几页，从第一页开始（page = 1）
     * @param size 一页有多少条数据
     * @return
     */
    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderMasterDTO> orderMasterDTOPage = orderMasterService.findAllList(request);
        map.put("orderMasterDTOPage", orderMasterDTOPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("order/list", map);
    }

    /**
     * 取消订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        map.put("url", MENU_URL);
        try {
            OrderMasterDTO orderMasterDTO = orderMasterService.getOne(orderId);
            orderMasterService.cancel(orderMasterDTO);
        } catch (SellException e) {
            log.error("【卖家端取消订单】发生异常={}", e);
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        return new ModelAndView("common/success", map);
    }

    /**
     * 订单详情
     *
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        try {
            orderMasterDTO = orderMasterService.getOne(orderId);
        } catch (SellException e) {
            log.error("【卖家端查看订单详情】发生异常={}", e);
            map.put("url", MENU_URL);
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("orderMasterDTO", orderMasterDTO);

        return new ModelAndView("order/detail");
    }

    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        map.put("url", MENU_URL);
        try {
            OrderMasterDTO orderMasterDTO = orderMasterService.getOne(orderId);
            orderMasterService.finish(orderMasterDTO);
        } catch (SellException e) {
            log.error("【卖家端完结订单】发生异常={}", e);
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        return new ModelAndView("common/success", map);
    }
}
