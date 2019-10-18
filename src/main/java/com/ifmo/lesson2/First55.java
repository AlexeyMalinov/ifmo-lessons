package com.ifmo.lesson2;

public class First55 {
    /*
    Создайте программу, выводящую на экран первые 55 элементов последовательности 1 3 5 7 9 11 13 15 17 ….
     */
    public static void main(String[] args) {
        printSequence(55);
    }

    private static void printSequence(int n) {
        for (int i = 0, result = 1; i < n; i++) {
            System.out.println(result);
            result += 2;
        }
    }
}
