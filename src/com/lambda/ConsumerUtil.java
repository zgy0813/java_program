package com.lambda;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Y
 */
public class ConsumerUtil {
    public static <E> void foreach(List<E> list, Consumer<E> consumer) {
        List<E> retList = new ArrayList<>();
        for (E t : list) {
            consumer.accept(t);
        }
    }
}
