package dyve.aoc2021.day.day14;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 extends Part {

    public static void main(String[] args)throws Exception{
        new Part1().subMain(14);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();

        String component = entries.get(0);

        Map<String, String> pairs = new HashMap<>();
        Map<String, Integer> occurences = new HashMap<>();

        for(String entry : entries){
            if(!entry.contains("->")){
                continue;
            }
            String[] parts = entry.split(" -> ");
            pairs.put(parts[0], parts[1]);
        }

        for (int step = 0; step < 10; step++) {
            String newComponent = "";
            for (int i = 0; i < component.length() - 1; i++) {
                newComponent += component.charAt(i);
                String pair = "" + component.charAt(i) + component.charAt(i + 1);
                if (pairs.containsKey(pair)) {
                    newComponent += pairs.get(pair);
                }
            }
            newComponent += component.charAt(component.length() - 1);
            component = newComponent;
        }


        for (int i = 0; i < component.length(); i++) {
            occurences.compute("" + component.charAt(i), (k, v) -> v == null ? 1 : v + 1);
        }
        int minOccurrence = occurences.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue)).orElseThrow().getValue();
        int maxOccurrence = occurences.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).orElseThrow().getValue();
        return maxOccurrence - minOccurrence;
    }
}
