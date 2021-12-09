package dyve.aoc2021.day.day9;

import java.util.Objects;

public class Point {

    final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point of(int x, int y){
        return new Point(x, y);
    }

    public Point above(){
        return Point.of(x, y + 1);
    }

    public Point below(){
        return Point.of(x, y - 1);
    }

    public Point right(){
        return Point.of(x + 1, y);
    }

    public Point left(){
        return Point.of(x - 1, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString(){
        return "(" + x + "," + y + ")";
    }
}
