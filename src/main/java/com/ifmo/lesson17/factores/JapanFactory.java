package com.ifmo.lesson17.factores;

import com.ifmo.lesson17.Car;
import com.ifmo.lesson17.Factory;
import com.ifmo.lesson17.model.Toyota;

public class JapanFactory extends Factory {

    @Override
    public Car createCar() {
        return new Toyota();
    }
}
