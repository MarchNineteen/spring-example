package com.wyb.shiro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Kunzite
 *
 * 生成token时使用
 */
@Component
public class JWTConfig {

    @Value("${jwt.header}")
    private String jwtHeader;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.issuer}")
    private String issure;

    public String getJwtHeader() {
        return jwtHeader;
    }

    public void setJwtHeader(String jwtHeader) {
        this.jwtHeader = jwtHeader;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public String getIssure() {
        return issure;
    }

    public void setIssure(String issure) {
        this.issure = issure;
    }

}
