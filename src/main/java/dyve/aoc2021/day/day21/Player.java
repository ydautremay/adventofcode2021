package dyve.aoc2021.day.day21;

public class Player {

    int position = 0;

    int score = 0;

    Player(int position){
        this.position = position == 10 ? 0 : position;
    }

    void play(Die die){
        int dieResult = die.roll();
        position = (position + dieResult - 1) % 10 + 1;
        score += position;
    }
}
