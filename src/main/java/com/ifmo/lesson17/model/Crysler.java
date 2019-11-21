package com.ifmo.lesson17.model;

import com.ifmo.lesson17.Car;

public class Crysler implements Car {
    @Override
    public int power() {
        return 350;
    }

    @Override
    public int maxSpeed() {
        return 200;
    }
}
