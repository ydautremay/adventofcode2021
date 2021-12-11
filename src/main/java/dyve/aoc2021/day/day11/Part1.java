package dyve.aoc2021.day.day11;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.*;

public class Part1 extends Part {

    public static void main(String[] args)throws Exception{
        new Part1().subMain(11);
    }

    Map<Point, Integer> octopuses = new HashMap<>();

    int flashes = 0;

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();

        for(int y = 0; y < entries.size(); y++){
            String entry = entries.get(y);
            for(int x = 0; x < entry.length(); x++){
                octopuses.put(Point.of(x, y), Integer.parseInt("" + entry.charAt(x)));
            }
        }
        for(int cycle = 1; cycle < 101; cycle++){
            //everyone gets +1
            octopuses.replaceAll((p, v) -> v + 1);
            //flash !
            for(Point p : octopuses.keySet()){
                flash(p, false);
            }
        }

        return flashes;
    }

    private void flash(Point p, boolean flashed){
        if(octopuses.get(p) == null || octopuses.get(p) == 0){
            return;
        }
        if(flashed){
            octopuses.put(p, octopuses.get(p) + 1);
        }
        if(octopuses.get(p) > 9){
            octopuses.put(p, 0);
            flashes++;
            flash(p.above(), true);
            flash(p.aboveRight(), true);
            flash(p.right(), true);
            flash(p.belowRight(), true);
            flash(p.below(), true);
            flash(p.belowLeft(), true);
            flash(p.left(), true);
            flash(p.aboveLeft(), true);
        }
    }

    private String octopusesToString(){
        StringBuffer sb = new StringBuffer();
        for (int y = 0; y < 10; y++){
            for(int x = 0; x < 10; x++){
                sb.append(octopuses.get(Point.of(x, y)));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
