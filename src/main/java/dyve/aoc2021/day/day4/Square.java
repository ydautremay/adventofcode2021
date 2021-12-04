package dyve.aoc2021.day.day4;

public class Square {

    final int number;

    boolean checked = false;

    public Square(int number) {
        this.number = number;
    }

    public String toString(){
        return (number<10?" "+number:number) + (checked?"✓":"✗");
    }
}
