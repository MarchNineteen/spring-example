package com.wyb.test.java.java8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * jdk 1.8  stream 操作最强实战
 *
 * @author Marcher丶
 */
public class StreamApiAction {

    private static List<Integer> static_list = Arrays.asList(1, 2, 3, 4, 5);

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
//        map(list);
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(6, 7, 8, 9, 10);
        List<List<Integer>> allList = new ArrayList<>();
        allList.add(list1);
        allList.add(list2);
//        flatMap(allList);
        // 留下偶数
//        filter(list);
        List<Integer> list3 = Arrays.asList(1, 2, 2, 2, 5);
//        distinct(list3);
//        sorted(list);
//        peek(list);
//        limit(list);
//        skip(list);
//        parallel(list);
//        sequential(list);
//        unordered(list);
//        Long[] aa = list.stream().map(value -> value * value).toArray(Long[]::new);
//        System.out.println(list.stream().reduce(0, Integer::sum));
//        Optional<Integer> optional = list.stream().reduce(Integer::sum);
//        System.out.println(optional.get());

        Integer num = list.stream().mapToInt(Integer::intValue).sum();
    }

    private static void map(List<Integer> list) {
        list = list.stream().map(v -> v * v).collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    private static void flatMap(List<List<Integer>> list) {
        list.stream().flatMap(v -> v.stream()).forEach(System.out::println);
    }

    private static void filter(List<Integer> list) {
        list.stream().filter(v -> v % 2 == 0).forEach(System.out::println);
    }

    private static void distinct(List<Integer> list) {
        list.stream().distinct().forEach(System.out::println);
    }

    private static void sorted(List<Integer> list) {
        System.out.println("从小到大");
        list.stream().sorted().forEach(System.out::println);
        System.out.println("从大到小");
        list.stream().sorted((o1, o2) -> -1).forEach(System.out::println);
    }

    private static void peek(List<Integer> list) {
        list = list.stream().peek(System.out::println).collect(Collectors.toList());
    }

    private static void limit(List<Integer> list) {
        list.stream().limit(2).forEach(System.out::println);
    }

    private static void skip(List<Integer> list) {
        list.stream().skip(2).forEach(System.out::println);
    }

    private static void parallel(List<Integer> list) {
        list.stream().parallel().forEach(System.out::println);
        list.stream().parallel().forEachOrdered(System.out::println);
    }

    private static void sequential(List<Integer> list) {
        list.parallelStream().forEach(System.out::println);
        list.parallelStream().sequential().forEach(System.out::println);
    }

    private static void unordered(List<Integer> list) {
        list.forEach(System.out::println);
        list.stream().unordered().forEach(System.out::println);
    }

}
