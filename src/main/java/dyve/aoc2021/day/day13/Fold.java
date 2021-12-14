package dyve.aoc2021.day.day13;

public class Fold {

    Direction direction;

    int value;

    public Fold(Direction direction, int value) {
        this.direction = direction;
        this.value = value;
    }

    public static Fold of(Direction direction, int value){
        return new Fold(direction, value);
    }

    @Override
    public String toString() {
        return "Fold{" +
                "direction=" + direction +
                ", value=" + value +
                '}';
    }
}
