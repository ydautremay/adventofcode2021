package dyve.aoc2021.day.day18;

public class Sum extends Operation{

    protected Sum(String input, int depth) {
        super(input, depth);
    }

    protected Sum(){};

    @Override
    protected void setOperations() {
        String packet = input.substring(1, input.length() - 1);
        int cursor = 0;
        int nbBracket = 0;
        boolean foundComma = false;
        while(!foundComma){
            char c = packet.charAt(cursor);
            if(c == '['){
                nbBracket++;
            } else if(c == ']'){
                nbBracket--;
            }
            if(nbBracket == 0){
                foundComma = true;
                String input1 = packet.substring(0, cursor + 1);
                String  input2 = packet.substring(cursor + 2);
                operation1 = Operation.of(input1, depth + 1);
                operation2 = Operation.of(input2, depth + 1);
            }
            cursor++;
        }
    }

    @Override
    public int magnitude() {
        return 3 * operation1.magnitude() + 2 * operation2.magnitude();
    }

    @Override
    protected void addDepth() {
        this.depth++;
        operation1.addDepth();
        operation2.addDepth();
    }

    @Override
    public String toString() {
        return "[" + operation1 + "," + operation2 + "]";
    }
}
