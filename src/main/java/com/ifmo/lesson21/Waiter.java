package com.ifmo.lesson21;

public class Waiter extends Thread {

    private Client client;
    private Chef chef;

    private Order order;
    private Dish dish;

    private boolean orderReceived = false;
    private boolean dishReceived = false;

    private Object waiterMutex = new Object();

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
                    if (orderReceived) {
                        System.out.println("Waiter: I received the order");
                        chef.setOrder(order);
                        Object chefMutex = chef.getChefMutex();
                        synchronized (chefMutex) {
                            chefMutex.notify();
                            chefMutex.wait();
                            if (dishReceived) {
                                client.setDish(dish);
                                System.out.println("Waiter: I got the dish");
                            }
                        }
                    }
                    System.out.println("Waiter: I took the dish to the client");
                    waiterMutex.notify();
                } catch (InterruptedException e) {
                    interrupt();
                }
            }

        }
    }
}
