package com.wyb.test.java.map;

/**
 * @author Kunzite
 */
public class MyHashMap<K, V> {

    private Node<K, V>[] table;// node数组
    private int capacity;// 数组容量
    private int default_capacity = 1 << 2;// 默认数组容量
    private int max_capacity;// 最大数组容量
    private int size;// Table数组当前所有元素数
    private float loadFactor;// 负载因子
    private float default_loadFactor = 0.75f;// 负载因子
    private int threshold;// 扩容阈值

    // 采用懒加载 数组、阈值等信息在put时加载
    public MyHashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = 0.75f;
    }

    public MyHashMap() {
        loadFactor = default_loadFactor;
    }

    public V get(K key) {
        int n, i;// n 数组大小 i index
        Node<K, V> first, next;
        n = table.length;
        i = hash(key) & (n - 1);
        // 数组上有值
        first = table[i];
        if (null != first) {
            //判断数组上第一个是不是要查找元素
            if (first.hash == hash(key) && key.hashCode() == first.k.hashCode()) {
                return first.v;
            }
            // 遍历直到遇到相同的元素 或者 遍历到链表尾部
            if ((next = first.next) != null) {
                do {
                    if (next.hash == hash(key) && key.hashCode() == next.k.hashCode()) {
                        return next.v;
                    }
                } while ((next = next.next) != null);
            }
        }
        return null;
    }

    public V put(K key, V val) {
        int n, i;// n 数组大小 i index
        Node<K,V> firstNode;
        if (null == table || table.length == 0) {
            // 初始化扩容
            table = resize();
        }
        int hash = hash(key);
        // 数组为为空
        if (table[i = (hash & (table.length - 1))] == null) {
            table[i] = new Node<>(hash, key, val, null);
        }
        // 发送hash冲突
        else {
            Node<K,V> next;
            // 数组位上就是要查的数
            firstNode = table[i];
            if (firstNode.hash == hash && firstNode.k.hashCode() == key.hashCode()) {
                next = firstNode;
            }
            else {
                for (int j = 0; ; ++j) {
                    // 遍历直到遇到相同的元素 或者 遍历到链表尾部
                    if ((next = firstNode.next) == null) {
                        firstNode.next = new Node(hash, key, val, null);
                        break;
                    }
                    if (next.hash == hash && (key == next.k || (key != null && key.equals(next.k)))) {
                        break;
                    }
                    firstNode = next;
                }

            }
            if (next != null) {
                V oldValue = next.v;
                next.v = val;
                return oldValue;
            }
        }
        if (++size > threshold) {
            resize();
        }
        return null;
    }

    // hash
    static final int hash(Object key) {
        int h = key.hashCode();
        return (null == key) ? 0 : h ^ h >> 16;
    }

    // 初始化以及扩容
    private Node<K, V>[] resize() {
        Node<K, V>[] oldTable = table;
        // 初始化需要参数：capacity threshold
        int oldCap = null == table ? 0 : oldTable.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= max_capacity) {
                threshold = Integer.MAX_VALUE;
                return oldTable;
            }
            newCap = oldCap << 1;
            if (newCap < max_capacity && oldCap >= default_capacity) {
                newThr = oldThr << 1;
            }
        } else {
            newCap = default_capacity;
            newThr = (int) (default_loadFactor * default_capacity);
        }
        threshold = newThr;
        Node<K, V>[] newTable = new Node[newCap];
        table = newTable;
        // todo 扩容
        return newTable;
    }

    // 结点
    private class Node<K, V> {
        int hash;// hash值，用于判断是否相等
        K k;
        V v;
        Node<K, V> next;// 下一个节点

        public Node(int hash, K k, V v, Node<K, V> next) {
            this.hash = hash;
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        for (int i = 0; i < 100; i++) {
            map.put(i, i);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(map.get(i));
        }
    }
}
