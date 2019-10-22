package com.ifmo.lesson2;

import java.util.Random;

public class WallClock {
    /*
    На некотором предприятии инженер Петров создал устройство, на табло которого
    показывается количество секунд, оставшихся до конца рабочего дня. Когда рабочий день
    начинается ровно в 9 часов утра — табло отображает «28800» (т.е. остаётся 8 часов), когда
    времени 14:30 — на табло «9000» (т.е. остаётся два с половиной часа), а когда наступает 17
    часов — на табло отображается «0» (т.е. рабочий день закончился).Программист Иванов
    заметил, как страдают офисные сотрудницы — им неудобно оценивать остаток рабочего дня
    в секундах. Иванов вызвался помочь сотрудницам и написать программу, которая вместо
    секунд будет выводить на табло понятные фразы с информацией о том, сколько полных часов
    осталось до конца рабочего дня. Например: «осталось 7 часов», «осталось 4 часа», «остался
    1 час», «осталось менее часа».Итак, в переменную n должно записываться случайное (на
    время тестирования программы) целое число из [0;28800], далее оно должно выводиться на
    экран (для Петрова) и на следующей строке (для сотрудниц) должна выводиться фраза о
    количестве полных часов, содержащихся в n секундах.

    Примеры работы программы:
    23466
    Осталось 6 часов

    10644
    Осталось 2 часа

    5891
    Остался 1 час

    1249
    Осталось менее часа
     */
    private static Random random  = new Random();
    public static void main(String[] args) {
        int randomSecond = randomSecond();

        String remainingHours = remainingHours(randomSecond);

        System.out.println(remainingHours);
    }

    public static int randomSecond() {
        return random.nextInt( 28801);
    }

    public static String remainingHours(int rndSecond) {
        String[] endingOfWord = {"а","ов"};
        int lastTime = rndSecond / 3600;
        String outString = "";
        if(lastTime < 1) {
            outString = "Осталось менее часа";
        } else if (lastTime == 1){
            outString = "Остался 1 час";
        } else if (lastTime >= 5){
            outString = "Осталось "+lastTime + " час"+endingOfWord[1];
        } else {
            outString = "Осталось "+lastTime + " час"+endingOfWord[0];
        }
//        return rndSecond+System.lineSeparator() + outString;
        return outString;
    }
}
