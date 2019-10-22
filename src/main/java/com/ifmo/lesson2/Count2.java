package com.ifmo.lesson2;

public class Count2 {
    /*
     В городе N есть большой склад на котором существует 50000 различных полок. Для
    удобства работников руководство склада решило заказать для каждой полки табличку с
    номером от 00001 до 50000 в местной типографии, но когда таблички напечатали, оказалось
    что печатный станок из-за неисправности не печатал цифру 2, поэтому все таблички, в
    номерах которых содержалась одна или более двойка (например, 00002 или 20202) — надо
    перепечатывать. Напишите программу, которая подсчитает сколько всего таких ошибочных
    табличек оказалось в бракованной партии.
     */
    public static void main(String[] args) {
        System.out.println(count2());
    }

    public static int count2() {
        int count = 0;
        for (int i = 0; i < 50000; i += 1) {
            if (contains2(i)) {
                ++count;
            }
        }
        return count;
    }

    private static boolean contains2(int i){
        while(i != 0) {
            if ((i % 10) == 2) return true;
            i /= 10;
        }
        return false;
    }
}
