package dyve.aoc2021.day.day9;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.*;

public class Part2 extends Part {

    public static void main(String[] args)throws Exception{
        new Part2().subMain(9);
    }

    Map<Point, Integer> terrain = new HashMap<>();

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();
//        entries = List.of(
//                "2199943210",
//                "3987894921",
//                "9856789892",
//                "8767896789",
//                "9899965678"
//        );

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

        Set<Basin> basins = new HashSet<>();

        for(Point lowPoint : lowPoints.keySet()){
            Basin basin = new Basin();
            fillBasin(basin, lowPoint);
            basins.add(basin);
            System.out.println(basin.getPoints());
            System.out.println(basin.surface());
        }

        return basins.stream().map(Basin::surface).sorted(Comparator.reverseOrder()).limit(3).mapToLong(i -> i).reduce((i, j) -> i * j).getAsLong();
    }

    private void fillBasin(Basin basin, Point currentPoint){
        if(basin.contains(currentPoint) || terrain.getOrDefault(currentPoint, 9) == 9){
            return;
        }
        basin.add(currentPoint);
        fillBasin(basin, currentPoint.above());
        fillBasin(basin, currentPoint.below());
        fillBasin(basin, currentPoint.right());
        fillBasin(basin, currentPoint.left());
    }
}
