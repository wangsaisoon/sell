package com.ws.dataobject;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wangsaisoon
 * @title 卖家信息表
 * @time 2018/4/8 0008 下午 3:43
 */
@Data
@Entity
public class SellerInfo {

    /** 卖家信息主键id */
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解
    @GeneratedValue(generator="idGenerator") //使用uuid的生成策略
    private String sellerId;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 微信id */
    private String openid;

    public SellerInfo() {
    }

    public SellerInfo(String username, String password, String openid) {
        this.username = username;
        this.password = password;
        this.openid = openid;
    }
}
