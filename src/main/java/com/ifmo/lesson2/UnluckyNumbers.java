package com.ifmo.lesson2;

public class UnluckyNumbers {
    /*
    В американской армии считается несчастливым число 13, а в японской — 4. Перед
    международными учениями штаб российской армии решил исключить номера боевой
    техники, содержащие числа 4 или 13 (например, 40123, 13313, 12345 или 13040), чтобы не
    смущать иностранных коллег. Если в распоряжении армии имеется 100 тыс. единиц боевой
    техники и каждая боевая машина имеет номер от 00001 до 99999, то сколько всего номеров
    придётся исключить?
     */
    public static void main(String[] args) {
        System.out.println(unluckyNumbersCount());
    }

    public static int unluckyNumbersCount() {
        int count = 0;
        for (int i = 1; i < 100000; i++) {
            String number = appendZero(String.valueOf(i));
            if(number.contains("4") || (number.contains("13"))){
                count++;
            }
        }
        return count;
    }

    private static String intToString(int number){
        if(number < 10000){
            return appendZero(String.valueOf(number));
        }
        return String.valueOf(number);
    }

    private static String appendZero(String str){
        StringBuilder builder = new StringBuilder();
        for (int i = 5; i > str.length(); i--) {
            builder.append("0");
        }
        return builder.append(str).toString();
    }
}
