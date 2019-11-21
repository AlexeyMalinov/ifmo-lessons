package com.ifmo.lesson17;

public class Application {
    public static void main(String[] args) {
        Factory factory = Factory.getFactory("RUS");
        Car car = factory.createCar();

        Pizza pizza = new Pizza.PizzaBuilder("qqqqq","qqqqq").catchup(1).pepperoni(2).build();
    }
}
