package com.wyb.test.java.array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Kunzite
 */
public class ArrayUtilsDemo {

    /**
     * asList 数组转化为ArrayList
     */
    public <T> void asList(T[] t) {
        List<T> arrayList = Arrays.asList(t);
        System.out.println("asList方法：" + arrayList.toString());
    }

    /**
     * sort排序和parallelSort并行排序
     * <p>
     * Arrays.deepToString()主要用于数组中还有数组的情况，而Arrays.toString()则相反，
     * 对于Arrays.toString()而言，当数组中有数组时，不会打印出数组中的内容，只会以地址的形式打印出来。
     * <p>
     * parallelSort则采用并行的排序算法排序.但是我自己测试，可能数据量太小，速度上并没有明显的变化。
     */
    public <T> void sort(T[] t) {
        Arrays.sort(t, 2, 5);
        System.out.println("sort排序方法：" + (Arrays.toString(t)));
        System.out.println("sort排序方法：" + (Arrays.deepToString(t)));

//        Arrays.parallelSort(t);
//        System.out.println("parallelSort并行方法：" + (Arrays.toString(t)));
    }

    /**
     * binarySearch 查找目标元素所在的位置，注意需要先进行排序。
     */
    public <T> void binarySearch(T[] t, T t1) {
        System.out.println("未排序binarySearch方法:" + Arrays.binarySearch(t, t1));
        Arrays.sort(t);
        System.out.println("排序后binarySearch方法:" + Arrays.binarySearch(t, t1));
    }

    /**
     * copyOf 拷贝数组
     * 第一种用法，如果目标长度不够，会使用0进行补位
     * 第二种用法，支持拷贝目标起始位置到结束为止的数组。
     */
    public <T> void copyOf(T[] t) {
        // 如果位数不够，需要补位
        T[] newArray = Arrays.copyOf(t, 10);
        System.out.println("位数不够copyOf方法:" + Arrays.toString(newArray));
        // 如果位数够，就取最小的数组
        newArray = Arrays.copyOf(t, 3);
        System.out.println("排序后copyOf方法:" + Arrays.toString(newArray));
        // 指定位置copy
        newArray = Arrays.copyOfRange(t, 1, 3);
        System.out.println("指定copy的起始位置copyOfRange方法:" + Arrays.toString(newArray));
    }

    /**
     * deepEquals深度比较
     * deepHashCode生成hashcode
     * deepToString深度打印
     */
    public <T> void deepEquals(T[] t) {
        String[] array2 = new String[]{"a", "c", "2", "1", "b"};
        System.out.println("深度比较两个数组是否相同deepEquals方法:" + Arrays.deepEquals(t, array2));
        System.out.println(Arrays.deepHashCode(t));
        //如果两个数组deepEquals相同，那么他们的hashcode一定相同
        System.out.println(Arrays.deepHashCode(array2));
        System.out.println(Arrays.deepToString(t));
    }

    /**
     * equals 对比两个数组是否相等
     */
    public <T> void equals(T[] t) {
        String[] array2 = new String[]{"a", "c", "2", "1", "b"};
        //1 对比引用是否相同
        //2 对比是否存在null
        //3 对比长度是否相同
        //4 挨个元素对比
        System.out.println("equals方法:" + Arrays.deepEquals(t, array2));
    }

    /**
     * fill 基于目标元素填充数组
     */
    public <T> void fill(T[] t) {
        Arrays.fill(t, "test");
        System.out.println("equals方法:" + Arrays.toString(t));
    }

    /**
     * stream 把数组转换成stream,然后可以使用java8的stream特性了。
     */
    public <T> void stream(T[] t) {
        Stream s = Arrays.stream(t);
        System.out.println("stream方法:");
        s.forEach(v -> System.out.println(v));
    }

    /**
     * parallelPrefix 根据传入的方法一次计算
     */
    public void parallelPrefix(Integer[] t) {
        Arrays.parallelPrefix(t, (x, y) -> (x + y));
        System.out.println("parallelPrefix方法:" + Arrays.toString(t));
    }

    /**
     * parallelSetAll 这个方法相当于stream.map会挨个元素遍历执行方法
     * setAll 不是并行
     */
    public void parallelSetAll(Integer[] t) {
        // x 代表正在计算的元素索引
        Arrays.parallelSetAll(t, x -> (x*x)); //0, 1, 4, 9, 16
        System.out.println("parallelSetAll方法:" + Arrays.toString(t));
        Arrays.setAll(t, x -> (x*x)); //0, 1, 4, 9, 16
        System.out.println("setAll方法:" + Arrays.toString(t));
    }


    public static void main(String[] args) {
        String[] array = new String[]{"a", "c", "2", "1", "b"};
        Integer[] ints = new Integer[]{5, 1, 4, 3, 2};
        ArrayUtilsDemo demo = new ArrayUtilsDemo();
        demo.asList(array);
        demo.sort(array);
        demo.binarySearch(array, "c");
        demo.copyOf(ints);
        demo.deepEquals(array);
        demo.equals(array);
        demo.fill(array);
        demo.stream(array);
        demo.parallelPrefix(ints);
        demo.parallelSetAll(ints);
    }
}
