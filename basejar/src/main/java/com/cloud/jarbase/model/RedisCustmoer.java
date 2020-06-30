package com.cloud.jarbase.model;

import lombok.Data;

@Data
public class RedisCustmoer {
    private String tenant;
    private String sessionId;

    private String imei;  //硬件设备号
    private String token; //会员登录token
    private String clientPubKey; //会员创建的公钥
    private String srvPriKey;  //服务器给会员的公钥对应的私钥
    private Integer customerId;  //会员id
    private String account;  //会员登录用的账号
//    private String country;  //会员手机登录时的国家
    private String aesKey;  //数据部分加解密密钥
    private String useLanguage; //记录赠卡人使用的何种语言，以便于给他发送通知信息
}
