package dyve.aoc2021.day.day3;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 extends Part {

    public static void main(String[] args)throws Exception{
        new Part2().subMain(3);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();

        int binSize = entries.get(0).length();

        String o2Str = "";
        String co2Str = "";

        List<String> remain = new ArrayList<>(entries);
        for(int i = 0; i < binSize; i++){
            int finalI = i;
            long nb0 = remain.stream().mapToInt(s -> s.charAt(finalI)).filter(c -> '0' == c).count();
            long nb1 = remain.size() - nb0;
            char common;
            if(nb1 >= nb0){
                common = '1';
            }else{
                common = '0';
            }
            List<String> newRemain = new ArrayList<>();
            for(String s : remain){
                if(s.charAt(i) == common){
                    newRemain.add(s);
                }
            }
            remain = newRemain;
            if(remain.size() == 1){
                break;
            }
        }
        o2Str = remain.get(0);

        remain = new ArrayList<>(entries);
        for(int i = 0; i < binSize; i++){
            int finalI = i;
            long nb0 = remain.stream().mapToInt(s -> s.charAt(finalI)).filter(c -> '0' == c).count();
            long nb1 = remain.size() - nb0;
            char common;
            if(nb1 < nb0){
                common = '1';
            }else{
                common = '0';
            }
            List<String> newRemain = new ArrayList<>();
            for(String s : remain){
                if(s.charAt(i) == common){
                    newRemain.add(s);
                }
            }
            remain = newRemain;
            if(remain.size() == 1){
                break;
            }
        }
        co2Str = remain.get(0);

        int o2 = Integer.parseInt(o2Str, 2);
        int co2 = Integer.parseInt(co2Str, 2);

        return o2 * co2;
    }
}
