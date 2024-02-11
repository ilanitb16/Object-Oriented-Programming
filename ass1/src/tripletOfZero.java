// 322453200, Ilanit Berditchevski

/**
 *  a program called tripletOfZero that gets a list of numbers in the
 *  commandline, and prints the triplet [nums[i], nums[j], nums[k]] such
 *  that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *  */
public class tripletOfZero {
    /**
     *   Reads the input number from the args array and converts it
     *   from a string to an integer. It will then call methods
     *  @param args an array of parameters of type String
     *  */
    public static void main(String[] args) {

        // array containing the numbers
        int[] numbers = stringsToArray(args);

        // array containing the triplets
        int[] triplet = tripletOfZero(numbers);
        if (triplet[0] == -1 && triplet[1] == -1 && triplet[2] == -1) {
            System.out.println("The triplet is: -1");
        } else {
            if (isAsc(args)) {
                //print in ascending order
                ascTripletPrint(triplet);
            } else {
                //print in descending order
                descTripletPrint(triplet);
            }
        }
    }

    /**
     * Converts strings into integer array.
     * @param numbers @NotNull
     * @return  positions of a these numbers */
    public static int[] stringsToArray(String[] numbers) {
        int[] arr = new int[numbers.length];

        // scans string and places int numbers
        for (int i = 1 ; i < numbers.length; i++) {
            arr[i] = Integer.parseInt(numbers[i]);
        }
        return arr;
    }

    /**
     * Gets a list of numbers in the commandline, and prints the triplet
     * [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k,
     * and nums[i] + nums[j] + nums[k] == 0.
     * @param numbers array of numbers
     * @return  positions of a these numbers */

    public static int[] tripletOfZero(int[] numbers) {
        // create triplet array
        int[] result = {-1, -1, -1};
        for (int i = 0; i < numbers.length - 2; i++) {
            for (int j = i + 1; j < numbers.length - 1; j++) {
                for (int k = j + 1; k < numbers.length; k++) {
                    // check if numbers sum up to zero
                    if (numbers[i] + numbers[j] + numbers[k] == 0) {
                        result[0] = numbers[i];
                        result[1] = numbers[j];
                        result[2] = numbers[k];
                        return result;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Print the triplet in ascending order using bubble sort.
     * @param numbers @NotNull */
    public static void ascTripletPrint(int[] numbers) {
        int length = numbers.length;

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (numbers[j] < numbers[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }

        System.out.print("the triplet is:[");
        for (int i = 0; i < length; i++) {
            if( i != length-1){
                System.out.print(numbers[i] + " ");
            }
            else{
                System.out.print(numbers[i]);
            }
        }
        System.out.print("]");
    }

    /**
     * Print the triplet in descending order using bubble sort.
     * @param numbers @NotNull
     **/
    public static void descTripletPrint(int[] numbers) {
        int length = numbers.length;

        // swap using bubble sort algorithm
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    // Swap between arr[j] and arr[j+1]
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }

        System.out.print("the triplet is:[");
        for (int i = 0; i < length; i++) {
            if( i != length-1){
                System.out.print(numbers[i] + " ");
            }
            else{
                System.out.print(numbers[i]);
            }
        }
        System.out.print("]");

    }

    /**
     * Checks if the order parameter is "asc" or not.
     * @param order
     * @return boolean */
    public static boolean isAsc(String[] order) {

        if (order.length > 0 && order[0].equals("asc")) {
            return true;
        }
        return false;
    }
}
