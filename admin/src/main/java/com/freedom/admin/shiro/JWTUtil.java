package com.freedom.admin.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.freedom.admin.model.User;
import com.freedom.core.config.MyYml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 使用用户密码加密
 */
@Component
public class JWTUtil {

    @Autowired
    private MyYml yml;
    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public boolean verify(String token, User user, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("id", user.getId())
                    .withClaim("username", user.getUserName())
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名
     * @param user 用户
     * @return 加密的token
     */
    public String sign(User user) {
        try {
            Date date = new Date(System.currentTimeMillis() + yml.getJwtExpire());
            // 附带username信息
            return JWT.create()
                    .withClaim("id", user.getId())
                    .withClaim("userName", user.getUserName())
                    .withExpiresAt(date)
                    .sign(Algorithm.HMAC256(yml.getJwtSecret()));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
