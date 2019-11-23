package com.ifmo.lesson18;

public class Application {

    public static void main(String[] args) {
        Reactor reactor = new Reactor();

        reactor.setListener(new TemperatureListener(70, 110, "Green"));
        reactor.setListener(new TemperatureListener(110, 130, "YELLOW"));
        reactor.setListener(new AlarmTemperatureListener(130, "RED"));

        upperTemperature(reactor);

        lowerTemperature(reactor);
    }

    private static void upperTemperature(Reactor reactor) {
        for (int i = 0; i < 170; i++) {
            reactor.upperTemperature();
        }
    }

    private static void lowerTemperature(Reactor reactor) {
        for (int i = 0; i < 170; i++) {
            reactor.lowerTemperature();
        }
    }
}
