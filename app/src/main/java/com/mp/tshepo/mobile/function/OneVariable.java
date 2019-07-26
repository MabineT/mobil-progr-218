package com.mp.tshepo.mobile.function;

import java.util.ArrayList;

/**
 * Created by tshep on 13/05/2018.
 */

public class OneVariable {

    public static double Factorial(long number) {
        if (number < 0) {
            System.out.println("INV");
            return 0;
        }
        if (number == 0) {
            return 1;
        } else if (number == 1) {
            return 1;
        } else {
            return number * Factorial(number - 1);
        }
    }

    public static ArrayList<Long> subOf = null;

    public static ArrayList<Long> unitsModuloN(int n) {
        ArrayList<Long> units = new ArrayList<>();
        subOf = new ArrayList<>();
        for (long i = 0; i < n; i++) {
            subOf.add(i);
            if (TwoVariable.greatestCommonDivisor(i, n) == 1) {
                units.add(i);
            }
        }
        return units;
    }

    public static boolean isAbelian(ArrayList<Long> units, int n) {
        for (int a = 0; a < units.size(); a++) {
            if ((units.get(a) * units.get(a)) % n != 1) {
                return false;
            }
        }
        return true;
    }

    private int a, b, c;
    public static String seq = "{0, 1, ";

    public static int Fibonacci(int n, int a, int b) {
        int c = a + b;
        seq += c + ", ";
        if (n <= 1) {
            return 0;
        } else {
            a = b;
            b = c;
            return Fibonacci(n - 1, a, b);
        }
    }

    public static int min(int[] arr) {
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            c = arr[i];
            for (int j = 0; j < arr.length; j++) {
                if (c > arr[j]) {
                    c = arr[j];
                }
            }
        }
        return c;
    }

    public static int max(int[] arr) {
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            c = arr[i];
            for (int j = 0; j < arr.length; j++) {
                if (c < arr[j]) {
                    c = arr[j];
                }
            }
        }
        return c;
    }

}
