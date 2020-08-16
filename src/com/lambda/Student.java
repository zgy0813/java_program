package com.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Y
 */
public class Student {
    private String name;
    private double score;

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("zhangsan2",95d),
                new Student("zhangsan",89d),
                new Student("lisi",89d),
                new Student("wangwu",98d));

        // 分数大于90的学生集合
        List<Student> students1 = PredicateUtil.filter(students, t -> t.getScore() > 90);
        System.out.println(students1.toString());

        List<Integer> intArray = Arrays.asList(19,20,21);
        intArray = PredicateUtil.intFilter(intArray,t -> t > 20);
        System.out.println(intArray.toString());

        // 取学生名字组成新的集合 直接调用对象的某个方法，可以更加简化成 :: 如下
        // 方法引用由 :: 分隔，前面是类名或者对象名，后面是方法名
        List<String> names = FunctionUtil.map(students1, Student::getName);
        System.out.println(names.toString());

        // 将学生名字转换成大写 直接修改了原对象
        ConsumerUtil.foreach(students, t -> t.setName(t.getName().toUpperCase()));
        System.out.println(students.toString());

        // 按照分数进行 倒序 排序，分数相同的话按照姓名排序
        // reversed() 倒序
        // thenComparing() 排序相同下，后续排序规则
        students.sort(Comparator.comparing(Student::getScore)
                                .reversed()
                                .thenComparing(Student::getName));

    }

    public Student(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}

