package com.ifmo.lesson9;

public class IntAppendable extends AbstractNumberAppendable <Integer, IntAppendable> {
    int value;

    private static ArithmeticOperation<Integer> DEFAULT_OPERATION = Integer::sum;

    public IntAppendable(ArithmeticOperation<Integer> op) {
        super(op);
        this.value = value;
    }

    public IntAppendable(){
        super(DEFAULT_OPERATION);
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

    public static void main(String[] args) {
        Appendable<Integer, IntAppendable> appendable = new IntAppendable();
        appendable.append(1).value();
    }


}
