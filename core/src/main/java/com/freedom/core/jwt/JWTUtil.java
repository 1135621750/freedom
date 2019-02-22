package com.freedom.core.jwt;

import com.alibaba.fastjson.util.TypeUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.freedom.core.config.MyYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * 使用用户密码加密
 */
@Component
@Slf4j
public class JWTUtil {

    @Autowired
    private MyYml yml;
    /**
     * 校验token是否正确
     * TokenExpiredException 超时
     *SignatureVerificationException 篡改了token
     * @param token 密钥
     * @return 是否正确
     */
    public boolean verify(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(yml.getJwtSecret());
            DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("USERNAME").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    public String getId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("ID").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名
     * @param map 用户
     * @return 加密的token
     */
    public String sign(Map map) {
        try {
            Date date = new Date(System.currentTimeMillis() + yml.getJwtExpire());
            // 附带username信息
            return JWT.create()
                    .withClaim("ID", map.get("id").toString())
                    .withClaim("USERNAME", map.get("userName").toString())
                    .withExpiresAt(date)
                    .sign(Algorithm.HMAC256(yml.getJwtSecret()));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

}
