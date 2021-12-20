package dyve.aoc2021.day.day20;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.day.day18.Operation;
import dyve.aoc2021.input.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 extends Part {

    public static void main(String[] args) throws Exception {
        new Part1().subMain(20);
    }

    Map<Point, String> image = new HashMap<>();

    @Override
    public Object execute(InputReader inputReader) {
        List<String> entries = inputReader.stream().toList();

        String algo = entries.get(0);

        for(int y = 2; y < entries.size(); y++){
            String entry = entries.get(y);
            for(int x = 0; x < entries.get(y).length(); x++){
                image.put(Point.of(x, entries.size() -y - 2), String.valueOf(entry.charAt(x)));
            }
        }

//        int minY = Integer.MIN_VALUE + 1000;
//        int maxY = Integer.MAX_VALUE - 1000;
//        int minX = Integer.MIN_VALUE + 1000;
//        int maxX = Integer.MAX_VALUE - 1000;
        int minY = -100;
        int maxY = 200;
        int minX = -100;
        int maxX = 200;

//        for (Point point : image.keySet()) {
//            minY = Math.min(minY, point.y);
//            maxY = Math.max(maxY, point.y);
//            minX = Math.min(minX, point.x);
//            maxX = Math.max(maxX, point.x);
//        }

        System.out.println(printImage(minY, maxY, minX, maxX));

        for(int i = 0; i < 2; i++){
            Map<Point, String> enhanced = new HashMap<>();
            for(int x = minX; x < maxX; x++){
                for(int y = minY; y < maxY; y++){
                    Point p = Point.of(x, y);
                    String bin = image.getOrDefault(p.aboveLeft(), ".")
                            + image.getOrDefault(p.above(), ".")
                            + image.getOrDefault(p.aboveRight(), ".")
                            + image.getOrDefault(p.left(), ".")
                            + image.getOrDefault(p, ".")
                            + image.getOrDefault(p.right(), ".")
                            + image.getOrDefault(p.belowLeft(), ".")
                            + image.getOrDefault(p.below(), ".")
                            + image.getOrDefault(p.belowRight(), ".");
                    bin = bin.replace('.', '0');
                    bin = bin.replace('#', '1');
                    int pos = Integer.parseInt(bin, 2);
                    enhanced.put(p, String.valueOf(algo.charAt(pos)));
                }
            }
            image = enhanced;
            minY += 1;
            maxY -= 1;
            minX += 1;
            maxX -= 1;
            System.out.println(printImage(minY, maxY, minX, maxX));
        }

        return image.values().stream().filter(v -> v.equals("#")).count();
    }

    public String printImage(int minY, int maxY, int minX, int maxX){
        StringBuilder sb = new StringBuilder();
        for(int y = maxY; y > minY; y--) {
            for (int x = minX; x < maxX; x++) {
                sb.append(image.getOrDefault(Point.of(x, y), "."));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}