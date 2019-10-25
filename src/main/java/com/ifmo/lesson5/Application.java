package com.ifmo.lesson5;

import com.ifmo.lesson4.Library;

import java.util.Objects;

public class Application {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(new Rectangle(1.2, 3.4));
        list.add(new Square(5.6));
        list.add(new Triangle(7.8, 9.1));
        list.add(new Oval(1.2, 3.4));
        list.add(new Circle(5.6));
        int i = 0;
        Shape shape = list.get(i);
        double sum = 0;
        while (shape != null) {
            sum += shape.aria();
            shape = list.get(++i);
        }

        System.out.println(sum);
        System.out.println((1.2 * 3.4)
                + (5.6 * 5.6)
                + (7.8 * 9.1 / 2)
                + (Math.PI * 1.2 * 3.4)
                + (Math.PI * 5.6 * 5.6)
        );

        Rectangle rectangle = new Rectangle(1,1);
        Triangle triangle = new Triangle(2,1);
        Circle circle = new Circle(4);
        Square square = new Square(5);
        Oval oval = new Oval(3,7);
        Item item1 = new Item(rectangle);
        Item item2 = new Item(triangle);
        Item item3 = new Item(circle);
        Item item4 = new Item(square);
        Item item5 = new Item(oval);
        item1.next = item2;
        item2.next = item3;
        item3.next = item4;
        item4.next = item5;
        item5.next = item3;
        System.out.println(list.fixLoop(item1));
        System.out.println(item5.next);

    }
}
