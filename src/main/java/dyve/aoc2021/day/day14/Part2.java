package dyve.aoc2021.day.day14;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 extends Part {

    public static void main(String[] args)throws Exception{
        new Part2().subMain(14);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();

        String component = entries.get(0);

        Map<String, String> pairMap = new HashMap<>();
        Map<String, Long> occurences = new HashMap<>();
        Map<String, Long> pairOccurences = new HashMap<>();

        for(String entry : entries){
            if(!entry.contains("->")){
                continue;
            }
            String[] parts = entry.split(" -> ");
            pairMap.put(parts[0], parts[1]);
        }
        for (int i = 0; i < component.length() - 1; i++) {
            occurences.compute("" + component.charAt(i), (c, l) -> l == null ? 1 : l + 1);
            String pair = "" + component.charAt(i) + component.charAt(i + 1);
            pairOccurences.compute(pair, (p, l) -> l == null ? 1 : l + 1);
        }
        occurences.compute("" + component.charAt(component.length() - 1), (c, l) -> l == null ? 1 : l + 1);

        for (int step = 0; step < 40; step++) {
            Map<String, Long> currentPairOccurences = new HashMap<>(pairOccurences);
            for (Map.Entry<String, Long> entry : pairOccurences.entrySet()) {
                String pair = entry.getKey();
                long value = entry.getValue();
                if (!pairMap.containsKey(pair)) {
                    continue;
                }
                occurences.compute(pairMap.get(pair), (s, l) -> l == null ? value : l + value);
                currentPairOccurences.compute(pair.charAt(0) + pairMap.get(pair), (s, l) -> l == null ? value : l + value);
                currentPairOccurences.compute(pairMap.get(pair) + pair.charAt(1), (s, l) -> l == null ? value : l + value);
                currentPairOccurences.compute(pair, (s, l) -> l == null ? 0 : l - value);
            }
            pairOccurences = currentPairOccurences;
        }

        long min = occurences.entrySet().stream().min(Comparator.comparingLong(Map.Entry::getValue)).orElseThrow().getValue();
        long max = occurences.entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue)).orElseThrow().getValue();

        return max - min;
    }
}
