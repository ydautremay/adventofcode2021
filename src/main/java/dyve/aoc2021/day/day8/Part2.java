package dyve.aoc2021.day.day8;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

import java.util.*;

public class Part2 extends Part {

    public static void main(String[] args)throws Exception{
        new Part2().subMain(8);
    }

    @Override
    public Object execute(InputReader inputReader){
        List<String> entries = inputReader.stream().toList();
        //entries = List.of("cgf gf fadegbc afgcde abgced fecba ceagf dcagbf fgde cdaeg | gdebacf gf gdfe cfega");

        long sum = 0;

        for(String entry : entries){
            System.out.println(entry);
            String[] parts = entry.split(" \\| ");
            String[] digitsStr = parts[0].split(" ");
            String[] display = parts[1].split(" ");

            EnumMap<SegmentPosition, String> segments = new EnumMap<>(SegmentPosition.class);
            Map<Integer, List<String>> digits = new HashMap<>();
            List<List<String>> cinqSegments = new ArrayList<>();
            List<List<String>> sixSegments = new ArrayList<>();
            identify(digitsStr, segments, digits, cinqSegments, sixSegments);
            System.out.println(digits);
            System.out.println(segments);
            System.out.println(Arrays.toString(display));

            String nums = "";
            for(String s : display){
                List<String> segs = List.of(s.split(""));
                for (Map.Entry<Integer, List<String>> e : digits.entrySet()) {
                    Integer i = e.getKey();
                    List<String> l = e.getValue();
                    if (segs.containsAll(l) && l.containsAll(segs)) {
                        nums += i;
                    }
                }
            }
            System.out.println(Long.parseLong(nums));
            System.out.println();
            sum += Long.parseLong(nums);
        }

        return sum;
    }

