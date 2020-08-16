package com.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @author Y
 */
public class FunctionUtil {
    public static <T,R> List<R> map(List<T> list, Function<T,R> mapper) {
        List<R> retList = new ArrayList<>();
        for (T t : list) {
            retList.add(mapper.apply(t));
        }
        return retList;
    }
}
