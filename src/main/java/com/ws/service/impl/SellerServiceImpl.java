package com.ws.service.impl;

import com.ws.dataobject.SellerInfo;
import com.ws.repository.SellerInfoRepository;
import com.ws.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/4/8 0008 下午 4:55
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
