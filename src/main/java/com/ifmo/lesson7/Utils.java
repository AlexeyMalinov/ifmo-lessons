package com.ifmo.lesson7;

import java.util.List;
import java.util.function.Predicate;

public class Utils {
    public static Object find(List list, Predicate predicate){
        for (Object object: list){
            if(predicate.test(object)) return object;
        }
        return null;
    }
}
