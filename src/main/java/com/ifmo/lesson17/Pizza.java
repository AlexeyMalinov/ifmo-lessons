package com.ifmo.lesson17;

public class Pizza {
    final String dough;
    final String cheese;
    final int catchup;
    final int tomatoes;
    final int pepperoni;

    public static class PizzaBuilder {
        final String dough;
        final String cheese;
        int catchup;
        int tomatoes;
        int pepperoni;

        public PizzaBuilder(String dough, String cheese) {
            this.dough = dough;
            this.cheese = cheese;
        }

        public PizzaBuilder catchup(int catchup) {
            this.catchup = catchup;
            return this;
        }

        public PizzaBuilder tomatoes(int tomatoes) {
            this.tomatoes = tomatoes;
            return this;
        }

        public PizzaBuilder pepperoni(int pepperoni) {
            this.pepperoni = pepperoni;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }

    }

    private Pizza(PizzaBuilder builder) {
        this.dough = builder.dough;
        this.cheese = builder.cheese;
        this.catchup = builder.catchup;
        this.pepperoni = builder.pepperoni;
        this.tomatoes = builder.tomatoes;
    }
}
