package com.wyb.test.algorithms.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Marcher丶
 */
public class LinkedHashMapLRU<K, V> {

    private LinkedHashMap<K, V> map;
    private int maxSize;

    public LinkedHashMapLRU(int maxSize) {
        int capacity = (int) Math.ceil(maxSize / 0.75f) + 1;

        this.map = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            private static final long serialVersionUID = 1L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxSize;
            }
        };
        this.maxSize = maxSize;
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void remove(K key) {
        map.remove(key);
    }

    public synchronized Set<Map.Entry<K, V>> getAll() {
        return map.entrySet();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            stringBuilder.append(String.format("%s: %s  ", entry.getKey(), entry.getValue()));
        }
        return stringBuilder.toString();
    }
//    public void put(K k, V v) {
//        if (map.containsKey(k)) {
//            map.remove(k);
//        }
//        map.put(k, v);
//        if (map.size() > maxSize) {
//            map.removeEldestEntry
//        }
//    }


    public static void main(String[] args) {
        LinkedHashMapLRU<String, String> lru = new LinkedHashMapLRU<>(5);
        lru.put("球员1", "杜兰特");
        lru.put("球员2", "表妹");
        lru.put("球员3", "库里");
        System.out.println(lru);
        lru.get("球员1");
        System.out.println(lru);
        lru.put("球员4", "一哥");
        lru.put("球员5", "汤姆");
        System.out.println(lru);
        lru.put("球员6", "格林");
        System.out.println(lru);
    }
}
