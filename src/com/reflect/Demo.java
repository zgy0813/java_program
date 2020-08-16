package com.reflect;

import java.util.Date;

/**
 * @author Y
 */
public class Demo {
    enum Size {
        SMALL, MEDIUM, BIG
    }

    public static void main(String[] args) {
        // 普通类
        Class<Date> clsDate = Date.class;
        // 接口类
        Class<Comparable> clsComparable = Comparable.class;
        // 基本类型没有getClass方法，但也都有对象的Class对象，类型参数为对应的包装类型
        Class<Integer> clsInteger = int.class;
        Class<Byte> clsByte = byte.class;
        Class<Character> clsCharacter = char.class;
        Class<Double> clsDouble = double.class;
        // void作为特殊的返回类型，也有对象的Class
        Class<Void> voidClass = void.class;
        // 对于数组，每种类型都有对应数组类型的Class对象，每个维度都有一个，即一维数组有一个，二维数组有一个不同的类型
        String[] strArr = new String[10];
        int[][] twoDimArr = new int[3][3];
        int[] oneDimArr = new int[10];
        Class<? extends String[]> strArrCls = strArr.getClass();
        // [Ljava.lang.String;
        System.out.println(strArrCls.getName());
        Class<? extends int[][]> twoDimArrCls = twoDimArr.getClass();
        // [[I
        System.out.println(twoDimArrCls.getName());
        Class<? extends int[]> oneDimArrCls = oneDimArr.getClass();
        // [I
        System.out.println(oneDimArrCls.getName());
        // 枚举类型也有对应的Class
        Class<Size> sizeCls = Size.class;
        // Class有个静态方法forName可以根据类名直接加载Class，获取Class对象
        try {
            Class<?> hashMapCls = Class.forName("java.util.HashMap");
            // java.util.HashMap
            System.out.println(hashMapCls.getName());
        } catch (ClassNotFoundException e) {
            // 可能会抛出异常ClassNotFoundException
            e.printStackTrace();
        }


        ClassLoader classLoader = clsDate.getClassLoader();
    }
}
