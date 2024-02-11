// 322453200, Ilanit Berditchevski

public class PlaceInArray {

    /**
     *   Reads the input number from the args array and convert it from a
     *   string to an integer. It will then call methods.
     *  @param args an array of parameters of type String
     *  */
    public static void main(String[] args) {

        int[] numArr= new int [args.length-1];

        // read array of numbers
        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = Integer.parseInt(args[i]);
        }

        // read the target value
        int targetValue = Integer.parseInt(args[args.length-1]);

        int[] result = placeInArray(targetValue, numArr);

        // print the starting and ending positions of the target in the array.
        System.out.println(targetValue + " start in " + result[0] + " and end in " + result[1]);
    }

    /**
     *get array of integers nums sorted in non-decreasing order, and find the
     * starting and ending position of a given target value.
     * @param target target value
     * @param nums array of numbers
     * @return int [] */
    public static int[] placeInArray(int target, int[] nums) {
        int[] result = {-1, -1};
        int start = -1;
        int end = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (start == -1) {
                    start = i;
                }
                end = i;
            }
        }
        if (start != -1) {
            result[0] = start;
            result[1] = end;
        }
        return result;
    }
}
