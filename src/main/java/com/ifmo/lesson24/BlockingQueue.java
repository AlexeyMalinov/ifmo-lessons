package com.ifmo.lesson24;

import java.util.*;

public class BlockingQueue<T> {

    private final List<T> list = new ArrayList<>();
    private final Object mutex = new Object();

    public boolean put(T t) {
        synchronized (mutex){
            list.add(t);
            mutex.notify();
            return true;
        }
    }

    public T take() throws InterruptedException {
        synchronized (mutex){
            while(list.isEmpty()){
                mutex.wait();
            }
            return list.remove(0);
        }
    }


}
