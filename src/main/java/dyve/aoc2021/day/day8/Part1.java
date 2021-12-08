package dyve.aoc2021.day.day8;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.List;

public class Part1 extends Part {

    public static void main(String[] args)throws Exception{
        new Part1().subMain(8);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();

        int nb1 = 0, nb4 = 0, nb7 = 0, nb8 = 0;

        for(String entry : entries){
            String right = entry.split(" \\| ")[1];
            String[] digits = right.split(" ");
            for(String digit : digits){
                switch (digit.length()){
                    case 2 -> nb1++;
                    case 3 -> nb7++;
                    case 4 -> nb4++;
                    case 7 -> nb8++;
                }
            }
        }

        return nb1 + nb4 + nb7 + nb8;
    }
}
