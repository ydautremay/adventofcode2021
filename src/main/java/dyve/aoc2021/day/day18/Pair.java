package dyve.aoc2021.day.day18;

public class Pair extends Operation{

    protected Pair(String input, int depth) {
        super(input, depth);
    }

    protected Pair(){};

    @Override
    protected void setOperations() {
        String[] s = input.substring(1, input.length() - 1).split(",");
        operation1 = new SnailNumber(s[0], depth + 1);
        operation2 = new SnailNumber(s[1], depth + 1);
    }

    @Override
    public int magnitude() {
        return 3 * getLeft().number + 2 * getRight().number;
    }

    @Override
    protected void addDepth() {

    }

    public SnailNumber getLeft(){
        return (SnailNumber) operation1;
    }

    public SnailNumber getRight(){
        return (SnailNumber) operation2;
    }

    @Override
    public String toString() {
        return "[" + operation1.toString() + "," + operation2.toString() + "]";
    }
}
