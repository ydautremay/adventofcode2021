package dyve.aoc2021.day.day5;

import java.util.HashSet;
import java.util.Set;

public class Line {

    final Point begin, end;

    public Line(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    public Set<Point> listPoints(boolean part1){
        Set<Point> points = new HashSet<>();
        points.add(begin);
        points.add(end);
        if(begin.equals(end)){
            return points;
        }
        if(begin.x == end.x){
            int y = begin.y;
            if(begin.y < end.y){
                while(y < end.y){
                    y++;
                    points.add(new Point(begin.x, y));
                }
            }else{
                while(y > end.y){
                    y--;
                    points.add(new Point(begin.x, y));
                }
            }
        }else if(begin.y == end.y){
            int x = begin.x;
            if(begin.x < end.x){
                while(x < end.x){
                    x++;
                    points.add(new Point(x, begin.y));
                }
            }else{
                while(x > end.x){
                    x--;
                    points.add(new Point(x, begin.y));
                }
            }
        }else{
            if(part1){
                return Set.of();
            }else{
                int x = begin.x;
                int y = begin.y;
                if(begin.x < end.x && begin.y < end.y){
                    while (x < end.x){
                        x++;
                        y++;
                        points.add(new Point(x, y));
                    }
                }else if(begin.x < end.x){
                    while (x < end.x){
                        x++;
                        y--;
                        points.add(new Point(x, y));
                    }
                }else if(begin.y < end.y){
                    while (x > end.x) {
                        x--;
                        y++;
                        points.add(new Point(x, y));
                    }
                }else{
                    while (x > end.x) {
                        x--;
                        y--;
                        points.add(new Point(x, y));
                    }
                }
            }
        }
        return points;
    }

    public String toString(){
        return begin + " -> " + end;
    }
}
