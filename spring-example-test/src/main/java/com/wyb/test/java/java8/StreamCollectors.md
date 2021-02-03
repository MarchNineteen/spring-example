## Collectors 

要作用就是辅助进行各类有用的 reduction 操作，例如转变输出为 Collection，把 Stream 元素进行归组。

- 类型归纳 

```
Collectors.toList();
Collectors.toMap();
Collectors.toSet();
Collectors.toCollection();
Collectors.toConcurrentMap();
```

- joining

```java
List<String> strList = Arrays.asList("aa", "bb", "cc", "dd", "ee");
String s = strList.stream().collect(Collectors.joining());
System.out.println(s);
// 逗号连接
s = strList.stream().collect(Collectors.joining(","));
System.out.println(s);
// 逗号连接
s = strList.stream().collect(Collectors.joining(",", "[", "]"));
System.out.println(s);
``` 

- collectingAndThen

```java
s = strList.stream().collect(Collectors.collectingAndThen(Collectors.joining(","), String::toUpperCase));
```

- groupingBy groupingByConcurrent

```java
Map<Integer, List<String>> map = strList.stream().collect(Collectors.groupingBy(String::length));
map.keySet().forEach(v -> map.get(v).forEach(System.out::println));
    
Map<Integer, Set<String>> map1 = strList.stream().collect(Collectors.groupingBy(String::length, Collectors.toSet();
        
```

- partitioningBy

```java
Map<Boolean, List<Integer>> mapBoolean = list.stream().collect(Collectors.partitioningBy(v -> v % 2 == 0));
mapBoolean.keySet().forEach(v -> mapBoolean.get(v).forEach(System.out::println));
```

- summingInt/Double/Long summarizingInt/Double/Long

- reducing

> https://juejin.cn/post/6844904036831854599#heading-13

