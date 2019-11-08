package com.ifmo.lesson9;

public abstract class AbstractNumberAppendable<T extends Number, A extends AbstractNumberAppendable> implements Appendable<T,A> {
    ArithmeticOperation<T> op;
    AbstractNumberAppendable(ArithmeticOperation op){
        this.op = op;
    }
}