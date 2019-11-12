package com.ifmo.lesson9;

public interface ArithmeticOperation<T extends Number>{
    T apply(T arg1, T arg2);
}
