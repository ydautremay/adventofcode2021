package dyve.aoc2021.day.day10;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.day.day4.BingoBoard;
import dyve.aoc2021.input.InputReader;

import java.util.*;

public class Part1 extends Part {

    public static void main(String[] args)throws Exception{
        new Part1().subMain(10);
    }

    Map<Character, Integer> points = new HashMap<>();
    {
        points.put(')', 3);
        points.put(']', 57);
        points.put('}', 1197);
        points.put('>', 25137);
    }

    Map<Character, Character> pairs = new HashMap<>();
    {
        pairs.put('(', ')');
        pairs.put('[', ']');
        pairs.put('{', '}');
        pairs.put('<', '>');
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();

        long score = 0;

        for (String entry : entries){
            Deque<Character> stack = new ArrayDeque<>();
            for(char c : entry.toCharArray()){
                if(pairs.containsKey(c)){
                    stack.addFirst(c);
                }else{
                    char lastInserted = stack.removeFirst();
                    if(c != pairs.get(lastInserted)){
                        score += points.get(c);
                        break;
                    }
                }
            }
        }

        return score;
    }
}
