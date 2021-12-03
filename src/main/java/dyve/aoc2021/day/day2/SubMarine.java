package dyve.aoc2021.day.day2;

public class SubMarine {

    Position position = new Position(0, 0);

    public void move(Command command){
        switch (command.direction){
            case UP -> up(command.amount);
            case DOWN -> down(command.amount);
            case FORWARD -> forward(command.amount);
        }
    }

    public void forward(int v){
        position.horizontal += v;
    }

    public void down(int v){
        position.depth += v;
    }

    public void up(int v){
        position.depth -= v;
    }

}
