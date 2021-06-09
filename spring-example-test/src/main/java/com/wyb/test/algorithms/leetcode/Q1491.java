package com.wyb.test.algorithms.leetcode;

import java.util.Arrays;

/**
 * @author Marcher丶
 */
public class Q1491 {

    public double average(int[] salary) {
        Arrays.sort(salary);
        double sum = 0;
        for (int i = 1; i < salary.length - 1; i++) {
            sum += salary[i];
        }
        return sum / (salary.length - 2);
    }

    public static void main(String[] args) {
        Q1491 a = new Q1491();
        System.out.println(a.average(new int[] { 6000, 5000, 4000, 3000, 2000, 1000 }));
    }

}
