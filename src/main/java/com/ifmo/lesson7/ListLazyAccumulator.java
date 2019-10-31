package com.ifmo.lesson7;

import com.ifmo.lesson6.List;
import com.ifmo.lesson6.accumulator.Operation;

public class ListLazyAccumulator implements LazyAccumulator {

    List expressionList = new LinkedList();

    @Override
    public int calculate() {
        int result = 0;
        for(Object expression: expressionList){
            result = (int) ((Expression) expression).getOperation().calculate(result, ((Expression) expression).getValue());
        }
        return result;
    }

    @Override
    public void add(int a, Operation operation) {
        expressionList.add(new Expression(a, operation));
    }
}
