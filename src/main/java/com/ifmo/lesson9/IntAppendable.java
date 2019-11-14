package com.ifmo.lesson9;

public class IntAppendable extends AbstractNumberAppendable <Integer, IntAppendable> {

    private static ArithmeticOperation<Integer> DEFAULT_OPERATION = Integer::sum;

    public IntAppendable(Integer value, ArithmeticOperation<Integer> op) {
        super(value, op);
    }

    public IntAppendable(){
        super(0, DEFAULT_OPERATION);
    }

}
