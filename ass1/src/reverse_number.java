// 322453200, Ilanit Berditchevski


/**
 *  Given a 32-bit signed integer, reverse digits of an integer.
 *  */
public class reverse_number {

    /**
     *  Reads the input number from the args array and convert it from a
     *  string to an integer. It will then call methods.
     *  @param args an array of parameters of type String
     *  */
    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("error");
            System.exit(1);
        }

        int input = Integer.parseInt(args[0]);
        int reversed = reverseNum(input);
        System.out.println("Input: " + input);
        System.out.println("Reversed: " + reversed);

    }

    /**
     * Reverse digits of an integer.
     * @param n @NotNull
     * @return  reversed number */
    public static int reverseNum(int n) {

        long max = Integer.MAX_VALUE;
        long min = Integer.MIN_VALUE;

        int reversed = 0;
        while (n != 0) {
            int digit = n % 10;
            if (reversed > max / 10 || (reversed == max / 10 && digit > 7)) {
                return 0;
            }
            if (reversed < min / 10 || (reversed == min / 10 && digit < -8)) {
                return 0;
            }
            reversed = reversed * 10 + digit;
            n /= 10;
        }
        return reversed;
    }
}
