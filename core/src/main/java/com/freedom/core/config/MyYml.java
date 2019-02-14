package com.freedom.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目配置文件
 */
@Data
@Component
@ConfigurationProperties(prefix = "freedom")
public class MyYml {
    //druid
    private String druidUserName;
    private String druidPassword;
    private String allows;
    private String deny;
    //Swagger
    private Boolean swaggerEnable = false;
    private String swaggerPath;
    private String swaggerTitle;
    private String swaggerDescription;
    private String swaggerServiceUrl;
    private String swaggerUserName;
    private String swaggerUserUrl;
    private String swaggerUserEmail;
    private String swaggerVersion;
    //jwt
    private String jwtSecret;
    private long jwtExpire;
    private String jwtHeader;
    //redis
    private Boolean redisOpen;


}
