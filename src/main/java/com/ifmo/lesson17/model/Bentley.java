package com.ifmo.lesson17.model;

import com.ifmo.lesson17.Car;

public class Bentley implements Car {
    @Override
    public int power() {
        return 220;
    }

    @Override
    public int maxSpeed() {
        return 220;
    }
}
