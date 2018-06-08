package com.ws.repository;

import com.ws.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/27 0027 下午 2:30
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 按照买家的openId查询，并分页
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);


}
