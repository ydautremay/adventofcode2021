package dyve.aoc2021.day.day16;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.input.InputReader;

public class Part2 extends Part {

    public static void main(String[] args) throws Exception {
        new Part2().subMain(16);
    }

    @Override
    public Object execute(InputReader inputReader) {
        String hex = inputReader.stream().findFirst().orElseThrow();
        String bin = BinaryUtils.hexToBin(hex);

        Packet packet = Packet.of(bin);
        packet.decode();

        return packet.evaluate();
    }
}