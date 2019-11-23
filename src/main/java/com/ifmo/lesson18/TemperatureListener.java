package com.ifmo.lesson18;

class TemperatureListener implements Listener {

    final int minValue;
    final int maxValue;
    final String color;

    public TemperatureListener(int minValue, int maxValue, String color) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.color = color;
    }

    @Override
    public void operate(Reactor reactor) {
        int temperature = reactor.getTemperature();
        if (temperature > minValue && temperature < maxValue) {
            System.out.println("WARNING: Temperature in " + color + " area");
        }
    }
}
