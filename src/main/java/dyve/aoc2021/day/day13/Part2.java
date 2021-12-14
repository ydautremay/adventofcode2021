package dyve.aoc2021.day.day13;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 extends Part {

    public static void main(String[] args)throws Exception{
        new Part2().subMain(13);
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

        for(Fold fold : folds) {
            Map<Integer, Map<Integer, Point>> newGrid = new HashMap<>();
            for (Map<Integer, Point> y : grid.values()) {
                for (Point p : y.values()) {
                    Point newPoint = p.fold(fold);
                    newGrid.computeIfAbsent(newPoint.x, k -> new HashMap<>()).put(newPoint.y, newPoint);
                }
            }
            grid = newGrid;
        }

        int minX = grid.keySet().stream().mapToInt(i -> i).min().orElseThrow();
        int maxX = grid.keySet().stream().mapToInt(i -> i).max().orElseThrow();
        int minY = Integer.MAX_VALUE;
        for (Map<Integer, Point> y : grid.values()) {
            minY = Math.min(minY, y.keySet().stream().mapToInt(i -> i).min().orElseThrow());
        }
        int maxY = Integer.MIN_VALUE;
        for (Map<Integer, Point> y : grid.values()) {
            maxY = Math.max(minY, y.keySet().stream().mapToInt(i -> i).max().orElseThrow());
        }

        StringBuilder sb = new StringBuilder();
        for(int y = minY; y <= maxY; y++){
            for(int x = minX; x <= maxX; x++){
                sb.append(grid.computeIfAbsent(x, k -> new HashMap<>()).get(y) == null ? " " : "#");
            }
            sb.append("\n");
        }

        return sb;
    }
}
