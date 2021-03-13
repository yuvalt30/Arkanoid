// ID 311127120

/**
 * counter of integers.
 */
public class Counter {
    private int count;

    /**
     * constructor.
     * @param startsValue value from which count is started
     */
    public Counter(int startsValue) {
        this.count = startsValue;
    }

    /**
     * add number to current count.
     * @param number to be add to count
     */
    void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     * @param number to be substracted from count
     */
    void decrease(int number) {
        this.count -= number;
    }

    /**
     * get current count.
     * @return current count
     */
    int getValue() {
        return this.count;
    }
}