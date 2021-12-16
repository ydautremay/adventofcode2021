package dyve.aoc2021.day.day16;

import dyve.aoc2021.day.Part;
import dyve.aoc2021.day.day15.Point;
import dyve.aoc2021.dijkstra.Dijkstra;
import dyve.aoc2021.dijkstra.Graph;
import dyve.aoc2021.dijkstra.Node;
import dyve.aoc2021.input.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 extends Part {

    public static void main(String[] args) throws Exception {
        new Part1().subMain(16);
    }

    @Override
    public Object execute(InputReader inputReader) {
        String hex = inputReader.stream().findFirst().orElseThrow();
        String bin = BinaryUtils.hexToBin(hex);

        Packet packet = Packet.of(bin);
        packet.decode();
        return sumVersions(packet);
    }

    public int sumVersions(Packet packet){
        if(packet.packets.isEmpty()){
            return packet.version;
        }
        int sum = 0;
        for(Packet p : packet.packets){
            sum += sumVersions(p);
        }
        return sum + packet.version;
    }
}