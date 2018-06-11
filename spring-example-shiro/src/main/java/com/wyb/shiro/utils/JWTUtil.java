package com.wyb.shiro.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wyb.shiro.config.JWTConfig;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author kunizte
 */
public class JWTUtil {

    @Resource
    private JWTConfig jwtConfig;

    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, Long uid, String secret, String issuer) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("uid", uid)
                    .withIssuer(issuer)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户id
     */
    public static Long getUid(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("uid").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名（token）,5min后过期
     *
     * @param uid    用户Id
     * @param secret 用户的密码(或者配置的jwt密码)
     * @param issuer JWT签发者
     * @return 加密的token
     */
    public static String sign(Long uid, String secret, String issuer) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withIssuer(issuer)
                    .withClaim("uid", uid)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 生成JWT
     * @param secret    JWT密钥
     * @param uid       用户唯一标识
     * @param issuer    JWT签发者
     *  <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
     * @return
     */
//    public static String generateJWT(String secret, Long uid, String issuer) {
//        Date now = new Date();
//        return Jwts.builder().setClaims(new DefaultClaims().setIssuer(issuer).setIssuedAt(now)
//                .setSubject(uid.toString())).signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//    }
}
