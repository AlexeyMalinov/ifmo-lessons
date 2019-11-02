package com.ifmo.lesson9;

public class IntAppendable extends AbstractNumberAppendable <Integer, IntAppendable> {
    int value = 0;

    public IntAppendable(ArithmeticOperation op) {
        super(op);
    }

    @Override
    public IntAppendable append(Integer type) {
        value = this.op.apply(value, type);
        return this;
    }

    @Override
    public Integer value() {
        return value;
    }
}
