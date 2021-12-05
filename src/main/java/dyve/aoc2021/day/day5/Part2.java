package dyve.aoc2021.day.day5;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 extends Part {

    public static void main(String[] args)throws Exception{
        new Part2().subMain(5);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();
        Map<Point, Integer> map = new HashMap<>();

        List<Line> lines = new ArrayList<>();
        for (String entry : entries){
            String[] pointsStr = entry.split(" -> ");
            String[] beginStr = pointsStr[0].split(",");
            String[] endStr = pointsStr[1].split(",");
            Line line = new Line(new Point(Integer.parseInt(beginStr[0]), Integer.parseInt(beginStr[1])), new Point(Integer.parseInt(endStr[0]), Integer.parseInt(endStr[1])));
            lines.add(line);
        }

        for (Line line : lines){
            for(Point point : line.listPoints(false)){
                map.compute(point, (p, i) -> i == null ? 1 : i + 1);
            }
        }
        return map.values().stream().filter(i -> i > 1).count();
    }
}
