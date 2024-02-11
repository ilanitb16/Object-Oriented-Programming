package arkanoid;

/** The Counter class is a class that is used for counting things.
 */
public class Counter {
    private int count;

    /** Add number to current count.
     @param number the number to add.
     */
    void increase(int number) {
        count += number;
    }

    /** Subtract number from current count.
     @param number the number to add.
     */
    void decrease(int number) {
        count -= number;
    }

    /** Get current count.
     * @return value of counter.
     */
    public int getValue() {
        return count;
    }
}
