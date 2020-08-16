package com.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @author Y
 */
public class PredicateUtil {
    public static <E> List<E> filter(List<E> list, Predicate<E> pred) {
        List<E> retList = new ArrayList<>();
        for (E e : list) {
            if (pred.test(e)) {
                retList.add(e);
            }
        }
        return retList;
    }

    public static List<Integer> intFilter(List<Integer> list, IntPredicate pred) {
        List<Integer> retList = new ArrayList<>();
        for (Integer e : list) {
            if (pred.test(e)) {
                retList.add(e);
            }
        }
        return retList;
    }
}
