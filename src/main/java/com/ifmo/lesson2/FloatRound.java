package com.ifmo.lesson2;

public class FloatRound {
    /*
    В переменной n хранится вещественное число с ненулевой дробной частью.
    Создайте программу, округляющую число n до ближайшего целого и выводящую результат на экран.
     */
    public static void main(String[] args) {
        float n = 12.4F;

        float rounded = round(n);

        System.out.println(rounded);
    }

    public static float round(float n) {

        float a = n % 1;
        if(a > 0.5f){
            n += 1 - a;
        } else {
            n -= a;
        }
        return n;
    }
}
