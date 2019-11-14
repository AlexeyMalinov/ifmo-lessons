package com.ifmo.lesson9;

public abstract class AbstractNumberAppendable<T extends Number, A extends AbstractNumberAppendable> implements Appendable<T,A> {

    T value;
    ArithmeticOperation<T> op;
    AbstractNumberAppendable(T value, ArithmeticOperation<T> op){
        this.value = value;
        this.op = op;
    }

    @Override
    public A append(T type) {
        value = this.op.apply(value, type);
        return (A) this;
    }

    @Override
    public T value() {
        return value;
    }

}
