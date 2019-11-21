package com.ifmo.lesson17.model;

import com.ifmo.lesson17.Car;

public class Bmw implements Car {
    @Override
    public int power() {
        return 300;
    }

    @Override
    public int maxSpeed() {
        return 250;
    }
}
