package com.ifmo.lesson2;

import com.ifmo.fun.AssertRandomInterval;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WallClockTest {

    @Test
    void randomSecond() {
        new AssertRandomInterval(0, 28800, WallClock::randomSecond)
                .assertInterval();
    }

    @Test
    void remainingHours() {
        var vals = new int[]{28800, 28700, 25199, 21599, 17999, 14399, 10799, 7199, 3599};
        var expected = new String[]{
                "Осталось 8 часов",
                "Осталось 7 часов",
                "Осталось 6 часов",
                "Осталось 5 часов",
                "Осталось 4 часа",
                "Осталось 3 часа",
                "Осталось 2 часа",
                "Остался 1 час",
                "Осталось менее часа"
        };

        for (int i = 0; i < vals.length; i++) {
            String remainingHours = WallClock.remainingHours(vals[i]);

            assertEquals(expected[i], remainingHours, "seconds = " + vals[i]);
        }
    }
}