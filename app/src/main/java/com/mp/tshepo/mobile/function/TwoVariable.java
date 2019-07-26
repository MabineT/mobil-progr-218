package com.mp.tshepo.mobile.function;

/**
 * Created by tshep on 13/05/2018.
 */

public class TwoVariable {

    public static long greatestCommonDivisor(long a, long b) {
        if (b == 0) {
            return a;
        }
        if (a == 0) {
            return b;
        } else {
            return greatestCommonDivisor(b, a % b);
        }
    }

    public static long leastCommonMultiple(long a, long b) {
        for (int n = 1; ; n++) {
            if (n % a == 0 && n % b == 0) {
                return n;
            }
        }
    }

    public static double permutation(long x, long y) {
        return (OneVariable.Factorial(x) / OneVariable.Factorial(x - y));
    }

    public static double combination(long x, long y) {
        return (OneVariable.Factorial(x) / (OneVariable.Factorial(x - y) * OneVariable.Factorial(y)));
    }

    public static boolean relativelyPrime(long x, long y) {
        return greatestCommonDivisor(x, y) == 1;
    }

    public static long modulo(Long x, Long y) {
        return x % y;
    }
}