    private void identify(String[] digitsStr, EnumMap<SegmentPosition, String> segments, Map<Integer, List<String>> digits, List<List<String>> cinqSegments, List<List<String>> sixSegments) {
        List<List<String>> cinqSegmentsCopy = new ArrayList<>();
        List<List<String>> sixSegmentsCopy = new ArrayList<>();
        //On identifie 1, 4, 7, 8, les 5 segments et les 6 segments
        for(String digitStr : digitsStr){
            switch (digitStr.length()){
                case 2 -> digits.put(1, new ArrayList<>(List.of(digitStr.split(""))));
                case 3 -> digits.put(7, new ArrayList<>(List.of(digitStr.split(""))));
                case 4 -> digits.put(4, new ArrayList<>(List.of(digitStr.split(""))));
                case 5 -> {
                    cinqSegments.add(new ArrayList<>(List.of(digitStr.split(""))));
                    cinqSegmentsCopy.add(new ArrayList<>(List.of(digitStr.split(""))));
                }
                case 6 -> {
                    sixSegments.add(new ArrayList<>(List.of(digitStr.split(""))));
                    sixSegmentsCopy.add(new ArrayList<>(List.of(digitStr.split(""))));
                }
                case 7 -> digits.put(8, new ArrayList<>(List.of(digitStr.split(""))));
            }
        }
        //Segment haut
        segments.put(SegmentPosition.UP, digits.get(7).stream().filter(s -> !digits.get(1).contains(s)).findFirst().get());

        //On idnetifie 3
        for(List<String> current : cinqSegmentsCopy){
            if(current.containsAll(digits.get(1))){
                digits.put(3, current);
                break;
            }
        }
        cinqSegmentsCopy.remove(digits.get(3));
        cinqSegments.remove(digits.get(3));

        for(List<String> current : cinqSegmentsCopy){
            current.remove(segments.get(SegmentPosition.UP));
        }
        //Segment bas
        Set<String> commun = new HashSet<>(cinqSegmentsCopy.get(0));
        commun.retainAll(cinqSegmentsCopy.get(1));
        for(String s : commun){
            if(!digits.get(4).contains(s)){
                segments.put(SegmentPosition.BOTTOM, s);
            }
        }
        for(List<String> current : cinqSegmentsCopy){
            current.remove(segments.get(SegmentPosition.BOTTOM));
        }

        //On identifie 2, 5 et le segment bas gauche
        List<String> firstCopy = new ArrayList<>(cinqSegmentsCopy.get(0));
        firstCopy.removeAll(cinqSegmentsCopy.get(1));
        firstCopy.removeAll(digits.get(4));
        if(!firstCopy.isEmpty()){
            digits.put(2, cinqSegments.get(0));
            digits.put(5, cinqSegments.get(1));
            segments.put(SegmentPosition.BOTTOM_LEFT, firstCopy.get(0));
        }else{
            digits.put(2, cinqSegments.get(1));
            digits.put(5, cinqSegments.get(0));
            List<String> secondCopy = new ArrayList<>(cinqSegmentsCopy.get(1));
            secondCopy.removeAll(cinqSegmentsCopy.get(0));
            secondCopy.removeAll(digits.get(4));
            segments.put(SegmentPosition.BOTTOM_LEFT, secondCopy.get(0));
        }

        for(List<String> current : cinqSegmentsCopy){
            current.remove(segments.get(SegmentPosition.BOTTOM_LEFT));
        }

        //Segment milieu maintenant seul commun à 2 et 5
        for(String s : cinqSegmentsCopy.get(0)){
            if(cinqSegmentsCopy.get(1).contains(s)){
                segments.put(SegmentPosition.MIDDLE, s);
            }
        }
        for(List<String> current : cinqSegmentsCopy){
            current.remove(segments.get(SegmentPosition.MIDDLE));
        }

        //Segment haut droit dernier non connu de 2
        for(List<String> current : cinqSegmentsCopy){
            if(current.size() == 1){
                segments.put(SegmentPosition.UP_RIGHT, current.get(0));
            }
        }
        for(List<String> current : cinqSegmentsCopy){
            current.remove(segments.get(SegmentPosition.UP_RIGHT));
        }

        //Segment bas droit est le 2e segment de 1 qui n'est pas haut droit
        if(digits.get(1).get(0).equals(segments.get(SegmentPosition.UP_RIGHT))){
            segments.put(SegmentPosition.BOTTOM_RIGHT, digits.get(1).get(1));
        }else{
            segments.put(SegmentPosition.BOTTOM_RIGHT, digits.get(1).get(0));
        }
        for(List<String> current : cinqSegmentsCopy){
            current.remove(segments.get(SegmentPosition.BOTTOM_RIGHT));
        }

        //Segment haut gauche dernier non connu de 5
        for(List<String> current : cinqSegmentsCopy){
            if(current.size() == 1){
                segments.put(SegmentPosition.UP_LEFT, current.get(0));
            }
        }
        //On construit le 9, 0, 6
        digits.put(9, List.of(segments.get(SegmentPosition.UP), segments.get(SegmentPosition.UP_LEFT), segments.get(SegmentPosition.UP_RIGHT), segments.get(SegmentPosition.MIDDLE), segments.get(SegmentPosition.BOTTOM_RIGHT), segments.get(SegmentPosition.BOTTOM)));
        digits.put(0, List.of(segments.get(SegmentPosition.UP), segments.get(SegmentPosition.UP_LEFT), segments.get(SegmentPosition.UP_RIGHT), segments.get(SegmentPosition.BOTTOM_LEFT), segments.get(SegmentPosition.BOTTOM_RIGHT), segments.get(SegmentPosition.BOTTOM)));
        digits.put(6, List.of(segments.get(SegmentPosition.UP), segments.get(SegmentPosition.UP_LEFT), segments.get(SegmentPosition.BOTTOM_LEFT), segments.get(SegmentPosition.MIDDLE), segments.get(SegmentPosition.BOTTOM_RIGHT), segments.get(SegmentPosition.BOTTOM)));
    }
}
