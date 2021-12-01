package dyve.aoc2021.day.day1;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Collectors;

public class DepthAnalyzer {

    int nbIncrease = 0;

    final int windowSize;

    Queue<Integer> window;

    int previousWindowSum = Integer.MAX_VALUE;

    public DepthAnalyzer(int windowSize){
        this.windowSize = windowSize;
        window = new ArrayDeque<>(windowSize);
    }

    public void analyze(int newDepth){
        window.offer(newDepth);
        if(window.size() <= windowSize){
            return;
        }
        window.poll();
        System.out.println(this);
        int sum = sum();
        if(sum > previousWindowSum){
            nbIncrease++;
        }
        previousWindowSum = sum;
    }

    private int sum(){
        return window.stream().mapToInt(value -> value).sum();
    }

    public String toString(){
        return window.stream().map(i -> "" + i).collect(Collectors.joining("+")) + " = " + sum();
    }
}
