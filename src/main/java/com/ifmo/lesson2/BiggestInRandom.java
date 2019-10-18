package com.ifmo.lesson2;

import java.util.Random;

public class BiggestInRandom {

    private static Random random = new Random();

    /*
     Создать программу, выводящую на экран случайно сгенерированное трёхзначное
     натуральное число и его наибольшую цифру.Примеры работы программы:
     В числе 208 наибольшая цифра 8.
     В числе 774 наибольшая цифра 7.
     В числе 613 наибольшая цифра 6.
     */
    public static void main(String[] args) {
        int rnd = threeDigitRandom();

        String largestDigit = largestDigit(rnd);

        System.out.println(largestDigit);
    }

    public static int threeDigitRandom() {
        return (random.nextInt(900) + 100);
    }

    public static String largestDigit(int rnd) {
        int x = rnd / 100;
        int y = (rnd - x * 100) / 10;
        int z = rnd % 10;
        int max = Math.max(Math.max(x, y), z);

        return "В числе " + rnd + " наибольшая цифра " + max + ".";
    }
}
