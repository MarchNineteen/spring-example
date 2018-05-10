package com.wyb.test.generic;


import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型demo
 */
public class GenericDoTest {


    public static void main(String[] args) {

        Person man = new Person("男","www",11);
        Person women = new Person("女","zzz",22);
        Person man1 = new Person("男","yyy",33);

        List<Person> people = new ArrayList<>();
        people.add(man);
        people.add(women);
        people.add(man1);

        sys(people);
        sysStud(man);
        Student student = new Student("男","www",11);


        student.setClassName("1班");
        student.setDescription(121);

        List<Student<?>> students = new ArrayList<>();
        students.add(student);
        sysStud(student);
        sysAll(students);
    }

    public static void sys(List<Person> list){
        for (Person list1 :list){
            System.out.println(list1.toString());
        }
    }
    public static void sysAll(List<? extends Person> list){
        for (Person list1 :list){
            System.out.println(JSONObject.toJSONString(list));

        }
    }

    public static void sysStud(Person person){
            System.out.println(person.toString());
    }
}
