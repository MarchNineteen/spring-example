package com.wyb.shiro.realm;

import lombok.ToString;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Kunzite
 */
@ToString
public class UserToken implements AuthenticationToken {

    private String username;

    private String password;

    public UserToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return password;
    }
}
