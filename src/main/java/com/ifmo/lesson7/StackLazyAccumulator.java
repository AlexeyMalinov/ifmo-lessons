package com.ifmo.lesson7;

import com.ifmo.lesson6.Stack;
import com.ifmo.lesson6.accumulator.Operation;

public class StackLazyAccumulator implements LazyAccumulator {

    Stack expressionStack = new LinkedList();

    @Override
    public int calculate() {
        int result = 0;
        Expression expression;
        while ((expression = (Expression) expressionStack.pop()) != null) {
            result = (int) expression.getOperation().calculate(result, expression.getValue());
        }
        return result;
    }

    @Override
    public void add(int a, Operation operation) {
        expressionStack.push(new Expression(a, operation));
    }
}
