package dyve.aoc2021.day.day2;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.List;

public class Part1 extends Part {

    public static void main(String[] args)throws Exception{
        new Part1().subMain(2);
    }

    @Override
    protected Object execute(InputReader inputReader) throws Exception {
        List<String> entries = inputReader.stream().toList();
        SubMarine subMarine = new SubMarine();
        for(String entry : entries){
            String[] parts = entry.split(" ");
            Command command = new Command(Direction.valueOf(parts[0].toUpperCase()), Integer.parseInt(parts[1]));
            subMarine.move(command);
        }
        return subMarine.position.horizontal * subMarine.position.depth;
    }
}
