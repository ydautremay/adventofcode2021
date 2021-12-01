package dyve.aoc2021.day.day1;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.List;
import java.util.stream.Collectors;

public class Part1 extends Part {

    public static void main(String[] args)throws Exception{
        new Part1().subMain(1);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().collect(Collectors.toList());
        DepthAnalyzer da = new DepthAnalyzer(1);
        entries.stream().mapToInt(Integer::parseInt).forEach(da::analyze);

        return da.nbIncrease;
    }
}
