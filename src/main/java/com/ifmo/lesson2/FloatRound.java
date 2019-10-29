package com.ifmo.lesson2;

public class FloatRound {
    /*
    В переменной n хранится вещественное число с ненулевой дробной частью.
    Создайте программу, округляющую число n до ближайшего целого и выводящую результат на экран.
     */
    public static void main(String[] args) {
        float n = -1.55F;

        float rounded = n >= 0?roundPositiveNumber(n): roundNegativeNumber(n);

        System.out.println(rounded);
    }

    private static float roundPositiveNumber(float n) {

        float a = n % 1;
        if (a > 0.5f) {
            n += 1 - a;
        } else {
            n -= a;
        }
        return n;
    }

    private static float roundNegativeNumber(float n) {
        float a = n % 1;
        if (a < -0.5f) {
            n -= 1 + a;
        } else {
            n -= a;
        }
        return n;
    }
}
