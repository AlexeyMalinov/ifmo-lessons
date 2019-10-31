package com.ifmo.lesson7;

import com.ifmo.lesson6.accumulator.Operation;

public interface LazyAccumulator {

    class Expression {

        private int value;
        private Operation operation;

        Expression(int value, Operation operation) {
            this.value = value;
            this.operation = operation;
        }

        public int getValue() {
            return value;
        }

        public Operation getOperation() {
            return operation;
        }
    }

    int calculate();
    void add(int a, Operation operation);
}
