package com.ifmo.lesson21;

public class Client extends Thread {

    private Waiter waiter;

    private Dish dish;

    private volatile Order order;

    private volatile boolean dishReceived = false;

    public void setDish(Dish dish) {
        this.dish = dish;
        dishReceived = true;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    @Override
    public void run() {
        if (waiter == null)
            throw new NullPointerException("waiter not found");

        order = new Order();
        final Object waiterMutex = waiter.getWaiterMutex();
        waiter.setOrder(order);
        System.out.println("Client: I made an order");
        synchronized (waiterMutex) {
            waiterMutex.notify();
            try {
                while(!dishReceived) {
                    waiterMutex.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (dishReceived) {
            System.out.println("Client: Om-Nom-nom");
        }
    }
}
