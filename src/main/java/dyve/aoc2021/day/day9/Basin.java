package dyve.aoc2021.day.day9;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Basin {

    private Set<Point> points = new HashSet<>();

    public int surface(){
        return points.size();
    }

    public Set<Point> getPoints() {
        return points;
    }

    public void add(Point p){
        points.add(p);
    }

    public boolean contains(Point p){
        return points.contains(p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basin basin = (Basin) o;
        return points.equals(basin.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }
}
