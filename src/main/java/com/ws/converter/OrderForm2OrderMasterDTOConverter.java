package com.ws.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ws.dataobject.OrderDetail;
import com.ws.datatransformobject.OrderMasterDTO;
import com.ws.enums.ResultEnum;
import com.ws.exception.SellException;
import com.ws.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangsaisoon
 * @title OrderForm转OrderMasterDTO
 * @time 2018/3/29 0029 上午 10:08
 */
@Slf4j
public class OrderForm2OrderMasterDTOConverter {

    public static OrderMasterDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();

        orderMasterDTO.setBuyerName(orderForm.getName());
        orderMasterDTO.setBuyerPhone(orderForm.getPhone());
        orderMasterDTO.setBuyerAddress(orderForm.getAddress());
        orderMasterDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());

        } catch (Exception e) {
            log.error("【对象转换】错误，string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderMasterDTO.setOrderDetailList(orderDetailList);

        return orderMasterDTO;
    }
}
