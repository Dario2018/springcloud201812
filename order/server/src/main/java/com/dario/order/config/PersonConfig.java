package com.dario.order.config;

/*
* 和git上面的order-*yml文件里的person属性对应
* */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@ConfigurationProperties(prefix = "person")
@Component
@Data
public class PersonConfig {

    private String username;
    private String password;
}
