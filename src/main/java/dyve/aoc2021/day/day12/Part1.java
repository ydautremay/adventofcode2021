package dyve.aoc2021.day.day12;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 extends Part {

    public static void main(String[] args)throws Exception{
        new Part1().subMain(12);
    }

    Map<String, Cave> caves = new HashMap<>();
    int paths = 0;

    Cave start = null;
    Cave end = null;

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();
        for(String entry : entries){
            String[] names = entry.split("-");
            Cave cave1 = caves.computeIfAbsent(names[0], k -> Cave.of(names[0]));
            Cave cave2 = caves.computeIfAbsent(names[1], k -> Cave.of(names[1]));

            cave1.neighbours.add(cave2);
            cave2.neighbours.add(cave1);
        }

        start = caves.entrySet().stream().filter(e -> e.getKey().equals("start")).findFirst().orElseThrow().getValue();
        end = caves.entrySet().stream().filter(e -> e.getKey().equals("end")).findFirst().orElseThrow().getValue();


        explore(start, new ArrayList<>());

        return paths;
    }

    void explore(Cave cave, List<Cave> smallVisited){
        if(cave.small && smallVisited.contains(cave)){
            return;
        }
        if(cave.equals(end)){
            paths++;
            return;
        }
        if(cave.small){
            smallVisited.add(cave);
        }
        for(Cave neighbour : cave.neighbours){
            explore(neighbour, new ArrayList<>(smallVisited));
        }
    }
}
