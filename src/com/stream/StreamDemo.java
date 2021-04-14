package com.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {


    public void demo() {
        List<Integer> aList = Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<Integer> collect = aList.stream().filter(this::checkNum).collect(Collectors.toList());
        int sum = collect.stream().mapToInt(a -> a).sum();
        System.out.println(collect.toString());
        System.out.println(sum);
    }

    public boolean checkNum(int a) {
        if (a > 4) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        StreamDemo streamDemo = new StreamDemo();
        streamDemo.demo();

    }
}
