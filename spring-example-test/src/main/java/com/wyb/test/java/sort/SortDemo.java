package com.wyb.test.java.sort;

/**
 * java 排序demo
 *
 * @author Kunzite
 * <p>
 * 一、稳定性:
 * 稳定：冒泡排序、插入排序、归并排序和基数排序
 * 不稳定：选择排序、快速排序、希尔排序、堆排序
 * 二、平均时间复杂度
 * O(n^2):直接插入排序，简单选择排序，冒泡排序。
 * 在数据规模较小时（9W内），直接插入排序，简单选择排序差不多。当数据较大时，冒泡排序算法的时间代价最高。性能为O(n^2)的算法基本上是相邻元素进行比较，基本上都是稳定的。
 * O(nlogn):快速排序，归并排序，希尔排序，堆排序。
 * 其中，快排是最好的， 其次是归并和希尔，堆排序在数据量很大时效果明显。
 * 三、排序算法的选择
 * 1.数据规模较小
 * （1）待排序列基本序的情况下，可以选择直接插入排序；
 * （2）对稳定性不作要求宜用简单选择排序，对稳定性有要求宜用插入或冒泡
 * 2.数据规模不是很大
 * （1）完全可以用内存空间，序列杂乱无序，对稳定性没有要求，快速排序，此时要付出log（N）的额外空间。
 * （2）序列本身可能有序，对稳定性有要求，空间允许下，宜用归并排序
 * 3.数据规模很大
 * （1）对稳定性有求，则可考虑归并排序。
 * （2）对稳定性没要求，宜用堆排序
 * 4.序列初始基本有序（正序），宜用直接插入，冒泡
 */
public class SortDemo {

    //从大到小排序
    public void bigToSmall(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] < a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        System.out.print("从大到小排序后:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    //从小到大排序
    public void smallToBig(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        System.out.print("从小到大排序后:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    //直接插入排序
    //直接插入排序的平均时间复杂度为O(n2)
    public void insertSort(int[] a) {
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        //直接插入排序
        for (int i = 1; i < a.length; i++) {
            //待插入元素
            int temp = a[i];
            int j;
            /*for (j = i-1; j>=0 && a[j]>temp; j--) {
                //将大于temp的往后移动一位
                a[j+1] = a[j];
            }*/
            for (j = i - 1; j >= 0; j--) {
                //将大于temp的往后移动一位
                if (a[j] > temp) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = temp;
        }
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    //希尔排序
    //希尔排序的平均时间复杂度为O(nlogn)。
    public void Hill(int[] a) {
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        //希尔排序
        int d = a.length;
        while (true) {
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < a.length; i = i + d) {
                    int temp = a[i];
                    int j;
                    for (j = i - d; j >= 0 && a[j] > temp; j = j - d) {
                        a[j + d] = a[j];
                    }
                    a[j + d] = temp;
                }
            }
            if (d == 1) {
                break;
            }
        }
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    //二分法
    private void sort(int[] a) {
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        for (int i = 0; i < a.length; i++) {
            int temp = a[i];
            int left = 0;
            int right = i - 1;
            int mid = 0;
            while (left <= right) {
                mid = (left + right) / 2;
                if (temp < a[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                a[j + 1] = a[j];
            }
            if (left != i) {
                a[left] = temp;
            }
        }
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    //简单选择排序是不稳定的排序。时间复杂度：T(n)=O(n2)。
    public void choose(int[] a) {
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        //简单的选择排序
        for (int i = 0; i < a.length; i++) {
            int min = a[i];
            int n = i; //最小数的索引
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < min) {  //找出最小的数
                    min = a[j];
                    n = j;
                }
            }
            a[n] = a[i];
            a[i] = min;

        }
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    //堆排序
    //快速排序
    //归并排序
    //基数排序
    public static void main(String[] args) {
        int a[] = {100, -2, 144, -44, 0, 44545646, -5544544, 3};
        SortDemo SortDemo = new SortDemo();
//        SortDemo.bigToSmall(a);
//        SortDemo.smallToBig(a);
//        SortDemo.insertSort(a);
//        SortDemo.Hill(a);
//        SortDemo.choose(a);
        SortDemo.sort(a);
    }
}


