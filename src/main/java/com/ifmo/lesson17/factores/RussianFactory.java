package com.ifmo.lesson17.factores;

import com.ifmo.lesson17.Car;
import com.ifmo.lesson17.Factory;
import com.ifmo.lesson17.model.Lada;

public class RussianFactory extends Factory {

    @Override
    public Car createCar() {
        return new Lada();
    }
}
