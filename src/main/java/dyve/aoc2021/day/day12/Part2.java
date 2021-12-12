package dyve.aoc2021.day.day12;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.*;
import java.util.stream.Collectors;

public class Part2 extends Part {

    public static void main(String[] args)throws Exception{
        new Part2().subMain(12);
    }

    Map<String, Cave> caves = new HashMap<>();
    Set<List<Cave>> paths = new HashSet<>();

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

        for(Cave smallCave : caves.values().stream().filter(c -> !c.equals(start)).filter(c -> !c.equals(end)).filter(c -> c.small).toList()) {
            explore(start, new HashSet<>(), smallCave, false, new ArrayList<>());
        }
        return paths.size();
    }

    void explore(Cave cave, Set<Cave> smallVisited, Cave smallCave, boolean smallCaveRevisited, List<Cave> path){
        if(cave.small && !cave.equals(smallCave) && smallVisited.contains(cave)){
            return;
        }else if(cave.small && cave.equals(smallCave) && smallVisited.contains(cave) && smallCaveRevisited){
            return;
        }
        List<Cave> newPath = new ArrayList<>(path);
        newPath.add(cave);
        if(paths.contains(newPath)){
            return;
        }
        if(cave.equals(end)){
            paths.add(newPath);
            return;
        }
        boolean smallCaveRevisited2 = smallCaveRevisited || (cave.equals(smallCave) && smallVisited.contains(cave));
        if(cave.small){
            smallVisited.add(cave);
        }
        for(Cave neighbour : cave.neighbours){
            explore(neighbour, new HashSet<>(smallVisited), smallCave, smallCaveRevisited2, newPath);
        }
    }
}
