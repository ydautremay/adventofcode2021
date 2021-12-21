package dyve.aoc2021.day.day21;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.day.day11.Point;
import dyve.aoc2021.input.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 extends Part {

    public static void main(String[] args)throws Exception{
        new Part1().subMain(21);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();
        int[] pos = new int[2];
        pos[0] = Integer.parseInt(entries.get(0).split(": ")[1]);
        pos[1] = Integer.parseInt(entries.get(1).split(": ")[1]);
        int[] scores = { 0, 0 };

        int rolls = 0;
        int pturn = 0;
        while (true) {
            int dist = 3 * (rolls % 10) + 6;
            rolls += 3;

            int newPos = (pos[pturn] + dist - 1) % 10 + 1;
            pos[pturn] = newPos;
            scores[pturn] += newPos;
            if (scores[pturn] >= 1000) {
                break;
            }
            pturn = 1 - pturn;
        }
        return scores[1 - pturn] * rolls;
    }

 }
