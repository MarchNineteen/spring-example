package com.wyb.test.datastructure.adt.link;

/**
 * @author Kunzite
 */
public class SimpleLink<E> {

    private Node<E> head;// 头节点
     int size = 0;

    public SimpleLink(){
        head = null;
    }



    private class Node<E> {
        private Node next;// 后置节点
        private E e;// 元素

        public Node(Node next, E e) {
            this.next = next;
            this.e = e;
        }

        //打印该链结点的信息
        public void displayNode(){
            System.out.println("e:" + e);
        }
    }
}




