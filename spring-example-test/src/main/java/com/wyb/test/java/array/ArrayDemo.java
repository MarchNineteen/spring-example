package com.wyb.test.java.array;

import java.util.Scanner;

/**
 * @author Kunzite
 * <p>
 * 数组
 * <p>
 * 二维数组:数组的数组---二维数组的每一个元素是一个一维数组
 * 实现杨辉三角:
 * 杨辉三角用二维数组的理解：arr[i][j] = arr[i -1][j] + arr[i - 1][j - 1]
 * </p>
 */
public class ArrayDemo {

    public static void main(String[] args) {
        //声明数组
        String[] a;
        String b[];
        //数组静态初始化
        a = new String[]{"a1", "a1"};//直接为数组元素分配空间并赋值 可在声明时直接初始化 即：
        String[] a1 = {"a1", "a1"};// 此时 new String[]{}， new String 可以省略
        //数组动态初始化
        b = new String[2];//指定长度 长度从0起
        b[0] = "b0";
        b[1] = "b1";
        //循环取值
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        for (String b1 : b) {
            System.out.println(b1);
        }
        // 二维数组的声明和初始化
        int[][] twoAarray1 = new int[2][3];// 第一个【】表示一维数组的个数  第二个表示每个一维数组的长度 int[2][3] 表示2个一维数组 每个一维数组长度为3
        int[][] twoAarray2 = new int[1][];// 只指定一维数组的个数，不知道每个一维数组的长度  使用是需要再初始化每个一维数组
        twoAarray2[0] = new int[1];
        twoAarray2[0][0] = 5;
        System.out.println("当前二维数组的长度：" + twoAarray2.length);
        for (int i = 0; i < twoAarray2.length; i++) {
            for (int j = 0; j < twoAarray2[i].length; j++) {
                System.out.println("当前是二维数组的第" + i + "个数组，长度为:" + twoAarray2[i].length + ",数值为：" + twoAarray2[i][j]);
            }
        }

        int[] twoa1 = twoAarray1[0];// twoAarray1[0] 表示第一个一维数组
        int aa = twoAarray1[1][1];// 如果要获取具体的元素需要两个下标 第一个数组 第三个位置的值
        int intArray[][] = {{1, 2}, {2, 3}, {3, 4, 5}, {1, 1, 1, 1, 1}};
        System.out.println("当前二维数组的长度：" + intArray.length);
        for (int i = 0; i < intArray.length; i++) {
            for (int j = 0; j < intArray[i].length; j++) {
                System.out.println("当前是二维数组的第" + i + "个数组，长度为:" + intArray[i].length + ",数值为：" + intArray[i][j]);
            }
        }

        // 杨辉三角  从控制台获取行数
        Scanner s = new Scanner(System.in);
        int row = s.nextInt();
        //根据行数定义好二维数组，由于每一行的元素个数不同，所以不定义每一行的个数
        int[][] arr = new int[row][];
        //遍历二维数组
        for(int i = 0; i < row; i++){
            //初始化每一行的这个一维数组
            arr[i] = new int[i + 1];
            //遍历这个一维数组，添加元素
            for(int j = 0; j <= i; j++){
                //每一列的开头和结尾元素为1，开头的时候，j=0，结尾的时候，j=i
                if(j == 0 || j == i){
                    arr[i][j] = 1;
                } else {//每一个元素是它上一行的元素和斜对角元素之和
                    arr[i][j] = arr[i -1][j] + arr[i - 1][j - 1];
                }
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

}
