package com.swjtu.secondhand.interceptor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
@ConfigurationProperties(prefix = "alipay")// 读取配置文件中application.properties中的内容
public class AlipayConfig {
    private String appId;
    private String appPrivateKey;
    private String alipayPublicKey;
    private String notifyUrl;


//    @PostConstruct
//    public void init() {
//        // 设置参数（全局只需设置一次）
//        Config config = new Config();
//        config.protocol = "https";
//        config.gatewayHost = "openapi.alipaydev.com";
//        config.signType = "RSA2";
//        config.appId = this.appId;
//        config.merchantPrivateKey = this.appPrivateKey;
//        config.alipayPublicKey = this.alipayPublicKey;
//        config.notifyUrl = this.notifyUrl;
//        Factory.setOptions(config);
//        System.out.println("=======支付宝SDK初始化成功=======");
//    }

}

