package com.ws.repository;

import com.ws.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/4/8 0008 下午 3:54
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenid(String openid);
}
