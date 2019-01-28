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
    //上传
    public String uploadPath;
    private String uploadMaxFileSize;
    private String uploadMaxRequestSize;
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
    //Snowflake
    private Long workerId;
    private Long datacenterId;
    private Boolean isUseSystemClock;


    @Override
    public String toString() {
        return "MyYml{" +
                "druidUserName='" + druidUserName + '\'' +
                ", druidPassword='" + druidPassword + '\'' +
                ", allows='" + allows + '\'' +
                ", deny='" + deny + '\'' +
                ", uploadPath='" + uploadPath + '\'' +
                ", uploadMaxFileSize='" + uploadMaxFileSize + '\'' +
                ", uploadMaxRequestSize='" + uploadMaxRequestSize + '\'' +
                ", swaggerEnable=" + swaggerEnable +
                ", swaggerPath='" + swaggerPath + '\'' +
                ", swaggerTitle='" + swaggerTitle + '\'' +
                ", swaggerDescription='" + swaggerDescription + '\'' +
                ", swaggerServiceUrl='" + swaggerServiceUrl + '\'' +
                ", swaggerUserName='" + swaggerUserName + '\'' +
                ", swaggerUserUrl='" + swaggerUserUrl + '\'' +
                ", swaggerUserEmail='" + swaggerUserEmail + '\'' +
                ", swaggerVersion='" + swaggerVersion + '\'' +
                ", jwtSecret='" + jwtSecret + '\'' +
                ", jwtExpire=" + jwtExpire +
                ", jwtHeader='" + jwtHeader + '\'' +
                ", workerId=" + workerId +
                ", datacenterId=" + datacenterId +
                ", isUseSystemClock=" + isUseSystemClock +
                '}';
    }
}
