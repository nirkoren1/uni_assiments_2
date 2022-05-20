package listeners;

public class Counter {
    private int value;
    // add number to current count.
    public void increase(int number) {
        this.value += number;
    }
    // subtract number from current count.
    public void decrease(int number) {
        this.value -= number;
    }
    // get current count.
    public int getValue() {
        return value;
    }
}
