package com.ifmo.lesson17.factores;

import com.ifmo.lesson17.Car;
import com.ifmo.lesson17.Factory;
import com.ifmo.lesson17.model.Bentley;

public class UkFactory extends Factory {

    @Override
    public Car createCar() {
        return new Bentley();
    }
}
