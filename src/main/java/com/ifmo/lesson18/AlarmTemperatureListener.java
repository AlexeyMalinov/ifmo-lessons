package com.ifmo.lesson18;

public class AlarmTemperatureListener extends TemperatureListener {

    public AlarmTemperatureListener(int minValue, String color) {
        super(minValue, 0, color);
    }

    @Override
    public void operate(Reactor reactor) {
        if(reactor.getTemperature() > minValue){
            System.out.println("ALARM: Temperature in "+ color + " area");
        };
    }
}
