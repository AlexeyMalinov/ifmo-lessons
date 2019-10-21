package com.ifmo.lesson3;

import java.util.Random;

public class Random4 {
    /*
    Создайте массив из 4 случайных целых чисел из отрезка [10;99], выведите его на экран в
    строку. Определить и вывести на экран сообщение о том, является ли массив строго
    возрастающей последовательностью.
     */
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] randomNumbers = randomNumbers();
        System.out.println(isIncreasingSequence(randomNumbers));
    }

    public static int[] randomNumbers() {
        int[] array = new int[4];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(90) + 10;
        }
        return array;
    }


    public static boolean isIncreasingSequence(int[] randomNumbers) {
        for (int i = 1; i < randomNumbers.length; i++) {
            if (randomNumbers[i] < randomNumbers[i - 1]) return false;
        }
        return true;
    }
}
