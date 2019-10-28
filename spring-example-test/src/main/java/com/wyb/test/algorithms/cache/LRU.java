package com.wyb.test.algorithms.cache;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Marcher丶
 * 缓存lru淘汰策略实现
 */
public class LRU<K, V> implements Iterable<K> {

    private Node head;
    private Node tail;
    private HashMap<K, Node> map;
    private int maxSize;

    public LRU(int maxSize) {
        this.maxSize = maxSize;
        this.map = new HashMap<K, Node>(maxSize * 4 / 3);

        // 增加头尾节点 不删除
        head = new Node(null, null);
        tail = new Node(null, null);

        head.next = tail;
        tail.pre = head;
    }

    public V get(K k) {
        if (!map.containsKey(k)) {
            return null;
        }

        // 使用过先删除 后增加节点在头部
        Node node = map.get(k);
        removeNode(node);
        appendHead(node);
        return node.v;
    }

    public void put(K k, V v) {
        if (map.containsKey(k)) {
            // 删除原来的node
            Node node = map.get(k);
            removeNode(node);
        }

        // 新增一个节点增加在头上
        Node node = new Node(k, v);
        map.put(k, node);
        appendHead(node);

        // 验证是否需要清除
        if (map.size() > maxSize) {
            // 找到并移除链表尾部节点
            Node lastNode = removeTail();
            map.remove(lastNode.k);
        }
    }

    /**
     * 删除节点
     */
    private void removeNode(Node node) {
        Node pre = node.pre;
        Node next = node.next;

        pre.next = next;
        next.pre = pre;

        node.pre = null;
        node.next = null;
    }

    /**
     * 在头部增加节点 head的后一个
     */
    private void appendHead(Node node) {
        Node next = head.next;

        next.pre = node;
        node.next = next;
        node.pre = head;
        head.next = node;
    }

    /**
     * 移除尾部 tail的前一个
     */
    private Node removeTail() {
        Node node = tail.pre;

        //倒数第二个节点
        Node pre = node.pre;
        pre.next = tail;
        tail.pre = pre;

        node.pre = null;
        node.next = null;

        return node;
    }

    private class Node {

        Node pre;
        Node next;
        K k;
        V v;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<K>() {

            private Node cur = head.next;

            @Override
            public boolean hasNext() {
                return cur != tail;
            }

            @Override
            public K next() {
                Node node = cur;
                cur = cur.next;
                return node.k;
            }
        };
    }

    public static void main(String[] args) {
        LRU<String, String> lru = new LRU<>(5);
        lru.put("1", "1");
        lru.put("2", "2");
        lru.put("3", "3");
        lru.put("4", "4");
        lru.put("5", "5");
//        for (String s : lru) {
//            System.out.println(lru.get(s));
//        }
//        System.out.println("执行过get后链表顺序: \r");
        for (String s : lru) {
            System.out.println(s);
        }
        lru.put("6", "6");
        System.out.println("覆盖后: \r");
        for (String s : lru) {
            System.out.println(lru.get(s));
        }
    }
}
