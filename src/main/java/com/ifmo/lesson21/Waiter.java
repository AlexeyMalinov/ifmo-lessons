package com.ifmo.lesson21;

public class Waiter extends Thread {

    private Client client;
    private Chef chef;

    private volatile Order order;
    private volatile Dish dish;

    private volatile boolean orderReceived = false;
    private volatile boolean dishReceived = false;

    private final Object waiterMutex = new Object();

    public Order getOrder() {
        return order;
    }

    public Dish getDish() {
        return dish;
    }

    public Object getWaiterMutex() {
        return waiterMutex;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public void setOrder(Order order) {
        this.order = order;
        orderReceived = true;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
        dishReceived = true;
    }

    @Override
    public void run() {
        if (client == null)
            throw new NullPointerException("client not found");

        if (chef == null)
            throw new NullPointerException("chef not found");

        while (!isInterrupted()) {
            synchronized (waiterMutex) {
                try {
                    System.out.println("Waiter: I'am wait");
                    waiterMutex.wait();
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
            if (orderReceived) {
                System.out.println("Waiter: I received the order");
                transferOrder();
                final Object chefMutex = chef.getChefMutex();
                synchronized (chefMutex) {
                    chefMutex.notify();
                    try {
                        chefMutex.wait();
                    } catch (InterruptedException e) {
                        interrupt();
                    }
                }
                if (dishReceived) {
                    System.out.println("Waiter: I got the dish");
                    transferDish();
                    System.out.println("Waiter: I took the dish to the client");
                    synchronized (waiterMutex) {
                        waiterMutex.notify();
                    }
                }
            }
        }
    }

    private void transferOrder(){
        chef.setOrder(order);
        order = null;
        orderReceived = false;
    }

    private void transferDish(){
        client.setDish(dish);
        dish = null;
        dishReceived = false;
    }
}
