package com.ifmo.lesson24;

import java.util.*;

public class BlockingQueue<T> {

    private final List<T> list = new ArrayList<>();
    private final Object mutex = new Object();


    public boolean add(T t) {
        if(list.size() == 0){
            synchronized (mutex){
                list.add(t);
                mutex.notify();
            }
            return true;
        }
        return list.add(t);
    }

    public T take() throws InterruptedException {
        if(list.size() <= 1){
            synchronized (mutex){
                while(list.size() == 0) {
                    mutex.wait();
                }
                return list.remove(0);
            }
        }
        return list.remove(0);
    }


}
