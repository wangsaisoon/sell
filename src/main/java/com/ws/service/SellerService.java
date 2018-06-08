package com.ws.service;

import com.ws.dataobject.SellerInfo;

/**
 * @author wangsaisoon
 * @title 卖家端
 * @time 2018/4/8 0008 下午 4:54
 */
public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
