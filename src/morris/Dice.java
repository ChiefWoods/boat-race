package morris;

public class Dice {
    //Attributes
    private int minValue;
    private int maxValue;
    private int randomNum;

    //Constructor
    public Dice() {
        this(1, 6);
    }

    public Dice(int maxValue) {
        this.minValue = 1;
        this.maxValue = maxValue;
    }

    public Dice(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    //Getter and Setter
    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    //Generate and Return a random number btw (min ~ max)
    public int roll() {
        randomNum = (int) (Math.random()*(maxValue-minValue+1)+minValue);
        return randomNum;
    }
}
