package com.ifmo.lesson7;

import com.ifmo.lesson6.List;

import java.util.function.Predicate;
import java.util.stream.StreamSupport;

public class Utils {
    public static Object find(Predicate predicate, List list) {
        for (Object object : list) {
            if (predicate.test(object)) return object;
        }
        return null;
    }

    public static Object findUsedStream(Predicate predicate, List list) {
        return StreamSupport.stream(list.spliterator(), false).filter(predicate).findFirst().get();
    }

    public static List filter(Predicate predicate, List list) {
        List result = new ArrayList();
        for (Object object : list) {
            if (predicate.test(object)) {
                result.add(object);
            }
        }
        return result;
    }

    public static List filterUsedStream(Predicate predicate, List list) {
        List result = new ArrayList();
        StreamSupport.stream(list.spliterator(), false).filter(predicate).forEach(n -> result.add(n));
        return result;
    }

    public static List transform(Transformer trans, List list){
        List result = new ArrayList();
        for(Object object: list){
            result.add(trans.apply(object));
        }
        return result;
    }

    public static List transformUsedStream(Transformer trans, List list){
        List result = new ArrayList();
        StreamSupport.stream(list.spliterator(), false).map(f -> trans.apply(f)).forEach(n -> result.add(n));
        return result;
    }
}
