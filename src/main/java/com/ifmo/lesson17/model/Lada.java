package com.ifmo.lesson17.model;

import com.ifmo.lesson17.Car;

public class Lada implements Car {
    @Override
    public int power() {
        return 110;
    }

    @Override
    public int maxSpeed() {
        return 180;
    }
}
