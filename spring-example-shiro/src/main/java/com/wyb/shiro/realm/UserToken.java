package com.wyb.shiro.realm;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author kunzite
 */
public class UserToken implements AuthenticationToken {


    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
