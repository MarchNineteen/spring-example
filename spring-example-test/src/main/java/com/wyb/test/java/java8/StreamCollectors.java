package com.wyb.test.java.java8;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Marcher丶
 */
public class StreamCollectors {

    public static void main(String[] args) {

        // Collectors
        List<String> strList = Arrays.asList("aaaa", "bb", "cc", "dd", "ee");
        strList.stream().collect(Collectors.toList());

        String s = strList.stream().collect(Collectors.joining());
        System.out.println(s);
        // 逗号连接
        s = strList.stream().collect(Collectors.joining(","));
        System.out.println(s);
        // 逗号连接
        s = strList.stream().collect(Collectors.joining(",", "[", "]"));
        System.out.println(s);

        s = strList.stream().collect(Collectors.collectingAndThen(Collectors.joining(","), String::toUpperCase));
        System.out.println(s);

        Map<Integer, List<String>> map = strList.stream().collect(Collectors.groupingBy(String::length));
        map.keySet().forEach(v -> map.get(v).forEach(System.out::println));

        Map<Integer, Set<String>> map1 = strList.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        map.keySet().forEach(v -> map1.get(v).forEach(System.out::println));

        Map<Integer, Set<String>> map2 = strList.stream()
                .collect(Collectors.groupingByConcurrent(String::length, Collectors.toSet()));
        map.keySet().forEach(v -> map2.get(v).forEach(System.out::println));

        Map<Boolean, List<String>> mapBoolean = strList.stream()
                .collect(Collectors.partitioningBy(v -> v.length() % 2 == 0));
        mapBoolean.keySet().forEach(v -> mapBoolean.get(v).forEach(System.out::println));

        Optional<String> min = strList.stream().collect(Collectors.minBy(Comparator.comparingInt(String::length)));
        System.out.println(min);

        DoubleSummaryStatistics doubleSummaryStatistics = strList.stream()
                .collect(Collectors.summarizingDouble(String::length));
        // {count=5, sum=32.000000, min=5.000000, average=6.400000, max=8.000000}
        System.out.println("doubleSummaryStatistics.toString() = " + doubleSummaryStatistics.toString());

        // BinaryOperator<String> binaryOperator = (o, o2) -> String.valueOf(o.length() > o2.length());
        Optional<String> optional = strList.stream()
                .collect(Collectors.reducing(BinaryOperator.minBy(Comparator.comparing(String::length))));
        optional.ifPresent(System.out::println);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person (1.8, "david", "杭州"));
        personList.add(new Person (1.7, "david", "苏州"));
        personList.add(new Person (1.6, "anna", "杭州"));
        Comparator<Person> byHeight = Comparator.comparing(Person::getHeight);
        Map<String, Optional<Person>> tallestByCity = personList.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.reducing(BinaryOperator.maxBy(byHeight))));

        for (Map.Entry<String, Optional<Person>> entry : tallestByCity.entrySet()) {
             System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue().get());
        }


        // identity为基准
        Person identity= new Person();
        identity.setHeight(1.7);
        Map<String, Person> collect = personList.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.reducing(identity, BinaryOperator.maxBy(byHeight))));

        for (Map.Entry<String, Person> entry : collect.entrySet()) {
            System.out.println("identity key= " + entry.getKey() + " and value= " + entry);
        }

        // 定义映射 处理 四舍五入
        Function<Person, Person> mapper = ps -> {
            Double height = ps.getHeight();
            BigDecimal decimal = new BigDecimal(height);
            Double d = decimal.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            ps.setHeight(d);
            return ps;
        };

        Map<String, Person> collect1 = personList.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.reducing(identity, mapper, BinaryOperator.maxBy(byHeight))));

        for (Map.Entry<String, Person> entry : collect1.entrySet()) {
            System.out.println("mapper key= " + entry.getKey() + " and value= " + entry);
        }
    }

    static class Person {
        private Double height;
        private String name;
        private String city;

        public Person() {
        }

        public Person(Double height, String name, String city) {
            this.height = height;
            this.name = name;
            this.city = city;
        }

        public Double getHeight() {
            return height;
        }

        public void setHeight(Double height) {
            this.height = height;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "height=" + height +
                    ", name='" + name + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }
}
