// Nir Koren 316443902
package listeners;

/**
 * The Counter.
 */
public class Counter {
    private int value;

    /**
     * increase.
     * @param number the number
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * decrease.
     * @param number the number
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * get value.
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
