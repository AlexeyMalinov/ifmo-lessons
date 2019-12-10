package com.ifmo.lesson24;

import java.util.ArrayList;
import java.util.List;

public class Application {

    static BlockingQueue<Integer> queue = new BlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {

        List<Thread> getters = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
           getters.add(new Thread(() -> {
               while (true) {
                   try {
                       System.out.println(queue.take());
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }));
        }

        Thread setter = new Thread(Application::set);
        setter.start();
        for (Thread getter : getters) {
            getter.start();
        }
        setter.join();
    }

    private static void set() {
        for (int i = 0; i < 10; i++) {
            queue.add(i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
