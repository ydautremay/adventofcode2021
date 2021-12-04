package dyve.aoc2021.day.day4;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BingoBoard {

    final int size;

    boolean win = false;

    int score;

    public BingoBoard(int size){
        this.size = size;
    }

    //Map<row, map<column, number>>
    Map<Integer, Map<Integer, Square>> grid = new HashMap<>();

    public void put(int row, int column, int number){
        grid.computeIfAbsent(row, r -> new HashMap<>()).put(column, new Square(number));
    }

    public Square get(int row, int column){
        return grid.computeIfAbsent(row, r -> new HashMap<>()).get(column);
    }

    public void draw(int number){
        if(win){
            return;
        }
        Square foundSquare = null;
        int foundRow = -1, foundColumn = -1;
        loop: for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                Square square = get(i, j);
                if(square.number == number){
                    foundSquare = square;
                    foundRow = i;
                    foundColumn = j;
                    foundSquare.checked = true;
                    break loop;
                }
            }
        }
        if(foundSquare == null){
            return;
        }
        boolean checked = true;
        //verify row
        for (int j = 0; j < size; j++){
            checked &= get(foundRow, j).checked;
        }
        if(checked){
            win(number);
            return;
        }
        checked = true;
        //verify column
        for (int i = 0; i < size; i++){
            checked &= get(i, foundColumn).checked;
        }
        if(checked){
            win(number);
        }
    }

    private void win(int drawn){
        win = true;
        int sum = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                Square sq = get(i, j);
                if(!sq.checked){
                    sum += sq.number;
                }
            }
        }
        score = sum * drawn;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < size; i++){
            s.append(grid.get(i).values().stream().map(v -> ""+v).collect(Collectors.joining(",")));
            s.append("\n");
        }
        return s.toString();
    }
}
