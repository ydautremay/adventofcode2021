package dyve.aoc2021.day.day2;

public class Command {

    Direction direction;

    int amount;

    public Command(Direction d, int amount) {
        this.direction = d;
        this.amount = amount;
    }
}
