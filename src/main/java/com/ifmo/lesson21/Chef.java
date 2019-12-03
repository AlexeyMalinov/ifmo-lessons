package com.ifmo.lesson21;

public class Chef extends Thread {

    private Waiter waiter;

    private final Object chefMutex = new Object();

    private volatile boolean orderReceived = false;

    private volatile Order order;

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public void setOrder(Order order) {
        this.order = order;
        orderReceived = true;
    }

    public Object getChefMutex() {
        return chefMutex;
    }

    @Override
    public void run() {
        if (waiter == null)
            throw new NullPointerException("waiter not found");
        while (!isInterrupted()) {
            synchronized (chefMutex) {
                try {
                    System.out.println("Chef: I'am wait");
                    chefMutex.wait();
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
            if (orderReceived) {
                System.out.println("Chef: I received the order");
                waiter.setDish(cook());
                System.out.println("Chef: I prepared the dish");
                synchronized (chefMutex) {
                    chefMutex.notify();
                }
            }
        }
    }

    private Dish cook() {
        orderReceived = false;
        order = null;
        return new Dish();
    }
}
