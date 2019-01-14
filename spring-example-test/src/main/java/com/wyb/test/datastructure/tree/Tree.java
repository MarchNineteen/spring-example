package com.wyb.test.datastructure.tree;

/**
 * @author Kunzite
 */
public interface Tree<T> {

    boolean isEmpty();//判空

    int level(T key);//层次

    int size();//结点数

    int height();//高度

    void preorder();//先根次序遍历

    void postorder();//后根次序遍历

    void levelorder(); //层次遍历

    TreeNode<T> insert(T x);//插入元素x作为根

    TreeNode<T> insert(TreeNode<T> p, T x, int i);//插入x作为p结点的第i（≥0）个孩子

    void remove(TreeNode<T> p, int i);//删除子树

    void clear();//删除所有结点

    TreeNode<T> search(T key);//查找

    boolean contains(T key);//包含

    T remove(T key);//删除子树

}

class TreeNode<T> {

}