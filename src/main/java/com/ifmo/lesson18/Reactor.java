package com.ifmo.lesson18;


import java.util.ArrayList;
import java.util.List;

public class Reactor {

    private int temperature;
    private List<Listener> listeners = new ArrayList<>();


    public int getTemperature() {
            return temperature;
    }

    public void upperTemperature() {
            ++this.temperature;
            notifyListener();
    }

    public void lowerTemperature(){
            --this.temperature;
            notifyListener();
    }

    private void notifyListener(){
        listeners.forEach(n -> n.operate(this));
    }

    public void setListener(Listener listener) {
        listeners.add(listener);
    }
}
