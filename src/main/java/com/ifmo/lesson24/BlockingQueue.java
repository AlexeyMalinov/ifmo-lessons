package com.ifmo.lesson24;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueue<T> {

    private final List<T> list = new ArrayList<>();
    private final Object mutex = new Object();
    private AtomicInteger index = new AtomicInteger(0);

    public boolean add(T t) {
        if(index.get() < 1){
            synchronized (mutex){
                list.add(t);
                index.incrementAndGet();
                mutex.notify();
            }
            return true;
        }
        index.incrementAndGet();
        return list.add(t);
    }

    public T take() throws InterruptedException {
        if(index.get() < 1){
            synchronized (mutex){
                while(index.get() < 1) {
                    mutex.wait();
                }
                index.decrementAndGet();
                return list.remove(0);
            }
        }
        index.decrementAndGet();
        return list.remove(0);
    }


}
