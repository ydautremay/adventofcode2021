package dyve.aoc2021.day.day21;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 extends Part {

    public static void main(String[] args)throws Exception{
        new Part2().subMain(21);
    }

    public static final int[] MULTS = { 1, 3, 6, 7, 6, 3, 1 };

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();
        Map<GameState, Long> qps = new HashMap<>();

        int p1pos = Integer.parseInt(entries.get(0).split(": ")[1]);
        int p2pos = Integer.parseInt(entries.get(1).split(": ")[1]);
        GameState start = new GameState(p1pos, 0, p2pos, 0);
        qps.put(start, 1L);

        long p1Wins = 0;
        long p2Wins = 0;
        int pturn = 1;
        while (!qps.isEmpty()) {
            Map<GameState, Long> nextQps = new HashMap<>();
            for (java.util.Map.Entry<GameState, Long> e : qps.entrySet()) {
                for (int i = 0; i < 7; i++) {
                    int dist = i + 3;
                    int mult = MULTS[i];

                    GameState next = e.getKey().roll(pturn, dist);
                    if (next.isDone()) {
                        if (pturn == 1) {
                            p1Wins += e.getValue() * mult;
                        } else {
                            p2Wins += e.getValue() * mult;
                        }
                    } else {
                        nextQps.merge(next, e.getValue() * mult, (o, n) -> o + n);
                    }

                }
            }
            pturn = 3 - pturn;
            qps = nextQps;
        }

        return Math.max(p1Wins, p2Wins);
    }

    private record GameState(int p1Pos, int p1Score, int p2Pos, int p2Score) {
        public GameState roll(int player, int dist) {
            if (player == 1) {
                int newPos = (p1Pos + dist - 1) % 10 + 1;
                return new GameState(newPos, p1Score + newPos, p2Pos, p2Score);
            } else {
                int newPos = (p2Pos + dist - 1) % 10 + 1;
                return new GameState(p1Pos, p1Score, newPos, p2Score + newPos);
            }
        }

        public boolean isDone() { return p1Score >= 21 || p2Score >= 21; }
    }
}
