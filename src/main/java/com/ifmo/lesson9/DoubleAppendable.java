package com.ifmo.lesson9;

public class DoubleAppendable extends AbstractNumberAppendable<Double, DoubleAppendable> {

    private static final ArithmeticOperation<Double> DEFAULT_OPERATION = Double::sum;

    public DoubleAppendable(Double value, ArithmeticOperation op) {
        super(value, op);
    }

    public DoubleAppendable(){
        super(0D, DEFAULT_OPERATION);
    }

}
