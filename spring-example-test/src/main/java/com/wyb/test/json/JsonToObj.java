package com.wyb.test.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Kunzite
 */
public class JsonToObj {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lsh","1111");
        jsonObject.put("yb","2222");
        jsonObject.put("xj","3333");
        String s = jsonObject.toString();
        JSONObject object = JSON.parseObject(s);
        System.out.println(object);
    }
}
