package com.ifmo.lesson2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SquareEquationTest {

    @Test
    void squareEquationRoots() {
        var as = new double[]{2.0, 10.0, 4.0, 5.0, 2.0, 3.0, 3.0};
        var bs = new double[]{-9.0, -11.0, 12.0, -11.0, 1.0, -18.0, 0};
        var cs = new double[]{9.0, 3.0, 5.0, 6.0, -10.0, 27, 10.0};
        var results = new double[][]{
                new double[]{1.5, 3.0},
                new double[]{0.5, 0.6},
                new double[]{-2.5, -0.5},
                new double[]{1.0, 1.2},
                new double[]{-2.5, 2.0},
                new double[]{3.0, 3.0},
                null
        };

        for (int i = 0; i < as.length; i++) {
            double a = as[i];
            double b = bs[i];
            double c = cs[i];
            double[] expected = results[i];

            double[] roots = SquareEquation.squareEquationRoots(a, b, c);

            if (roots == null || expected == null)
                assertEquals(expected, roots, "a = " + a + ", b = " + b + ", c = " + c);
            else {
                Set<Double> expectedSet = toSet(expected);
                Set<Double> rootsSet = toSet(roots);

                assertEquals(expectedSet, rootsSet, "a = " + a + ", b = " + b + ", c = " + c);
            }

        }
    }


    private Set<Double> toSet(double[] arr) {
        Set<Double> set = new HashSet<>();

        for (Double d : arr)
            set.add(d);

        return set;
    }
}