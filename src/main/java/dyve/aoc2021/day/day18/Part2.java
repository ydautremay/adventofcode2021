package dyve.aoc2021.day.day18;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.List;

public class Part2 extends Part {

    public static void main(String[] args) throws Exception {
        new Part2().subMain(18);
    }

    @Override
    public Object execute(InputReader inputReader) {
        List<String> entries = inputReader.stream().toList();
        int maxMagnitude = 0;
        for (String entry : entries){
            for (String entry2 : entries){
                if(entry.equals(entry2)){
                    continue;
                }
                String sum = add(entry, entry2);
                sum = reduce(sum);
                Operation operation = Operation.of(sum, 0);
                maxMagnitude = Math.max(maxMagnitude, operation.magnitude());
            }
        }

        return maxMagnitude;
    }

    private String reduce(String s) {
        String previousString = s;
        String newString = explodeAndSplit(s);
        while (!newString.equals(previousString)) {
            previousString = newString;
            newString = explodeAndSplit(newString);
        }
        return newString;
    }

    private String explodeAndSplit(String s) {
        String previousString = s;
        String newString = explode(s);
        while (!newString.equals(previousString)){
            previousString = newString;
            newString = explode(newString);
        }
        return split(newString);
    }

    private String explode(String s) {
//        System.out.println("before explosion : " + s);
        String newString = "";
        int cursor = 0;
        boolean exploded = false;
        int nbBracket = 0;
        while(!exploded && cursor < s.length() - 1 ){
            char c = s.charAt(cursor);
            if(c == '['){
                nbBracket++;
            }else if(c == ']'){
                nbBracket--;
            }else{
                if(nbBracket > 4){
                    String substring = s.substring(cursor);
                    if(substring.matches("^\\d+,\\d+].*")){
                        exploded = true;
                        //explode !!
                        int commaIndex = substring.indexOf(",");
                        int bracketIndex = substring.indexOf("]");
                        long left = Long.parseLong(substring.substring(0, commaIndex));
                        long right = Long.parseLong(substring.substring(commaIndex + 1, bracketIndex));

                        //Find previous int
                        int cursor2 = cursor - 1;
                        boolean foundPrevious = false;
                        while(!foundPrevious && cursor2 > 0){
                            String c2 = String.valueOf(s.charAt(cursor2));
                            if(c2.matches("\\d")) {
                                foundPrevious = true;
                                int index = 1;
                                while (c2.matches("\\d+")){
                                    c2 = s.substring(cursor2 - index, cursor2 + 1);
                                    index++;
                                }
                                newString = s.substring(0, cursor2 - index + 2);
                                newString += (Long.parseLong(c2.substring(1)) + left);
                                newString += s.substring(cursor2 + 1, cursor - 1);
                            }
                            cursor2--;
                        }
                        if(!foundPrevious){
                            newString = s.substring(0, cursor - 1);
                        }

                        newString += "0";

                        //Add right to next int
                        boolean foundNext = false;
                        int cursor3 = cursor + bracketIndex + 1;
                        while(!foundNext && cursor3 < s.length()){
                            String c3 = String.valueOf(s.charAt(cursor3));
                            if(c3.matches("\\d")){
                                foundNext = true;
                                int index = 1;
                                while (c3.matches("\\d+")){
                                    c3 = s.substring(cursor3, cursor3 + index + 1);
                                    index++;
                                }
                                long next = Long.parseLong(c3.substring(0, c3.length() - 1));
                                newString += s.substring(cursor + bracketIndex + 1, cursor3);
                                newString += (next + right);
                                newString += s.substring(cursor3 + index - 1);
                            }
                            cursor3++;
                        }
                        if(!foundNext){
                            newString += s.substring(cursor + bracketIndex + 1);
                        }
                        s = newString;
                    }
                }
            }
            cursor++;
        }
        if(!exploded){
//            System.out.println("no explosion");
            return s;
        }else {
//            System.out.println("After explosion  : " + newString);
            return newString;
        }
    }

    private String split(String s){
//        System.out.println("Before split : " + s);
        int cursor = 0;
        boolean split = false;
        String newString = "";
        while(!split && cursor < s.length() - 1 ){
            if(s.substring(cursor, cursor + 2).matches("\\d\\d")){
                split = true;
                int size = 1;
                while(s.substring(cursor, cursor + size + 2).matches("\\d+")){
                    size++;
                }
                long toSplit = Long.parseLong(s.substring(cursor, cursor + size + 1));
                long left, right;
                if(toSplit%2 == 0){
                    left = toSplit/2;
                    right = left;
                }else{
                    left = toSplit/2;
                    right = toSplit/2 + 1;
                }
                newString += s.substring(0, cursor);
                newString = newString + "[" + left + "," + right + "]";
                newString += s.substring(cursor + size + 1);
            }
            cursor++;
        }
        if(!split){
//            System.out.println("No split");
            return s;
        }else{
//            System.out.println("After split  : " + newString);
            return newString;
        }
    }

    public String add(String s1, String s2){
        return "[" + s1 + "," + s2 + "]";
    }
}