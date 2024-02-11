// 322453200, Ilanit Berditchevski

/**
 *  Given integer numbers x and n, calculates x raised to the power n.
 *  Your code should reside in a file named Pow.java. The program will read
 *  a numbers x, n from the commandline, and will then calculate x^n twice,
 *  once using a recursive definition, and once using an iterative procedure,
 *  and print out the result.
 *  */
public class Pow {

    /**
     *  read the input numbers from the args array and convert it from a string
     *  to an integer. It will then call two methods.
     *  @param args an array of parameters of type String
     *  */
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("No arguments supplied");
            System.exit(1);
        }

        int x = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        long resultPowRecursive = powRecursive(n, x);
        long resultPowIter = powIter(n, x);

        System.out.println("recursive: " + resultPowRecursive);
        System.out.println("iterative: " + resultPowIter);
    }
    /**
     * recursively calculates the base raised to the power of the exponent(n).
     * @param  n  the exponent.
     * @param  x the base.
     * @return long */
    public static long powRecursive(long n, long x) {
        // if the exponent is 0
        if (n == 0) {
            return 1;
        }
        // if exponent is negative
        if (n < 0) {
            return 1 / powRecursive(--n, x);
        }

        long result = powRecursive(--n,x) * x;
        return result;
    }

    /**
     *  calculates the base raised to the power of the exponent using a while.
     *  loop
     * @param n  the exponent.
     * @param x the base.
     * @return long */
    public static long powIter(long n, long x) {
        // if the exponent is 0, x^0 = 1.
        if (n == 0) {
            return 1;
        }

        long result = 1;
        // convert to the absolute value of n
        long absN = findAbsolute(n);

        while (absN > 0) {
            if (absN % 2 == 1) {
                result *= x;
            }
            x *= x;
            absN /= 2;
        }

        // if n is negative
        if (n < 0) {
            return 1 / result;
        }

        return result;
    }

    /**
     *  returns the absolute value of a given number.
     * @param n the number to calculate the absolute value of.
     * @return long, absolute value of a given number */
    public static long findAbsolute(long n) {
      // if n is negative, return the positive value, else return n
       return n < 0 ? -n : n;
    }
}




