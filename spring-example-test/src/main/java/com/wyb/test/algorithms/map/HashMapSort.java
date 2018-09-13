package com.wyb.test.algorithms.map;

import java.util.*;

/**
 * @author Kunzite
 * <p>
 * <p>
 * 对hashmap里的值进行排序
 * 1.可以取出entry 放入list
 * 2.手动排序，放入linkedHashMap
 *
 * ibatis的queryForMap是查出的结果是无序的,即便是sql中有orderby,即便是ibatis文件中指定了返回值类型(有序类型,如TreeMap,LinkedHashMap),
 * ibatis对数据的填充到底是怎么弄的,我也不清楚,所以才只能在内存中排序,也不失是一种办法
 * </p>
 */
public class HashMapSort {

    public static void main(String[] args) {

        Map<String, String> oldTmp = new HashMap<>();
        oldTmp.put("1","1");
        oldTmp.put("3","3");
        oldTmp.put("2","2");
        oldTmp.put("44","44");
        oldTmp.put("33","33");
        oldTmp.put("111","111");


        Map<String, String> myMap = new LinkedHashMap<String, String>();
        List<String> keyList = new ArrayList<String>();
        Iterator<String> it = oldTmp.keySet().iterator();
        while (it.hasNext()) {
            keyList.add(it.next());
        }
        Collections.sort(keyList);

        Iterator<String> it2 = keyList.iterator();
        while (it2.hasNext()) {
            String key = it2.next();
            myMap.put(key, oldTmp.get(key));
        }
        Iterator<String> it3 = myMap.keySet().iterator();
        while (it3.hasNext()) {
            String key = it3.next();
            System.out.println("key:" +key + ",value:" + myMap.get(key));
        }


    }

}
