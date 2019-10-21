package com.ifmo.lesson3;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.Random;

public class TwoArrays {
    /*
     Создайте 2 массива из 5 случайных целых чисел из отрезка [0;5] каждый, выведите
     массивы на экран в двух отдельных строках. Посчитайте среднее арифметическое элементов
     каждого массива и сообщите, для какого из массивов это значение оказалось больше (либо
     сообщите, что их средние арифметические равны).
     */
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] randomNumbers1 = randomNumbers();
        printArrayToLine(randomNumbers1);

        int[] randomNumbers2 = randomNumbers();
        printArrayToLine(randomNumbers2);


        int average1 = average(randomNumbers1);
        int average2 = average(randomNumbers2);
        if(average1 == average2){
            System.out.println( "Средние арифметическое значение двух массивов равны");
        } else if (average1 > average2){
            System.out.println("Средние арифметическое значение первого массива больше второго");
        } else {
            System.out.println("Средние арифметическое значение второго массива больше первого");
        }
    }

    public static int[] randomNumbers() {
        int[] array = new int[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(6);
        }

        return array;
    }

    public static int average(int[] randomNumbers) {
        OptionalDouble averageDouble = Arrays.stream(randomNumbers).average();
        return (int) Math.round(averageDouble.getAsDouble());
    }

    private static void printArrayToLine(int[] array) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
            if(i < array.length - 1){
                builder.append(" ");
            }
        }
        System.out.println(builder);
    }
}
