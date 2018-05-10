package com.wyb.test.json;

import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Kunzite
 */
public class Test {

    public static void main(String[] args) {
        String s= "{\\\"terminalNo\\\":\\\"ZZJ101\\\",\\\"patientName\\\":\\\"\\\",\\\"hospCode\\\":\\\"01\\\",\\\"searchType\\\":\\\"\\\",\\\"cardType\\\":\\\"0\\\",\\\"operId\\\":\\\"ZZJ101\\\",\\\"secrityNo\\\":\\\"\\\",\\\"cardNo\\\":\\\"000115439800\\\",\\\"deviceInfo\\\":\\\"ZZJ101\\\",\\\"extend\\\":\\\"\\\",\\\"tradeTime\\\":\\\"2017-07-20 11:10:14\\\",\\\"sourceCode\\\":\\\"ZZJ\\\",\\\"hospitalId\\\":\\\"702\\\",\\\"service\\\":\\\"yuantu.wap.query.patient.info\\\",\\\"flowId\\\":\\\"\\\",\\\"sign_type\\\":\\\"RSA\\\"}";

        s = s.replace("\\","");
        s = s.trim();
//        s = s.replace("\"","");
        JSONObject jsonObj = JSONObject.parseObject(s);
        System.out.println(jsonObj);
        Iterator<Map.Entry<String, Object>> iterator = jsonObj.entrySet().iterator();
        StringBuilder str = new StringBuilder();
        while (iterator.hasNext()){
            Map.Entry<String,Object> entry = iterator.next();
            if (null != entry.getKey()){
                str.append("params.put(\""+entry.getKey()+"\",\""+entry.getValue()+"\"); \n");
            }
        }
        System.out.println(str);
    }
}
