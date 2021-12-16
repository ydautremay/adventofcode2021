package dyve.aoc2021.day.day16;

import java.util.HashMap;
import java.util.Map;

public class BinaryUtils {
    private static Map<String, String> digiMap = new HashMap<>();

    static {
        digiMap.put("0", "0000");
        digiMap.put("1", "0001");
        digiMap.put("2", "0010");
        digiMap.put("3", "0011");
        digiMap.put("4", "0100");
        digiMap.put("5", "0101");
        digiMap.put("6", "0110");
        digiMap.put("7", "0111");
        digiMap.put("8", "1000");
        digiMap.put("9", "1001");
        digiMap.put("A", "1010");
        digiMap.put("B", "1011");
        digiMap.put("C", "1100");
        digiMap.put("D", "1101");
        digiMap.put("E", "1110");
        digiMap.put("F", "1111");
    }

    public static String hexToBin(String s) {
        char[] hex = s.toCharArray();
        String binaryString = "";
        for (char h : hex) {
            binaryString = binaryString + digiMap.get(String.valueOf(h));
        }
        return binaryString;
    }
}
