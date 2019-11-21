package com.ifmo.lesson17;

import com.ifmo.lesson17.factores.*;

public abstract class Factory {

    public static Factory getFactory(String country) {
        switch (country) {
            case "JP":
                return new JapanFactory();
            case "RUS":
                return new RussianFactory();
            case "UK":
                return new UkFactory();
            case "DE":
                return new GermanyFactory();
            case "US":
                return new UsFactory();
        }
        throw new IllegalArgumentException("Factory not found");
    }

    public abstract Car createCar();
}
