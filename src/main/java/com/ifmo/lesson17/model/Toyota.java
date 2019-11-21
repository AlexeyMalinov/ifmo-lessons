package com.ifmo.lesson17.model;

import com.ifmo.lesson17.Car;

public class Toyota implements Car {
    @Override
    public int power() {
        return 180;
    }

    @Override
    public int maxSpeed() {
        return 200;
    }
}
