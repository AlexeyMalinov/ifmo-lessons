package com.ifmo.lesson12;

@FunctionalInterface
public interface Transformer<T, R> {
    R transform(T t);
}
