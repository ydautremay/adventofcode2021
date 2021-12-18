package dyve.aoc2021.day.day18;

public class SnailNumber extends Operation {

    int number;

    protected SnailNumber(String input, int depth) {
        super(input, depth);
    }

    protected SnailNumber(int number){
        this.number = number;
    }

    @Override
    public int magnitude() {
        return number;
    }

    @Override
    protected void addDepth() {
        this.depth++;
    }

    @Override
    protected void setOperations() {
        number = Integer.parseInt(input);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
