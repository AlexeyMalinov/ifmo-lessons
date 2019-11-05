package com.ifmo.lesson9;

public class DoubleAppendable extends AbstractNumberAppendable<Double, DoubleAppendable> {

    Double value;

    public DoubleAppendable(ArithmeticOperation op) {
        super(op);
    }


    @Override
    public DoubleAppendable append(Double type) {
        value = this.op.apply(value,type);
        return this;
    }

    @Override
    public Double value() {
        return value;
    }
}
