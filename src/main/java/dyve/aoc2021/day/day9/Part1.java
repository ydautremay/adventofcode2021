package dyve.aoc2021.day.day9;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 extends Part {

    public static void main(String[] args)throws Exception{
        new Part1().subMain(9);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();

        Map<Point, Integer> terrain = new HashMap<>();
        Map<Point, Integer> lowPoints = new HashMap<>();

        int y = 0;
        for (String line : entries){
            int x = 0;
            for(char n : line.toCharArray()){
                terrain.put(Point.of(x, y), Integer.parseInt(""+n));
                x++;
            }
            y++;
        }

        for(Map.Entry<Point, Integer> entry : terrain.entrySet()){
            Point point = entry.getKey();
            int value = entry.getValue();
            if(terrain.getOrDefault(point.above(), Integer.MAX_VALUE) > value
                    && terrain.getOrDefault(point.below(), Integer.MAX_VALUE) > value
                    && terrain.getOrDefault(point.right(), Integer.MAX_VALUE) > value
                    && terrain.getOrDefault(point.left(), Integer.MAX_VALUE) > value){
                lowPoints.put(point, value);
            }
        }

        return lowPoints.values().stream().mapToLong(v -> v + 1).sum();
    }
}
