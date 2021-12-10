package dyve.aoc2021.day.day10;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.*;
import java.util.stream.Collectors;

public class Part2 extends Part {

    public static void main(String[] args)throws Exception{
        new Part2().subMain(10);
    }

    Map<Character, Integer> points = new HashMap<>();
    {
        points.put(')', 1);
        points.put(']', 2);
        points.put('}', 3);
        points.put('>', 4);
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
//        entries = List.of("<{([{{}}[<[[[<>{}]]]>[]]");

        List<Long> scores = new ArrayList<>();

        loop: for (String entry : entries){
            Deque<Character> stack = new ArrayDeque<>();
            for(char c : entry.toCharArray()){
                if(pairs.containsKey(c)){
                    stack.addFirst(c);
                }else{
                    char lastInserted = stack.removeFirst();
                    if(c != pairs.get(lastInserted)){
                        continue loop;
                    }
                }
            }
            System.out.println(stack);
            long score = 0;
            while(!stack.isEmpty()){
                char lastInserted = stack.removeFirst();
                score *= 5;
                score += points.get(pairs.get(lastInserted));
            }
            scores.add(score);
        }

        scores = scores.stream().sorted().toList();
        return scores.get(scores.size()/2);
    }
}
