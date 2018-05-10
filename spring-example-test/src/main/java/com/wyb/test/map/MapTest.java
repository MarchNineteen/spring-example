package com.wyb.test.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Kunzite
 */
public class MapTest {


    /**
     * 在Map集合中
     values():方法是获取集合中的所有的值----没有键，没有对应关系，
     KeySet():
     将Map中所有的键存入到set集合中。因为set具备迭代器。所有可以迭代方式取出所有的键，再根据get方法。获取每一个键对应的值。 keySet():迭代后只能通过get()取key
     entrySet()：
     Set<Map.Entry<K,V>> entrySet() //返回此映射中包含的映射关系的 Set 视图。 Map.Entry表示映射关系。entrySet()：迭代后可以e.getKey()，e.getValue()取key和value。返回的是Entry接口 。
     * @param args
     */
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        for(int i =0;i<10;i++){
            map.put(String.valueOf(i),"value"+i);
        }
        //第一种遍历 map
        System.out.println("通过Map.keySet遍历key和value：");
        for (String key:map.keySet()){
            System.out.println("key= "+ key + " and value= " + map.get(key));
        }

        //第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
//        Map.Entry<String, String> entry1 = map.entrySet();
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第三种：推荐，尤其是容量大时
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }
    }
}
