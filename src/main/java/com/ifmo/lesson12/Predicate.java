package com.ifmo.lesson12;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
