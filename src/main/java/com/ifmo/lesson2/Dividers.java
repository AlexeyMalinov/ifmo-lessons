package com.ifmo.lesson2;

import java.util.Scanner;

public class Dividers {
    /*
    Выведите на экран все положительные делители натурального числа, введённого
    пользователем с клавиатуры.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        printNumbersDividers(n);

    }

    private static void printNumbersDividers(int number){
        for (int i = 1; i <= number; i++) {
            if(number % i == 0){
                System.out.println(i);
            }
        }
    }
}
