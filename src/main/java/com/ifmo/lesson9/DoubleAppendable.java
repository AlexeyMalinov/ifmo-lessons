package com.ifmo.lesson9;

public class DoubleAppendable extends AbstractNumberAppendable<Double, DoubleAppendable> {

    Double value;

    private static final ArithmeticOperation<Double> DEFAULT_OPERATION = Double::sum;

    public DoubleAppendable(Double value, ArithmeticOperation op) {
        super(op);
        this.value = value;
    }

    public DoubleAppendable(){
        super( DEFAULT_OPERATION);
    }


    @Override
    public DoubleAppendable append(Double type) {
        value = this.op.apply(value, type);
        return this;
    }

    @Override
    public Double value() {
        return value;
    }
}
