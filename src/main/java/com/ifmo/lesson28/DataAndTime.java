package com.ifmo.lesson28;

import com.sun.jdi.request.DuplicateRequestException;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.time.Period.between;

public class DataAndTime {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(countFriday13(2019));
        countTimeToNewYear();
        System.out.println(howOldInSeconds(LocalDateTime.of(1988,Month.MAY,5,23,0,0)));
    }

    private static int countFriday13(int year) {
        //Можно сделать через Stream
        int count = 0;
        LocalDate data = LocalDate.of(year, 1, 13);
        while (data.isBefore(LocalDate.of(year + 1, 1, 1))) {
            if (data.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                ++count;
            }
            data = data.plusMonths(1);
        }
        return count;
    }

    private static void countTimeToNewYear() throws InterruptedException {
        LocalDateTime ny = LocalDateTime.of(2020, Month.JANUARY, 1, 0, 0, 0);

        for (int i = 0; i < 20; i++) {

            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(now, ny);
            long days = duration.toDays();

            Duration hoursDuration = duration.minusDays(days);
            long hours = hoursDuration.toHours();

            Duration minutesDuration = hoursDuration.minusHours(hours);
            long minutes = minutesDuration.toMinutes();

            Duration secondsDuration = minutesDuration.minusMinutes(minutes);
            long seconds = secondsDuration.getSeconds();

            System.out.printf("Days: %s, %s:%s:%s\n", days, hours, minutes, seconds);

            Thread.sleep(1000);
        }
    }

    private static long howOldInSeconds(LocalDateTime day) {
        return Duration.between(day, LocalDateTime.now()).getSeconds();
    }
}
