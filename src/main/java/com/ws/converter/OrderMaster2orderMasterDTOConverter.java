package com.ws.converter;

import com.ws.dataobject.OrderMaster;
import com.ws.datatransformobject.OrderMasterDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangsaisoon
 * @title 订单转购物车
 * @time 2018/3/28 0028 下午 4:33
 */
public class OrderMaster2orderMasterDTOConverter {

    public static OrderMasterDTO convert(OrderMaster orderMaster) {
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        BeanUtils.copyProperties(orderMaster, orderMasterDTO);
        return orderMasterDTO;
    }

    public static List<OrderMasterDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
    }
}
