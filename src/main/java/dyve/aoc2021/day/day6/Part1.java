package dyve.aoc2021.day.day6;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 extends Part {

    public static void main(String[] args)throws Exception{
        new Part1().subMain(6);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();

        Map<Long, Long> fishCycles = new HashMap<>();

        String[] inputs = entries.get(0).split(",");
        for (String input : inputs){
            fishCycles.compute(Long.parseLong(input), (i, nb) -> nb == null ? 1 : nb + 1);
        }

        for(int i = 0; i < 80; i++){
            Map<Long, Long> newFishCycles = new HashMap<>();
            newFishCycles.put(8L, fishCycles.getOrDefault(0L, 0L));
            newFishCycles.put(7L, fishCycles.getOrDefault(8L, 0L));
            newFishCycles.put(6L, fishCycles.getOrDefault(0L, 0L) + fishCycles.getOrDefault(7L, 0L));
            newFishCycles.put(5L, fishCycles.getOrDefault(6L, 0L));
            newFishCycles.put(4L, fishCycles.getOrDefault(5L, 0L));
            newFishCycles.put(3L, fishCycles.getOrDefault(4L, 0L));
            newFishCycles.put(2L, fishCycles.getOrDefault(3L, 0L));
            newFishCycles.put(1L, fishCycles.getOrDefault(2L, 0L));
            newFishCycles.put(0L, fishCycles.getOrDefault(1L, 0L));
            fishCycles = newFishCycles;
        }

        return fishCycles.values().stream().mapToLong(i -> i).sum();
    }
}
