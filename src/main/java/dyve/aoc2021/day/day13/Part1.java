package dyve.aoc2021.day.day13;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.day.day4.BingoBoard;
import dyve.aoc2021.input.InputReader;

import java.util.*;

public class Part1 extends Part {

    public static void main(String[] args)throws Exception{
        new Part1().subMain(13);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();

        Map<Integer, Map<Integer, Point>> grid = new HashMap<>();
        List<Fold> folds = new ArrayList<>();

        for(String entry : entries){
            if(entry.contains(",")){
                String[] coords = entry.split(",");
                Point point = Point.of(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                grid.computeIfAbsent(point.x, k -> new HashMap<>()).put(point.y, point);
            }else if(entry.contains("=")){
                String newEntry = entry.replace("fold along ", "");
                String[] foldStr = newEntry.split("=");
                folds.add(Fold.of(Direction.valueOf(foldStr[0]), Integer.parseInt(foldStr[1])));
            }
        }

        Fold firstFold = folds.get(0);
        Map<Integer, Map<Integer, Point>> newGrid = new HashMap<>();
        for(Map<Integer, Point> y : grid.values()){
            for(Point p : y.values()){
                Point newPoint = p.fold(firstFold);
                newGrid.computeIfAbsent(newPoint.x, k -> new HashMap<>()).put(newPoint.y, newPoint);
            }
        }

        int dots = 0;
        for(Map<Integer, Point> y : newGrid.values()){
            dots = dots + y.values().size();
        }

        return dots;
    }
}
