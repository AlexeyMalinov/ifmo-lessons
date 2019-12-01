package com.ifmo.lesson21;

public class Client extends Thread {

    private Waiter waiter;

    private Dish dish;
    private boolean dishReceived = false;

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

        Order order = new Order();
        Object waiterMutex = waiter.getWaiterMutex();
        synchronized (waiterMutex) {
            waiter.setOrder(order);
            waiterMutex.notify();
            System.out.println("Client: I made an order");
            try {
                waiterMutex.wait();
                if (dishReceived) {
                    System.out.println("Client: Om-Nom-nom");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
