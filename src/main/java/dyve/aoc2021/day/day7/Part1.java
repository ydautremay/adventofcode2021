package dyve.aoc2021.day.day7;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Part1 extends Part {

    public static void main(String[] args)throws Exception{
        new Part1().subMain(7);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();
//        entries = List.of("16,1,2,0,4,2,7,1,2,14");

        List<Integer> positions = Arrays.stream(entries.get(0).split(",")).map(Integer::parseInt).sorted().toList();

        System.out.println(positions);

        int fuel = Integer.MAX_VALUE;

        for(int i = positions.get(0); i <= positions.get(positions.size() - 1); i++){
            int finalI = i;
            int newFuel = positions.stream().reduce(0, (result, element) -> result + (Math.abs(element - finalI)));
            fuel = Math.min(fuel, newFuel);
        }

        return fuel;
    }
}
