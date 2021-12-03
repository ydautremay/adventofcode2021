package dyve.aoc2021.day.day3;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.day.day1.DepthAnalyzer;
import dyve.aoc2021.input.InputReader;

import java.util.List;
import java.util.stream.Collectors;

public class Part1 extends Part {

    public static void main(String[] args)throws Exception{
        new Part1().subMain(3);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().collect(Collectors.toList());

        int binSize = entries.get(0).length();

        String gammaStr = "";
        String epsilonStr = "";

        for(int i = 0; i < binSize; i++){
            int nb0 = 0;
            for(String entry : entries){
                if(entry.charAt(i) == '0'){
                    nb0 ++;
                }
            }
            int nb1 = entries.size() - nb0;
            if(nb0 > nb1){
                gammaStr += "0";
                epsilonStr += "1";
            }else{
                gammaStr += "1";
                epsilonStr += "0";
            }
        }

        int gamma = Integer.parseInt(gammaStr, 2);
        int epsilon = Integer.parseInt(epsilonStr, 2);

        return gamma * epsilon;
    }
}
