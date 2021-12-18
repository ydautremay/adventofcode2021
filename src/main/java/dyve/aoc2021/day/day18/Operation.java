package dyve.aoc2021.day.day18;

public abstract class Operation {

    String input;

    int depth;

    protected Operation operation1;

    protected Operation operation2;

    protected Operation(){};

    protected Operation(String input, int depth){
        this.input = input;
        this.depth = depth;
        setOperations();
    }

    public static Operation of(String input, int depth){
        if(input.matches("^\\d$")){
            return new SnailNumber(input, depth);
        }
        if(input.matches("^\\[\\d,\\d]$")){
            return new Pair(input, depth);
        }
        return new Sum(input, depth);
    }

    protected abstract void setOperations();

    public abstract int magnitude();

    public static Operation add(Operation operation1, Operation operation2){
        Operation operation = new Sum();
        operation.operation1 = operation1;
        operation1.addDepth();
        operation.operation2 = operation2;
        operation2.addDepth();
        operation.input = operation.toString();
        operation.depth = 0;
        return operation;
    }

    protected abstract void addDepth();

}
