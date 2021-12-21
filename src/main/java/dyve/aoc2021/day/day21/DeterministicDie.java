package dyve.aoc2021.day.day21;

import java.util.concurrent.atomic.AtomicInteger;

public class DeterministicDie implements Die {

    int nbRolls = 0;

    @Override
    public int roll() {
        int result = 3 * (nbRolls % 10) + 6;
        nbRolls += 3;
        return result;
    }

    @Override
    public int nbRolls() {
        return nbRolls;
    }
}
