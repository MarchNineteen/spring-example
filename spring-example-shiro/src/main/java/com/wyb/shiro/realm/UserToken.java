package com.wyb.shiro.realm;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author kunzite
 */
public class UserToken implements AuthenticationToken {

    private String token;

    public UserToken(String password) {
        this.token = password;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
