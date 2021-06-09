package com.wyb.test.java.string;

/**
 * @author Marcher丶
 */
public class StringMaxLength {

    public static void main(String[] args) {
        /**
         * 理论上 s.length()返回值是int int最大值为2^31 - 1 = 2147483647,即存在元素最大的个数为2147483647个 s.length()
         * value.length;value是char数组，在java中char占16位，2个字节，存储Unicode码，用单引号赋值 所以最大的存储空间为 2147483647 * 2 = 4294967294 (Byte)
         * = = 3.99999999813735485076904296875 (GB) ≈ 4GB
         */

        /**
         * 编译时
         */
        String s = "11"; // 65534个1
        System.out.println(s.length());

        /**
         * 运行时 String运行期的限制主要体现在String的构造函数上。String的一个构造函数如下： public String(char value[], int offset, int count) { //
         * ... } 其中参数count就是字符串的最大长度。此时的计算与前面的算法一致，这里先转换为bit，然后再转换为GB： (2^31-1)*16/8/1024/1024/1024 = 4GB 复制代码
         * 也就是说，运行时理论上可以支持4GB大小的字符串，超过这个限制就会抛出异常的。JDK9对String的存储进行了优化，底层使用byte数组替代了char数组，对于纯Latin1字符来说可以节省一半的空间。
         * 当然，这个4GB的限制是基于JVM能够分配这么多可用的内存的前提下的。
         *
         */

        // 在编译期字符串的长度不能超过65534；第二，在运行期，字符串的长度不能超过2^31-1，占用内存（4GB）不能超过虚拟机所分配的最大内存
    }
}
