package dyve.aoc2021.day.day2;

public class SubMarine2 {

    Position position = new Position(0, 0);

    int aim;

    public void move(Command command){
        switch (command.direction){
            case UP -> up(command.amount);
            case DOWN -> down(command.amount);
            case FORWARD -> forward(command.amount);
        }
    }

    public void forward(int v){
        position.horizontal += v;
        position.depth += aim * v;
    }

    public void down(int v){
        aim += v;
    }

    public void up(int v){
        aim -= v;
    }

}
