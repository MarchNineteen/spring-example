package com.wyb.test.spring.simpleSpring.ioc;

/**
 * @author Marcher丶
 */
public class MiniBean {

    private String name;
    private String password;
    private MiniBean miniBean;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MiniBean getMiniBean() {
        return miniBean;
    }

    public void setMiniBean(MiniBean miniBean) {
        this.miniBean = miniBean;
    }


}
