package dyve.aoc2021.day.day4;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 extends Part {

    public static void main(String[] args)throws Exception{
        new Part2().subMain(4);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();
        List<Integer> drawn = Arrays.stream(entries.get(0).split(",")).map(Integer::parseInt).toList();
        List<BingoBoard> boards = new ArrayList<>();
        List<BingoBoard> wonBoards = new ArrayList<>();

        BingoBoard currentBoard = new BingoBoard(5);

        int row = 0;
        for(int i = 2; i < entries.size(); i++){
            String s = entries.get(i);
            if(s.isEmpty()){
                boards.add(currentBoard);
                currentBoard = new BingoBoard(5);
                row = 0;
                continue;
            }
            String[] numbers = s.split(" ");
            int offset = 0;
            for(int j = 0; j < numbers.length; j++){
                if(!numbers[j].isEmpty()) {
                    currentBoard.put(row, j - offset, Integer.parseInt(numbers[j]));
                }else{
                    offset++;
                }
            }
            row++;
        }
        boards.add(currentBoard);

        for(int number : drawn){
            for(BingoBoard board : boards){
                board.draw(number);
                if(board.win){
                    wonBoards.add(board);
                }
            }
            boards.removeAll(wonBoards);
        }

        return wonBoards.get(wonBoards.size() - 1).score;
    }
}
