## 流操作类型

### 中间操作

目的是打开一个流，做出一些操作或者映射后，会返回一个新的流交给下个操作。lazy的，在调用方式时并没有真正执行。

### 终止操作

在执行这个操作后，流被使用，在出现终止操作时才会调用之前的所有方法。

### short-circuiting

对于一个 intermediate 操作，如果它接受的是一个无限大（infinite/unbounded）的 Stream，但返回一个有限的新 Stream。
对于一个 terminal 操作，如果它接受的是一个无限大的 Stream，但能在有限的时间计算出结果。

## 具体流操作 

### 中间操作

- map (mapToInt, flatMap 等)

把input Stream 的每一个元素，映射成 output Stream 的另外一个元素。

map一对一

```
//list中数的平方
list.stream().map(v -> v * v) 
```

flatMap一对多 多对多 映射,把流扁平化，多个流转化成流里所有元素的单个流。

```
List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> list2 = Arrays.asList(6, 7, 8, 9, 10);
List<List<Integer>> allList = new ArrayList<>();
allList.add(list1);
allList.add(list2);
        
allList.stream().flatMap(v -> v.stream()).forEach(System.out::println);        
```

- filter 

过滤,对元素进行某种条件过滤，留下符合的

```
// 留下偶数
list.stream().filter(v -> v % 2 == 0).forEach(System.out::println);
```

- distinct 

去重，不接收方法
```
list.stream().distinct().forEach(System.out::println);
```
- sorted

排序，接受一个Comparator，或者默认顺序

```
list.stream().sorted()
list.stream().sorted((o1, o2) -> -1)
```

- peek

peek 对每个元素执行操作并返回一个新的 Stream。类似foreach，foreach是终止操作

```
list = list.stream().peek(System.out::println).collect(Collectors.toList());
```

- limit

返回 Stream 的前面 n 个元素

```
list.stream().limit(2).forEach(System.out::println);
```

- skip

扔掉前 n 个元素（它是由一个叫 subStream 的方法改名而来）

```
list.stream().skip(2).forEach(System.out::println);
```
- parallel

转行成并行流，进行多线程操作，顺序会改变。可以使用forEachOrdered，固定顺序
```
list.stream().parallel().forEach(System.out::println);
3
4
1
5
2
```

- sequential

并行流转为串行流
```
list.parallelStream().forEach(System.out::println);
list.parallelStream().sequential().forEach(System.out::println);
```

- unordered

**消除了必须保持有序的流的约束**,返回一个无序的等效的Stream，可能返回的是Stream本身，因为该Stream已经是无序的，或者该Stream的底层状态被修改为了无序.
一般用于并行的时候。例如TreeSet，或者流操作中指定顺序，会取消排序和指定顺序无效。

### 终止操作

- forEach

forEach 不能修改自己包含的本地变量值，也不能用 break/return 之类的关键字提前结束循环。

- forEachOrdered

- toArray

- reduce

这个方法的主要作用是把 Stream 元素组合起来。它提供一个起始值（种子），然后依照运算规则（BinaryOperator），和前面 Stream 的第一个、第二个、第 n 个元素组合。
从这个意义上说，字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce。

注意：没有起始值，返回对象为Optional

- collect

两个方法：

```
<R> R collect(Supplier<R> supplier,
                  BiConsumer<R, ? super T> accumulator,
                  BiConsumer<R, R> combiner);
                  
IntStream i = IntStream.of(6,5,7,1, 2, 3, 3);
  List<Integer> v = i
      .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
  
  System.out.println(v);

<R, A> R collect(Collector<? super T, A, R> collector);
```

Collector在后面重点讲解

- min

- max

- count

- anyMatch

- allMatch

- noneMatch

- findFirst

- findAny

- iterator

### Short-circuiting

- anyMatch

- allMatch 

- noneMatch

- findFirst 

- findAny 

- limit

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

