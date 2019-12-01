package com.ifmo.lesson21;

public class Cafe {
    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        Waiter waiter = new Waiter();
        Chef chef = new Chef();

        client.setWaiter(waiter);
        waiter.setClient(client);
        waiter.setChef(chef);
        chef.setWaiter(waiter);

        waiter.start();
        chef.start();
        client.start();

        client.join();
        waiter.interrupt();
        chef.interrupt();
    }
}
