package com.ifmo.lesson7;

import com.ifmo.lesson6.accumulator.Multiply;
import com.ifmo.lesson6.accumulator.Operation;
import com.ifmo.lesson6.accumulator.Plus;

public class Accumulator {
    private double value;
    private Operation operation;
    private Accumulator accumulator;

    private Accumulator(double value, Operation operation) {
        this.value = value;
        this.operation = operation;
    }

    double getValue() {
        return value;
    }

    public void accumulate(double v) {
        System.out.println(operation.calculate(value, v));
    }

    public static void main(String[] args) {
        Accumulator accumulator1 = new Accumulator(100, new Operation() {
            @Override
            public double calculate(double a, double b) {
                return a / b;
            }
        });
        accumulator1.accumulate(100);

        Accumulator accumulator2 = new Accumulator(50, new Operation() {
            @Override
            public double calculate(double a, double b) {
                return a + b;
            }
        });
        accumulator2.accumulate(50);
    }

    /**
     * Фабричный метод для создания Аккумулятора
     * @return
     */
    public static Accumulator getInstance(double value, Operation operation){
        return new Accumulator(value, operation);
    }


}
