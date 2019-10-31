package com.ifmo.lesson7;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface Transformer<E> extends UnaryOperator<E>{
}
